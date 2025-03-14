package content.board.api;

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
    public ResponseBoardDTO getById(@PathVariable Long id) {
        return boardService.getById(id);
    }

    @GetMapping("/author/{authorId}")
    public List<ResponseBoardDTO> getAllByAuthor(@PathVariable Long authorId) {
        return boardService.getAllByAuthor(authorId);
    }

    @PostMapping
    public ResponseBoardDTO create(@RequestBody RequestBoardDTO request) {
        return boardService.create(request);
    }

    @PatchMapping("/{id}")
    public ResponseBoardDTO update(@PathVariable Long id, @RequestBody RequestBoardDTO request) {
        return boardService.update(id, request);
    }


}
