package content.plan.board.api;

import content.plan.board.dto.ContentDTO;
import content.plan.board.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/{id}")
    public ContentDTO getById(@PathVariable Long id) {return contentService.getById(id);}

    @PostMapping
    public ContentDTO create(@RequestBody ContentDTO request) {
        return contentService.create(request);
    }

    @GetMapping("/date-plan/{id}")
    public List<ContentDTO> getAllByDatePlanId(@PathVariable Long id) {
        return  contentService.getAllByDatePlanId(id);
    }

    @PatchMapping("/{id}")
    public ContentDTO update(@PathVariable Long id, @RequestBody ContentDTO request) {
        return contentService.update(id, request);
    }
}
