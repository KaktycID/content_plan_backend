package content.plan.board.dto;

import content.plan.users.dto.ResponseUserDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContentDTO {

    private Long id;
    private DatePlanDTO datePlan;
    private ResponseUserDTO author;
    private DictDTO type;
    private String title;
    private String contentFile;
    private String description;
    private String hashtags;
    private String audio;
    private Long createDate;
    private Long updateDate;
    private boolean active;

}
