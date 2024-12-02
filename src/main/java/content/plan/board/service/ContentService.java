package content.plan.board.service;

import content.plan.board.dto.content.RequestContentDTO;
import content.plan.board.dto.content.ResponseContentDTO;
import content.plan.users.dto.RequestUserDTO;

import java.util.List;

public interface ContentService {

    ResponseContentDTO getById(Long id);
    ResponseContentDTO create(RequestContentDTO requestContentDTO);
    List<ResponseContentDTO> getAllByDatePlanId(Long datePlanId);
    ResponseContentDTO update(Long id, RequestContentDTO requestContentDTO);
}
