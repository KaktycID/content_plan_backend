package content.board.api;

import content.auth.RequiresAuthentication;
import content.board.dto.comment.RequestCommentDTO;
import content.board.dto.comment.ResponseCommentDTO;
import content.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    @RequiresAuthentication
    public ResponseCommentDTO getById(@PathVariable Long id) {return commentService.getById(id);}

    @GetMapping("/content/{id}")
    @RequiresAuthentication
    public List<ResponseCommentDTO> getAllByContentId(@PathVariable Long contentId) {
    return commentService.getAllByContentId(contentId);
    }

    @PostMapping
    @RequiresAuthentication
    public ResponseCommentDTO create(Long contentId, RequestCommentDTO requestCommentDTO) {
       return commentService.create(contentId, requestCommentDTO);
    }
}
