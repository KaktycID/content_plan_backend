package content.plan.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import content.plan.board.structure.enums.BoardPermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DictionaryDTO {

    private int id;
    private String title;
    private BoardPermissionEnum code;

}
