package content.board.dto.plan;

import content.board.dto.board.ResponseBoardDTO;
import content.users.dto.ResponseUserDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDatePlanDTO {

    private Long id;
    private ResponseBoardDTO board;
    private ResponseUserDTO author;
    private Long plannedDate;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
