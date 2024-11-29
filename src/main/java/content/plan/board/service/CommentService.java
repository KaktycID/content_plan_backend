package content.plan.board.service;

import content.plan.board.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO getById(Long id);
    List<CommentDTO> getAllByContentId(Long contentId);
    CommentDTO create(Long contentId, CommentDTO commentDTO);
    CommentDTO update(Long id, Long entityId, CommentDTO commentDTO);
}
