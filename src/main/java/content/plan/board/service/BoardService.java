package content.plan.board.service;

import content.plan.board.dto.RequestBoardDTO;
import content.plan.board.dto.ResponseBoardDTO;

import java.util.List;

public interface BoardService {

    ResponseBoardDTO getById(Long id);
    List<ResponseBoardDTO> getAllByAuthor(Long authorId);

    ResponseBoardDTO create(RequestBoardDTO requestBoardDTO);

    ResponseBoardDTO update(Long id, RequestBoardDTO requestBoardDTO);

}
