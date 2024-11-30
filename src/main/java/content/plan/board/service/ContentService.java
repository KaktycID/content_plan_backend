package content.plan.board.service;

import content.plan.board.dto.ContentDTO;

import java.util.List;

public interface ContentService {

    ContentDTO getById(Long id);
    ContentDTO create(ContentDTO contentDTO);
    List<ContentDTO> getAllByDatePlanId(Long datePlanId);
    ContentDTO update(Long id, ContentDTO contentDTO);
}
