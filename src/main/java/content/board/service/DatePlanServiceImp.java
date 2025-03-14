package content.board.service;

import content.board.dto.plan.RequestDatePlanDTO;
import content.board.dto.plan.ResponseDatePlanDTO;
import content.board.mapper.DictionaryMapper;
import content.board.repository.DatePlanRepository;
import content.board.structure.DatePlan;
import content.users.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DatePlanServiceImp implements DatePlanService{

    private final DatePlanRepository repository;
    private static BoardServiceImpl boardService;
    private static UserServiceImpl userMapper;
    private static DictionaryMapper dictionaryMapper;

    @Override
    public ResponseDatePlanDTO getById(Long id) {
        DatePlan datePlan = repository.findById(id).orElseThrow();
        return mapToDto(datePlan);
    }

    @Override
    public List<ResponseDatePlanDTO> getAllByBoardId(Long boardId) {
        List<DatePlan> datePlanList = repository.findAll()
                .stream()
                .filter(i -> i.getBoardId().getId().equals(boardId) && i.isActive())
                .toList();
        List<ResponseDatePlanDTO> datePlanDTOList = new ArrayList<>();
        for (DatePlan datePlan : datePlanList) {
            datePlanDTOList.add(mapToDto(datePlan));
        }
        return datePlanDTOList;
    }

    @Override
    public ResponseDatePlanDTO create(RequestDatePlanDTO requestDatePlanDTO) {
        DatePlan datePlan = mapToEntity(requestDatePlanDTO);
        datePlan.setCreateDate(dictionaryMapper.getActualTime());
        datePlan.setActive(true);
        repository.save(datePlan);
        return mapToDto(datePlan);
    }

    @Override
    public ResponseDatePlanDTO update(Long id, RequestDatePlanDTO requestDatePlanDTO) {
        boolean active = Boolean.TRUE.equals(requestDatePlanDTO.isActive());
        DatePlan datePlan = repository.findById(id).orElseThrow();

        if (!active) {

            if (!datePlan.getPlannedDate().equals(new Timestamp(requestDatePlanDTO.getPlannedDate()))) {
                datePlan.setPlannedDate(Timestamp.from
                        (Instant.ofEpochSecond(requestDatePlanDTO.getPlannedDate())));
            }
        } else {

            datePlan.setActive(false);
        }
        datePlan.setUpdateDate(dictionaryMapper.getActualTime());
        repository.save(datePlan);

        return mapToDto(datePlan);
    }

    public ResponseDatePlanDTO mapToDto(DatePlan datePlan) {

        return ResponseDatePlanDTO.builder()
                .id(datePlan.getId())
                .board(boardService.mapToDto(datePlan.getBoardId()))
                .author(userMapper.mapToDtoShort(datePlan.getAuthorId()))
                .plannedDate(datePlan.getPlannedDate().toInstant().toEpochMilli())
                .createDate(datePlan.getCreateDate().toInstant().toEpochMilli())
                .updateDate(datePlan.getUpdateDate().toInstant().toEpochMilli())
                .active(datePlan.isActive())
                .build();

    }

    public DatePlan mapToEntity(RequestDatePlanDTO requestDatePlanDTO) {
        DatePlan datePlan = new DatePlan();
        datePlan.setBoardId(boardService.mapToEntity(boardService.getById(requestDatePlanDTO.getBoard())));
        datePlan.setAuthorId(userMapper.mapToEntity(userMapper.getById((requestDatePlanDTO.getAuthor()))));
        datePlan.setPlannedDate(new Timestamp(requestDatePlanDTO.getPlannedDate()));
        datePlan.setActive(requestDatePlanDTO.isActive());
        return datePlan;
    }

    public DatePlan mapToEntity(ResponseDatePlanDTO responseDatePlanDTO) {
        DatePlan datePlan = new DatePlan();
        datePlan.setId(responseDatePlanDTO.getId());
        datePlan.setBoardId(boardService.mapToEntity(responseDatePlanDTO.getBoard()));
        datePlan.setAuthorId(userMapper.mapToEntity(responseDatePlanDTO.getAuthor()));
        datePlan.setPlannedDate(new Timestamp(responseDatePlanDTO.getPlannedDate()));
        datePlan.setCreateDate(new Timestamp(responseDatePlanDTO.getCreateDate()));
        datePlan.setUpdateDate(new Timestamp(responseDatePlanDTO.getUpdateDate()));
        datePlan.setActive(responseDatePlanDTO.isActive());
        return datePlan;
    }
}
