package content.plan.board.service;

import content.plan.board.dto.BoardDTO;
import content.plan.board.structure.Board;
import content.plan.users.structure.Users;

import java.util.List;

public interface BoardService {

    BoardDTO getById(Long id);
    List<BoardDTO> getAllByAuthor(Long authorId);

    BoardDTO create(BoardDTO boardDTO);

    BoardDTO update(BoardDTO boardDTO, Long id);

}
