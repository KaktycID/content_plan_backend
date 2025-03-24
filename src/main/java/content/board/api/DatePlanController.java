package content.board.api;

import content.auth.RequiresAuthentication;
import content.board.dto.plan.RequestDatePlanDTO;
import content.board.dto.plan.ResponseDatePlanDTO;
import content.board.service.DatePlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/date-plan")
@RequiredArgsConstructor
public class DatePlanController {

    private final DatePlanService datePlanService;

    @GetMapping("/{id}")
    @RequiresAuthentication
    public ResponseDatePlanDTO getById(@PathVariable Long id) {return datePlanService.getById(id);}

    @GetMapping
    @RequiresAuthentication
    public List<ResponseDatePlanDTO> getAllByBoardId(@PathVariable Long boardId) {
        return datePlanService.getAllByBoardId(boardId);
    }

    @PostMapping
    @RequiresAuthentication
    public ResponseDatePlanDTO create(@RequestBody RequestDatePlanDTO request) {
        return datePlanService.create(request);
    }

    @PatchMapping("/{id}")
    @RequiresAuthentication
    public ResponseDatePlanDTO update(@PathVariable Long id, @RequestBody RequestDatePlanDTO request) {
        return datePlanService.update(id, request);
    }
}
