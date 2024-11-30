package content.plan.board.service;

import content.plan.board.dto.CommentDTO;
import content.plan.board.mapper.MapperDTO;
import content.plan.board.repository.CommentMappingRepository;
import content.plan.board.repository.CommentRepository;
import content.plan.board.structure.Comment;
import content.plan.users.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static content.plan.board.mapper.Mapper.getActualTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository repository;
    private final CommentMappingRepository mappingRepository;
    private static UserServiceImpl service;
    private static CommentMappingImp commentMappingService;

    @Override
    public CommentDTO getById(Long id) {
        Comment comment = repository.findById(id).orElseThrow();
        if (comment.isActive()) {
            return mapToDto(comment);
        } else {
            return null;
        }
    }

    @Override
    public List<CommentDTO> getAllByContentId(Long contentId) {
        List<CommentDTO> comments = new ArrayList<>();
        List<MapperDTO> commentOnContentMapping = commentMappingService.getByEntityTwo(contentId);

        for(MapperDTO unmappedComment : commentOnContentMapping) {
            comments.add(getById(unmappedComment.getEntityOne()));
        }
        return comments;
    }

    @Override
    public CommentDTO create(Long contentId, CommentDTO commentDTO) {
        Comment comment = mapToEntity(commentDTO);
        comment.setCreateDate(getActualTime());
        comment.setActive(true);
        repository.save(comment);
        return mapToDto(comment);
    }

    @Override
    public CommentDTO update(Long commentId, Long entityId, CommentDTO commentDTO) {
        boolean active = Boolean.TRUE.equals(commentDTO.isActive());
        Comment comment = repository.findById(commentId).orElseThrow();

        if (!active) {

            comment.setComment(commentDTO.getComment());
            comment.setUpdateDate(getActualTime());

        } else {

            comment.setActive(false);
            comment.setUpdateDate(getActualTime());
            commentMappingService.delete(commentId, entityId);
        }
        repository.save(comment);

        return mapToDto(comment);
    }

    public static CommentDTO mapToDto(Comment comment) {

        return CommentDTO.builder()
                .id(comment.getId())
                .author(service.getById(comment.getAuthorId()))
                .comment(comment.getComment())
                .createDate(comment.getCreateDate().toInstant().toEpochMilli())
                .updateDate(comment.getUpdateDate().toInstant().toEpochMilli())
                .active(comment.isActive())
                .build();
    }

    public static Comment mapToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(comment.getId());
        comment.setAuthorId(commentDTO.getAuthor().getId());
        comment.setComment(commentDTO.getComment());
        comment.setCreateDate(new Timestamp(commentDTO.getCreateDate()));
        comment.setUpdateDate(new Timestamp(commentDTO.getUpdateDate()));
        comment.setActive(commentDTO.isActive());
        return comment;
    }
}
