package content.plan.board.dto.comment;

import content.plan.users.dto.ResponseUserDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseCommentDTO {

    private Long id;
    private ResponseUserDTO author;
    private String comment;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
