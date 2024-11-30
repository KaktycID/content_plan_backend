package content.plan.board.service;

import content.plan.board.dto.BoardDTO;
import content.plan.board.repository.BoardRepository;
import content.plan.board.structure.Board;
import content.plan.users.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import java.util.List;

import static content.plan.board.mapper.Mapper.getActualTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

    private static UserServiceImpl service;
    private final BoardRepository repository;


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
        board.setCreateDate(getActualTime());
        board.setActive(true);
        repository.save(board);
        return mapToDto(board);
    }

    @Override
    public BoardDTO update(Long id, BoardDTO boardDTO) {
        boolean active = Boolean.TRUE.equals(boardDTO.isActive());
        String name = boardDTO.getName();
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


    public static BoardDTO mapToDto(Board board) {

        return BoardDTO.builder()
                .id(board.getId())
                .author(service.getById(board.getAuthorId()))
                .name(board.getName())
                .createDate(board.getCreateDate().toInstant().toEpochMilli())
                .updateDate(board.getUpdateDate().toInstant().toEpochMilli())
                .active(board.isActive())
                .build();
    }

    public static Board mapToEntity(BoardDTO boardDto) {
        Board board = new Board();
        board.setId(board.getId());
        board.setAuthorId(boardDto.getAuthor().getId());
        board.setName(boardDto.getName());
        board.setCreateDate(new Timestamp(boardDto.getCreateDate()));
        board.setUpdateDate(new Timestamp(boardDto.getUpdateDate()));
        board.setActive(boardDto.isActive());
        return board;
    }

}
