package content.plan.board.service;

import content.plan.board.dto.BoardDTO;
import content.plan.board.repository.BoardRepository;
import content.plan.board.structure.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;


    @Override
    public BoardDTO getById(Long id) {
        return null;
    }

    @Override
    public List<BoardDTO> getAllByAuthor(Long authorId) {
        return List.of();
    }

    @Override
    public BoardDTO create(BoardDTO board) {
        return null;
    }

    @Override
    public BoardDTO update(Long id, BoardDTO board) {
        return null;
    }

    @Override
    public BoardDTO delete(BoardDTO board) {
        return null;
    }

    public static BoardDTO mapToDto(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setAuthorId(board.getAuthorId());
    }

    public static Board mapToEntity(BoardDTO boardDto) {

    }
}
