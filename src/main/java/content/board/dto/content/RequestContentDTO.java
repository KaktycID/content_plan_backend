package content.board.dto.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestContentDTO {

    private Long datePlan;
    private Long author;
    private int type;
    private String title;
    private String contentFile;
    private String description;
    private String hashtags;
    private String audio;
    private boolean active;
}
