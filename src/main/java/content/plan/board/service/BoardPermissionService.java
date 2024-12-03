package content.plan.board.service;

import content.plan.board.dto.permission.BoardPermissionDTO;
import content.plan.board.structure.BoardPermission;

import java.util.List;

public interface BoardPermissionService {

    List<BoardPermission> getByUserIdAndBoardId(Long userId, Long boardId);
    void create(Long userId, Long boardId);
    void delete(Long id, boolean active);
    boolean checkEdite(List<BoardPermission> boardPermissionList);
    boolean checkDelete(List<BoardPermission> boardPermissionList);

}
