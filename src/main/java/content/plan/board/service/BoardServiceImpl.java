package content.plan.board.service;

import content.plan.board.dto.BoardDTO;
import content.plan.board.mapper.DictDTOMapper;
import content.plan.board.repository.BoardRepository;
import content.plan.board.structure.Board;
import content.plan.users.mapper.UsersDTOMapper;
import content.plan.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

    private static DictDTOMapper mapper;
    private static UsersDTOMapper userMapper;

    private final BoardRepository repository;

    UsersRepository users;

    @Override
    public BoardDTO getById(Long id) {
        Board board = repository.findById(id).orElseThrow();
        return mapToDto(board);
    }

    @Override
    public List<BoardDTO> getAllByAuthor(Long authorId) {
        return repository.findAll()
                .stream()
                .filter(i -> i.getId().equals(authorId) && i.isActive())
                .map(BoardServiceImpl::mapToDto)
                .toList();
    }

    @Override
    public BoardDTO create(BoardDTO boardDTO) {
        Board board = mapToEntity(boardDTO);
        board.setCreateDate(ZonedDateTime.now());
        board.setActive(true);
        repository.save(board);
        return mapToDto(board);
    }

    @Override
    public BoardDTO update(BoardDTO boardDTO, Long id) {
        boolean active = Boolean.TRUE.equals(boardDTO.isActive());
        String name = boardDTO.getName();
        Board board = repository.findById(id).orElseThrow();

        if (!active) {

            if (!name.equals(board.getName())) {
                board.setName(name);
                board.setUpdateDate(ZonedDateTime.now());
            }
        }

        else {
            board.setActive(false);
        }

        return mapToDto(board);
    }

    public static BoardDTO mapToDto(Board board) {

        return BoardDTO.builder()
                .id(board.getId())
                .author(board.getAuthorId())
                .name(board.getName())
                .createDate(board.getCreateDate().toInstant().toEpochMilli())
                .updateDate(board.getUpdateDate().toInstant().toEpochMilli())
                .active(board.isActive())
        .build();
    }

    public static Board mapToEntity(BoardDTO boardDto) {
        Board board = new Board();
        board.setAuthorId(board.getAuthorId());
        board.setName(boardDto.getName());
        return board;
    }

//    board.setCreateDate(ZonedDateTime.now());
//        board.setActive(true);
}
