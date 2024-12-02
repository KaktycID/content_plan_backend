package content.plan.users.dto;

import content.plan.board.dto.DictionaryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
