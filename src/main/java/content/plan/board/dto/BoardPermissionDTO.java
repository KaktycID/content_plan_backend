package content.plan.board.dto;

import content.plan.users.dto.ResponseUserDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardPermissionDTO {

    private Long id;
    private ResponseBoardDTO board;
    private DictDTO permissionType;
    private ResponseUserDTO user;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
