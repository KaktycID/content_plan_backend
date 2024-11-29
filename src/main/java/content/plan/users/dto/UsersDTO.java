package content.plan.users.dto;

import content.plan.board.dto.DictDTO;
import content.plan.users.structure.Icons;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private Long id;
    private String email;
    private String password;
    private String name;
    private DictDTO icon;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
