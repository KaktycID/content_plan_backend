package content.plan.board.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import content.plan.users.dto.ResponseUserDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestCommentDTO {

    private Long author;
    private String comment;
    private boolean active;
}
