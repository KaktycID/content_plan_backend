package content.plan.board.dto;

import content.plan.users.dto.UsersDTO;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardDTO {

    private Long id;
    private Long author;
    private String name;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
