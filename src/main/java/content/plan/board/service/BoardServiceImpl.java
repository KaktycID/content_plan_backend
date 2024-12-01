package content.plan.board.service;

import content.plan.board.dto.RequestBoardDTO;
import content.plan.board.dto.ResponseBoardDTO;
import content.plan.board.repository.BoardRepository;
import content.plan.board.structure.Board;
import content.plan.users.service.UserServiceImpl;
import content.plan.users.structure.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import static content.plan.board.mapper.DictionaryMapper.getActualTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

    private static UserServiceImpl service;
    private final BoardRepository repository;
    private final UserServiceImpl userMapper;


    @Override
    public ResponseBoardDTO getById(Long id) {
        Board board = repository.findById(id).orElseThrow();
        return mapToDto(board);
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
        Board board = mapToEntity(requestBoardDTO);
        board.setCreateDate(getActualTime());
        board.setActive(true);
        repository.save(board);
        return mapToDto(board);
    }

    @Override
    public ResponseBoardDTO update(Long id, RequestBoardDTO requestBoardDTO) {
        boolean active = Boolean.TRUE.equals(requestBoardDTO.isActive());
        String name = requestBoardDTO.getName();
        Board board = repository.findById(id).orElseThrow();

        if (!active) {

            if (!name.equals(board.getName())) {
                board.setName(name);
                board.setUpdateDate(getActualTime());
            }
        }

        else {
            board.setActive(false);
            board.setUpdateDate(getActualTime());
        }

        return mapToDto(board);
    }


    public ResponseBoardDTO mapToDto(Board board) {

        return ResponseBoardDTO.builder()
                .id(board.getId())
                .author(userMapper.mapToDto(board.getAuthorId()))
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

}
