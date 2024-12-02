package content.plan.board.api;

import content.plan.board.dto.plan.RequestDatePlanDTO;
import content.plan.board.dto.plan.ResponseDatePlanDTO;
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
    public ResponseDatePlanDTO getById(@PathVariable Long id) {return datePlanService.getById(id);}

    @GetMapping
    public List<ResponseDatePlanDTO> getAllByBoardId(@PathVariable Long boardId) {
        return datePlanService.getAllByBoardId(boardId);
    }

    @PostMapping
    public ResponseDatePlanDTO create(@RequestBody RequestDatePlanDTO request) {
        return datePlanService.create(request);
    }

    @PatchMapping("/{id}")
    public ResponseDatePlanDTO update(@PathVariable Long id, @RequestBody RequestDatePlanDTO request) {
        return datePlanService.update(id, request);
    }
}
