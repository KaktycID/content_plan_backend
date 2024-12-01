package content.plan.board.api;

import content.plan.board.dto.DatePlanDTO;
import content.plan.board.service.DatePlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/date-plan")
@RequiredArgsConstructor
public class DatePlanController {

    private final DatePlanService datePlanService;

    @GetMapping("/{id}")
    public DatePlanDTO getById(@PathVariable Long id) {return datePlanService.getById(id);}

    @GetMapping
    public List<DatePlanDTO> getAllByBoardId(@PathVariable Long boardId) {
        return datePlanService.getAllByBoardId(boardId);
    }

    @PostMapping
    public DatePlanDTO create(@RequestBody DatePlanDTO request) {
        return datePlanService.create(request);
    }

    @PatchMapping("/{id}")
    public DatePlanDTO update(@PathVariable Long id, @RequestBody DatePlanDTO request) {
        return datePlanService.update(id, request);
    }
}
