package content.plan.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestBoardDTO {

    private Long author;
    private String name;
    private boolean active;

}
