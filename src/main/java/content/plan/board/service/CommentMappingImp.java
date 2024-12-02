package content.plan.board.service;

import content.plan.board.mapper.DictionaryMapper;
import content.plan.board.mapper.MapperDTO;
import content.plan.board.repository.CommentMappingRepository;
import content.plan.board.structure.CommentMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentMappingImp implements MappingService{

    private final CommentMappingRepository repository;
    private static CommentServiceImpl commentService;
    private static ContentServiceImp contentService;
    private static DictionaryMapper dictionaryMapper;


    @Override
    public List<MapperDTO> getByEntityOne(Long entityId) {
        return repository.findAll().stream()
                .filter(i -> Objects.equals(i.getCommentId().getId(), entityId) && i.isActive())
                .map(CommentMappingImp::mapToDto)
                .toList();
    }

    @Override
    public List<MapperDTO> getByEntityTwo(Long entityId) {
        return repository.findAll().stream()
                .filter(i -> Objects.equals(i.getFieldId().getId(), entityId) && i.isActive())
                .map(CommentMappingImp::mapToDto)
                .toList();
    }

    @Override
    public MapperDTO create(MapperDTO mapperDTO) {
        CommentMapping mapping = mapToEntity(mapperDTO);
        mapping.setActive(true);
        mapping.setCreateDate(dictionaryMapper.getActualTime());
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
                .entityOne(commentService.mapToDto(commentMapping.getCommentId()))
                .entityTwo(contentService.mapToDto(commentMapping.getFieldId()))
                .type(dictionaryMapper.mapFieldTypeToDto(commentMapping.getType()))
                .createDate(commentMapping.getCreateDate().toInstant().toEpochMilli())
                .updateDate(commentMapping.getUpdateDate().toInstant().toEpochMilli())
                .active(commentMapping.isActive())
                .build();
    }

    public static CommentMapping mapToEntity(MapperDTO mapperDTO) {
        CommentMapping commentMapping = new CommentMapping();
        commentMapping.setCommentId(commentService.mapToEntity(mapperDTO.getEntityOne()));
        commentMapping.setFieldId(contentService.mapToEntity(mapperDTO.getEntityTwo()));
        commentMapping.setType(dictionaryMapper.mapFieldTypeToEntity(mapperDTO.getType()));
        commentMapping.setCreateDate(new Timestamp(mapperDTO.getCreateDate()));
        commentMapping.setUpdateDate(new Timestamp(mapperDTO.getUpdateDate()));
        commentMapping.setActive(mapperDTO.isActive());
        return commentMapping;
    }
}
