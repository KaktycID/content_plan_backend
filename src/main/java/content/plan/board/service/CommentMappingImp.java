package content.plan.board.service;

import content.plan.board.dto.BoardDTO;
import content.plan.board.dto.DictDTO;
import content.plan.board.mapper.MapperDTO;
import content.plan.board.repository.CommentMappingRepository;
import content.plan.board.repository.TypeRepository;
import content.plan.board.structure.Board;
import content.plan.board.structure.CommentMapping;
import content.plan.board.structure.Types;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static content.plan.board.mapper.DictDTOMapper.mapType;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentMappingImp implements MappingService{

    private final CommentMappingRepository repository;
    private static TypeRepository types;


    public static DictDTO getType(int typeId) {
        Types type = types.findById(typeId).orElseThrow();
        return mapType(type);
    }

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
        return null;
    }

    @Override
    public MapperDTO update(Long id, MapperDTO mapperDTO) {
        return null;
    }

    public static MapperDTO mapToDto(CommentMapping commentMapping) {

        return MapperDTO.builder()
                .id(commentMapping.getId())
                .entityOne(commentMapping.getCommentId())
                .entityTwo(commentMapping.getContentId())
                .type(getType(commentMapping.getType()))
                .createDate(commentMapping.getCreateDate().toInstant().toEpochMilli())
                .updateDate(commentMapping.getUpdateDate().toInstant().toEpochMilli())
                .active(commentMapping.isActive())
                .build();
    }

    public static CommentMapping mapToEntity(MapperDTO mapperDTO) {
        CommentMapping commentMapping = new CommentMapping();
        commentMapping.setCommentId(mapperDTO.getEntityOne());
        commentMapping.setContentId(mapperDTO.getEntityTwo());
        return commentMapping;
    }
}
