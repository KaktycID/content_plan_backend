package content.plan.board.service;

import content.plan.board.dto.plan.RequestDatePlanDTO;
import content.plan.board.dto.plan.ResponseDatePlanDTO;

import java.util.List;

public interface DatePlanService {

    ResponseDatePlanDTO getById(Long id);
    List<ResponseDatePlanDTO> getAllByBoardId(Long boardId);
    ResponseDatePlanDTO create(RequestDatePlanDTO requestDatePlanDTO);
    ResponseDatePlanDTO update(Long id, RequestDatePlanDTO requestDatePlanDTO);

}
