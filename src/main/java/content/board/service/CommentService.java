package content.board.service;

import content.board.dto.comment.RequestCommentDTO;
import content.board.dto.comment.ResponseCommentDTO;

import java.util.List;

public interface CommentService {

    ResponseCommentDTO getById(Long id);
    List<ResponseCommentDTO> getAllByContentId(Long contentId);
    ResponseCommentDTO create(Long contentId, RequestCommentDTO requestCommentDTO);
    ResponseCommentDTO update(Long id, Long entityId, RequestCommentDTO requestCommentDTO);
}
