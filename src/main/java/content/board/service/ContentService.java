package content.board.service;

import content.board.dto.content.RequestContentDTO;
import content.board.dto.content.ResponseContentDTO;

import java.util.List;

public interface ContentService {

    ResponseContentDTO getById(Long id);
    ResponseContentDTO create(RequestContentDTO requestContentDTO);
    List<ResponseContentDTO> getAllByDatePlanId(Long datePlanId);
    ResponseContentDTO update(Long id, RequestContentDTO requestContentDTO);
}
