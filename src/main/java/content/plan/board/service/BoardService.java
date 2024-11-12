package content.plan.board.service;

import content.plan.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    BoardDTO getById(Long id);
    List<BoardDTO> getAllByAuthor(Long authorId);
    BoardDTO create(BoardDTO board);
    BoardDTO update(Long id, BoardDTO board);
    BoardDTO delete(BoardDTO board);

}
