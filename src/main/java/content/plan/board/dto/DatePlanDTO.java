package content.plan.board.dto;

import content.plan.users.dto.UsersDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DatePlanDTO {

    private Long id;
    private BoardDTO board;
    private UsersDTO author;
    private Long plannedDate;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
