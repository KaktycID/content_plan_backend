package content.plan.board.mapper;

import content.plan.board.dto.CommentDTO;
import content.plan.board.dto.ContentDTO;
import content.plan.board.dto.DictDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MapperDTO {

    private Long id;
    private CommentDTO entityOne;
    private ContentDTO entityTwo;
    private DictDTO type;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
