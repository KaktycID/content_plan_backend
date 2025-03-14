package content.board.dto;

import content.board.dto.comment.ResponseCommentDTO;
import content.board.dto.content.ResponseContentDTO;
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
