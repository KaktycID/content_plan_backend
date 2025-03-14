package content.board.dto.content;

import content.board.dto.DictionaryDTO;
import content.board.dto.plan.ResponseDatePlanDTO;
import content.users.dto.ResponseUserDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseContentDTO {

    private Long id;
    private ResponseDatePlanDTO datePlan;
    private ResponseUserDTO author;
    private DictionaryDTO type;
    private String title;
    private String contentFile;
    private String description;
    private String hashtags;
    private String audio;
    private Long createDate;
    private Long updateDate;
    private boolean active;

}
