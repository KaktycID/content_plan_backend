package content.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import content.board.dto.DictionaryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUserDTO {

        private Long id;
        private String email;
        private String password;
        private String name;
        private DictionaryDTO icon;
        private Long createDate;
        private Long updateDate;
        private boolean active;

}
