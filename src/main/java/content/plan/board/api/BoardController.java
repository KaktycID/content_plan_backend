package content.plan.board.api;

import content.plan.board.dto.RequestBoardDTO;
import content.plan.board.dto.ResponseBoardDTO;
import content.plan.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseBoardDTO create(@RequestBody RequestBoardDTO request) {
        return boardService.create(request);
    }

    @GetMapping("/{id}")
    public ResponseBoardDTO getById(@PathVariable Long id) {
        return boardService.getById(id);
    }

    @GetMapping
    public List<ResponseBoardDTO> getAllByAuthor(@RequestBody Long authorId) {
        return boardService.getAllByAuthor(authorId);
    }

    @PatchMapping("/{id}")
    public ResponseBoardDTO update(@PathVariable Long id, @RequestBody RequestBoardDTO request) {
        return boardService.update(id, request);
    }


}
