package content.plan.board.service;

import content.plan.board.dto.DatePlanDTO;
import content.plan.board.repository.DatePlanRepository;
import content.plan.board.structure.DatePlan;
import content.plan.users.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static content.plan.board.mapper.DictionaryMapper.getActualTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class DatePlanServiceImp implements DatePlanService{

    private final DatePlanRepository repository;
    private static BoardServiceImpl boardService;
    private static UserServiceImpl userMapper;

    @Override
    public DatePlanDTO getById(Long id) {
        DatePlan datePlan = repository.findById(id).orElseThrow();
        return mapToDto(datePlan);
    }

    @Override
    public List<DatePlanDTO> getAllByBoardId(Long boardId) {
        return repository.findAll()
                .stream()
                .filter(i -> i.getBoardId().equals(boardId) && i.isActive())
                .map(DatePlanServiceImp::mapToDto)
                .toList();
    }

    @Override
    public DatePlanDTO create(DatePlanDTO datePlanDTO) {
        DatePlan datePlan = mapToEntity(datePlanDTO);
        datePlan.setCreateDate(getActualTime());
        datePlan.setActive(true);
        repository.save(datePlan);
        return mapToDto(datePlan);
    }

    @Override
    public DatePlanDTO update(Long id, DatePlanDTO datePlanDTO) {
        boolean active = Boolean.TRUE.equals(datePlanDTO.isActive());
        DatePlan datePlan = repository.findById(id).orElseThrow();

        if (!active) {

            if (!datePlan.getPlannedDate().equals(datePlanDTO.getPlannedDate())) {
                datePlan.setPlannedDate(Timestamp.from
                        (Instant.ofEpochSecond(datePlanDTO.getPlannedDate())));
            }
        } else {

            datePlan.setActive(false);
        }
        datePlan.setUpdateDate(getActualTime());
        repository.save(datePlan);

        return mapToDto(datePlan);
    }

    public static DatePlanDTO mapToDto(DatePlan datePlan) {

        return DatePlanDTO.builder()
                .id(datePlan.getId())
                .board(boardService.mapToDto(datePlan.getBoardId()))
                .author(userMapper.mapToDto(datePlan.getAuthorId()))
                .plannedDate(datePlan.getPlannedDate().toInstant().toEpochMilli())
                .createDate(datePlan.getCreateDate().toInstant().toEpochMilli())
                .updateDate(datePlan.getUpdateDate().toInstant().toEpochMilli())
                .active(datePlan.isActive())
                .build();

    }

    public static DatePlan mapToEntity(DatePlanDTO datePlanDTO) {
        DatePlan datePlan = new DatePlan();
        datePlan.setId(datePlanDTO.getId());
        datePlan.setBoardId(boardService.mapToEntity(datePlanDTO.getBoard()));
        datePlan.setAuthorId(userMapper.mapToEntity(datePlanDTO.getAuthor()));
        datePlan.setPlannedDate(new Timestamp(datePlanDTO.getPlannedDate()));
        datePlan.setCreateDate(new Timestamp(datePlanDTO.getCreateDate()));
        datePlan.setUpdateDate(new Timestamp(datePlanDTO.getUpdateDate()));
        datePlan.setActive(datePlanDTO.isActive());
        return datePlan;
    }
}
