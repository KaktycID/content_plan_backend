package content.plan.board.mapper;

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
    private Long entityOne;
    private Long entityTwo;
    private DictDTO type;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
