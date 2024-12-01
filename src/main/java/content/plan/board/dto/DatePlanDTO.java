package content.plan.board.dto;

import content.plan.users.dto.ResponseUserDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DatePlanDTO {

    private Long id;
    private ResponseBoardDTO board;
    private ResponseUserDTO author;
    private Long plannedDate;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
