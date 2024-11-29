package content.plan.board.dto;

import content.plan.users.dto.UsersDTO;

public class DatePlanDTO {

    private Long id;
    private BoardDTO board;
    private UsersDTO author;
    private Long plannedDate;
    private Long createDate;
    private Long updateDate;
    private boolean active;
}
