package content.board.api;

import content.auth.RequiresAuthentication;
import content.board.dto.board.RequestBoardDTO;
import content.board.dto.board.ResponseBoardDTO;
import content.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{id}")
    @RequiresAuthentication
    public ResponseBoardDTO getById(@PathVariable Long id) {
        return boardService.getById(id);
    }

    @GetMapping
    @RequiresAuthentication
    public List<ResponseBoardDTO> getAllByAuthor(@RequestHeader("userId") Long authorId) {
        return boardService.getAllByAuthor(authorId);
    }

    @PostMapping
    @RequiresAuthentication
    public ResponseBoardDTO create(@RequestBody RequestBoardDTO request) {
        return boardService.create(request);
    }

    @PatchMapping("/{id}")
    @RequiresAuthentication
    public ResponseBoardDTO update(@PathVariable Long id, @RequestBody RequestBoardDTO request) {
        return boardService.update(id, request);
    }


}
