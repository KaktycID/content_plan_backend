package content.board.api;

import content.board.dto.content.RequestContentDTO;
import content.board.dto.content.ResponseContentDTO;
import content.board.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/{id}")
    public ResponseContentDTO getById(@PathVariable Long id) {return contentService.getById(id);}

    @PostMapping
    public ResponseContentDTO create(@RequestBody RequestContentDTO request) {
        return contentService.create(request);
    }

    @GetMapping("/date-plan/{id}")
    public List<ResponseContentDTO> getAllByDatePlanId(@PathVariable Long id) {
        return  contentService.getAllByDatePlanId(id);
    }

    @PatchMapping("/{id}")
    public ResponseContentDTO update(@PathVariable Long id, @RequestBody RequestContentDTO request) {
        return contentService.update(id, request);
    }
}
