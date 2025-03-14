package content.board.service;

import content.board.dto.board.RequestBoardDTO;
import content.board.dto.board.ResponseBoardDTO;
import content.board.mapper.DictionaryMapper;
import content.board.repository.BoardPermissionRepository;
import content.board.repository.BoardRepository;
import content.board.structure.Board;
import content.board.structure.BoardPermission;
import content.board.structure.enums.BoardPermissionEnum;
import content.handler.exceptions.BadRequestException;
import content.handler.exceptions.ForbiddenException;
import content.handler.exceptions.NotFoundException;
import content.users.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static content.board.mapper.DictionaryMapper.getActualTime;
import static content.board.structure.enums.BoardPermissionEnum.AUTHOR;
import static content.board.structure.enums.BoardPermissionEnum.READER;


@RequiredArgsConstructor
@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;
    private final UserServiceImpl userMapper;
    private final BoardPermissionRepository boardPermissionRepository;
    private final DictionaryMapper dictionaryMapper;


    @Override
    public ResponseBoardDTO getById(Long id) {
        Optional<Board> boardById = repository.findById(id);
        if (boardById.isPresent()) {
            return mapToDto(boardById.get());
        } else {
            throw new NotFoundException("Доска не существует");
        }
    }

    @Override
    public List<ResponseBoardDTO> getAllByAuthor(Long authorId) {
        List<Board> boards = repository.findAll()
                .stream()
                .filter(i -> i.getAuthorId().getId().equals(authorId) && i.isActive())
                .toList();
        List<ResponseBoardDTO> responseBoardDtoList = new ArrayList<>();
        for (Board board : boards) {
            responseBoardDtoList.add(mapToDto(board));
        }
        return responseBoardDtoList;
    }

    @Override
    public ResponseBoardDTO create(RequestBoardDTO requestBoardDTO) {
        validateRequest(requestBoardDTO);
        Board board = mapToEntity(requestBoardDTO);
        board.setCreateDate(getActualTime());
        board.setActive(true);
        repository.save(board);
        createMapping(board.getAuthorId().getId(), board.getId());
        return mapToDto(board);
    }

    @Override
    public ResponseBoardDTO update(Long boardId, RequestBoardDTO requestBoardDTO) {
        boolean active = Boolean.TRUE.equals(requestBoardDTO.isActive());
        String name = requestBoardDTO.getName();
        Board board = repository.findById(boardId).orElseThrow();
        Long userId = requestBoardDTO.getAuthor();
        boolean editable = checkEdite(getByUserIdAndBoardId(userId, boardId));

        boolean deletable = checkDelete(getByUserIdAndBoardId(userId, boardId));

        if (!active && editable) {

            if (!name.equals(board.getName())) {
                board.setName(name);
                board.setUpdateDate(getActualTime());
            }
        } else {
            throw new ForbiddenException("Редактирование доски запрещено");
        }

        if (deletable) {
            board.setActive(false);
            board.setUpdateDate(getActualTime());
        } else {
            throw new ForbiddenException("Удаление запрещено");
        }

        repository.save(board);
        return mapToDto(board);
    }


    @Override
    public List<BoardPermission> getByUserIdAndBoardId(Long userId, Long boardId) {
        return boardPermissionRepository.findAll()
                .stream()
                .filter(i -> i.getUserId().getId().equals(userId) && i.isActive()
                        && i.getBoardId().getId().equals(boardId))
                .toList();
    }

    @Override
    public void createMapping(Long userId, Long boardId) {
        BoardPermission permission = new BoardPermission();
        Board board = repository.findById(boardId).orElseThrow();
        permission.setUserId(userMapper.getUser(userId));
        permission.setBoardId(board);
        permission.setPermissionType(
                dictionaryMapper.mapPermissionTypesToEntity(
                        dictionaryMapper.getPermissionType(AUTHOR)));
        permission.setActive(true);
        boardPermissionRepository.save(permission);
    }

    @Override
    public void deleteMapping(Long id, boolean active) {
        if (Boolean.TRUE.equals(active)) {
            BoardPermission permission = boardPermissionRepository.findById(id).get();
            permission.setActive(false);
            boardPermissionRepository.save(permission);
        }
    }

    @Override
    public boolean checkEdite(List<BoardPermission> boardPermissionList) {
        List<BoardPermissionEnum> types = new ArrayList<>();
        for (BoardPermission permission : boardPermissionList) {
            types.add(permission.getPermissionType().getCode());
        }
        return types.contains(READER);
    }

    @Override
    public boolean checkDelete(List<BoardPermission> boardPermissionList) {
        List<BoardPermissionEnum> types = new ArrayList<>();
        for (BoardPermission permission : boardPermissionList) {
            types.add(permission.getPermissionType().getCode());
        }
        return types.contains(AUTHOR);
    }


    public ResponseBoardDTO mapToDto(Board board) {

        return ResponseBoardDTO.builder()
                .id(board.getId())
                .author(userMapper.mapToDtoShort(board.getAuthorId()))
                .name(board.getName())
                .createDate(board.getCreateDate().toInstant().toEpochMilli())
                .updateDate(board.getUpdateDate().toInstant().toEpochMilli())
                .active(board.isActive())
                .build();
    }

    public Board mapToEntity(RequestBoardDTO requestBoardDto) {

        Board board = new Board();
        board.setId(board.getId());
        board.setAuthorId(userMapper.mapToEntity(userMapper.getById(requestBoardDto.getAuthor())));
        board.setName(requestBoardDto.getName());
        board.setActive(requestBoardDto.isActive());
        return board;
    }

    public Board mapToEntity(ResponseBoardDTO responseBoardDTO) {
        Board board = new Board();
        board.setId(board.getId());
        board.setAuthorId(userMapper.mapToEntity(responseBoardDTO.getAuthor()));
        board.setName(responseBoardDTO.getName());
        board.setCreateDate(new Timestamp(responseBoardDTO.getCreateDate()));
        board.setUpdateDate(new Timestamp(responseBoardDTO.getUpdateDate()));
        board.setActive(responseBoardDTO.isActive());
        return board;
    }

    private void validateRequest(RequestBoardDTO requestBoardDTO) {
        if (requestBoardDTO.getName() == null) {
            throw new BadRequestException("Незаполнены обязательные поля");
        }
        if (requestBoardDTO.getAuthor() == null) {
            throw new ForbiddenException("Создание доски неавторизованным пользователем запрещено");
        }
    }

}
