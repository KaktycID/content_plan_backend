package content.plan.board.dto.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import content.plan.board.dto.DictionaryDTO;
import content.plan.board.dto.plan.ResponseDatePlanDTO;
import content.plan.users.dto.ResponseUserDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestContentDTO {

    private Long datePlan;
    private Long author;
    private int type;
    private String title;
    private String contentFile;
    private String description;
    private String hashtags;
    private String audio;
    private boolean active;
}
