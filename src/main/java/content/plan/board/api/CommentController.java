package content.plan.board.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/{id}/comment")
@RequiredArgsConstructor
public class CommentController {
}
