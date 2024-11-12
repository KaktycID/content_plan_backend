package content.plan.board.dto;

import content.plan.users.dto.UsersDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardDTO {

    private Long id;
    private UsersDTO authorId;
    private String name;
    private Long createDate;
    private Long updateDate;
    private boolean isActive;
}
