package content.plan.board.service;

import content.plan.board.dto.board.RequestBoardDTO;
import content.plan.board.dto.board.ResponseBoardDTO;
import content.plan.board.structure.BoardPermission;

import java.util.List;

public interface BoardService {

    ResponseBoardDTO getById(Long id);
    List<ResponseBoardDTO> getAllByAuthor(Long authorId);

    ResponseBoardDTO create(RequestBoardDTO requestBoardDTO);

    ResponseBoardDTO update(Long id, RequestBoardDTO requestBoardDTO);
    List<BoardPermission> getByUserIdAndBoardId(Long userId, Long boardId);
    void createMapping(Long userId, Long boardId);
    void deleteMapping(Long id, boolean active);
    boolean checkEdite(List<BoardPermission> boardPermissionList);
    boolean checkDelete(List<BoardPermission> boardPermissionList);

}
