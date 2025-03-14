package content.board.service;

import content.board.mapper.DictionaryMapper;
import content.board.dto.MapperDTO;
import content.board.repository.CommentMappingRepository;
import content.board.structure.CommentMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static content.board.mapper.DictionaryMapper.getActualTime;

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
        List<CommentMapping> commentMappingList = repository.findAll().stream()
                .filter(i -> Objects.equals(i.getCommentId().getId(), entityId) && i.isActive())
                .toList();
        List<MapperDTO> mapperDTOList = new ArrayList<>();
        for(CommentMapping comment : commentMappingList) {
            mapperDTOList.add(mapToDto(comment));
        }
        return mapperDTOList;
    }

    @Override
    public List<MapperDTO> getByEntityTwo(Long entityId) {
        List<CommentMapping> commentMappingList = repository.findAll().stream()
                .filter(i -> Objects.equals(i.getFieldId().getId(), entityId) && i.isActive())
                .toList();
        List<MapperDTO> mapperDTOList = new ArrayList<>();
        for(CommentMapping comment : commentMappingList) {
            mapperDTOList.add(mapToDto(comment));
        }
        return mapperDTOList;
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

    public MapperDTO mapToDto(CommentMapping commentMapping) {

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

    public CommentMapping mapToEntity(MapperDTO mapperDTO) {
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
