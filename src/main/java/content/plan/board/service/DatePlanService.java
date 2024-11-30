package content.plan.board.service;

import content.plan.board.dto.DatePlanDTO;

import java.util.List;

public interface DatePlanService {

    DatePlanDTO getById(Long id);
    List<DatePlanDTO> getAllByBoardId(Long boardId);
    DatePlanDTO create(DatePlanDTO datePlanDTO);
    DatePlanDTO update(Long id, DatePlanDTO datePlanDTO);

}
