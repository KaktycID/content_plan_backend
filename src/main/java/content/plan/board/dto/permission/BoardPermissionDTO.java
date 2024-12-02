package content.plan.board.dto.permission;

import content.plan.board.dto.DictionaryDTO;
import content.plan.board.dto.board.ResponseBoardDTO;
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
    private DictionaryDTO permissionType;
    private ResponseUserDTO user;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
