package content.plan.board.service;

import content.plan.board.mapper.MapperDTO;
import content.plan.board.repository.CommentMappingRepository;
import content.plan.board.structure.CommentMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static content.plan.board.mapper.Mapper.getActualTime;
import static content.plan.board.mapper.Mapper.getFieldType;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentMappingImp implements MappingService{

    private final CommentMappingRepository repository;


    @Override
    public List<MapperDTO> getByEntityOne(Long entityId) {
        return repository.findAll().stream()
                .filter(i -> i.getCommentId() == entityId && i.isActive())
                .map(CommentMappingImp::mapToDto)
                .toList();
    }

    @Override
    public List<MapperDTO> getByEntityTwo(Long entityId) {
        return repository.findAll().stream()
                .filter(i -> i.getCommentId() == entityId && i.isActive())
                .map(CommentMappingImp::mapToDto)
                .toList();
    }

    @Override
    public MapperDTO create(MapperDTO mapperDTO) {
        CommentMapping mapping = mapToEntity(mapperDTO);
        mapping.setActive(true);
        mapping.setCreateDate(getActualTime());
        repository.save(mapping);
        return mapToDto(mapping);
    }

    @Override
    public MapperDTO delete(Long id, Long entityId) {
        CommentMapping mapping = repository.findAll().stream()
                .filter(i ->i.getId().equals(id))
                .findFirst()
                .orElseThrow();

        mapping.setActive(false);
        repository.save(mapping);
        return mapToDto(mapping);
    }

    public static MapperDTO mapToDto(CommentMapping commentMapping) {

        return MapperDTO.builder()
                .id(commentMapping.getId())
                .entityOne(commentMapping.getCommentId())
                .entityTwo(commentMapping.getFieldId())
                .type(getFieldType(commentMapping.getType()))
                .createDate(commentMapping.getCreateDate().toInstant().toEpochMilli())
                .updateDate(commentMapping.getUpdateDate().toInstant().toEpochMilli())
                .active(commentMapping.isActive())
                .build();
    }

    public static CommentMapping mapToEntity(MapperDTO mapperDTO) {
        CommentMapping commentMapping = new CommentMapping();
        commentMapping.setCommentId(mapperDTO.getEntityOne());
        commentMapping.setFieldId(mapperDTO.getEntityTwo());
        return commentMapping;
    }
}
