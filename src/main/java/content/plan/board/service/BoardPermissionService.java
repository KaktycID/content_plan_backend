package content.plan.board.service;

import content.plan.board.dto.permission.BoardPermissionDTO;

public interface BoardPermissionService {

    BoardPermissionDTO getByUserIdAndBoardId(Long userId, Long boardId);
    BoardPermissionDTO create(BoardPermissionDTO boardPermissionDTO);
    BoardPermissionDTO delete(BoardPermissionDTO boardPermissionDTO);

}
