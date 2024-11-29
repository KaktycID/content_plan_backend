package content.plan.board.dto;

import content.plan.users.dto.UsersDTO;
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
    private UsersDTO author;
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
