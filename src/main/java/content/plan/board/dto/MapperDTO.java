package content.plan.board.dto;

import content.plan.board.dto.comment.ResponseCommentDTO;
import content.plan.board.dto.content.ResponseContentDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MapperDTO {

    private Long id;
    private ResponseCommentDTO entityOne;
    private ResponseContentDTO entityTwo;
    private DictionaryDTO type;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
