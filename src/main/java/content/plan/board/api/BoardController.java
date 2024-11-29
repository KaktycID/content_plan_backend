package content.plan.board.api;

import content.plan.board.dto.BoardDTO;
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
    public BoardDTO create(@RequestBody BoardDTO request) {
        return boardService.create(request);
    }

    @GetMapping("/{id}")
    public BoardDTO getById(@PathVariable Long id) {
        return boardService.getById(id);
    }

    @GetMapping
    public List<BoardDTO> getAllByAuthor(@RequestBody Long authorId) {
        return boardService.getAllByAuthor(authorId);
    }

    @PatchMapping("/{id}")
    public BoardDTO patch(@PathVariable Long id, @RequestBody BoardDTO request) {
        return boardService.update(id, request);
    }


}
