package content.plan.board.dto.plan;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDatePlanDTO {

    private Long board;
    private Long author;
    private Long plannedDate;
    private boolean active;

}
