package content.plan.board.mapper;

import content.plan.board.dto.comment.ResponseCommentDTO;
import content.plan.board.dto.content.ResponseContentDTO;
import content.plan.board.dto.DictionaryDTO;
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
