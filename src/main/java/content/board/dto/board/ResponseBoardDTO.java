package content.board.dto.board;

import content.users.dto.ResponseUserDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseBoardDTO {

    private Long id;
    private ResponseUserDTO author;
    private String name;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
