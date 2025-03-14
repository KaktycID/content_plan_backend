package content.board.service;

import content.board.dto.comment.RequestCommentDTO;
import content.board.dto.comment.ResponseCommentDTO;
import content.board.mapper.DictionaryMapper;
import content.board.dto.MapperDTO;
import content.board.repository.CommentMappingRepository;
import content.board.repository.CommentRepository;
import content.board.structure.Comment;
import content.users.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static content.board.mapper.DictionaryMapper.getActualTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository repository;
    private final CommentMappingRepository mappingRepository;
    private static UserServiceImpl service;
    private static CommentMappingImp commentMappingService;
    private static UserServiceImpl userMapper;
    private static DictionaryMapper dictionaryMapper;

    @Override
    public ResponseCommentDTO getById(Long id) {
        Comment comment = repository.findById(id).orElseThrow();
        if (comment.isActive()) {
            return mapToDto(comment);
        } else {
            return null;
        }
    }

    @Override
    public List<ResponseCommentDTO> getAllByContentId(Long contentId) {
        List<ResponseCommentDTO> comments = new ArrayList<>();
        List<MapperDTO> commentOnContentMapping = commentMappingService.getByEntityTwo(contentId);

        for(MapperDTO unmappedComment : commentOnContentMapping) {
            comments.add(getById(unmappedComment.getEntityOne().getId()));
        }
        return comments;
    }

    @Override
    public ResponseCommentDTO create(Long contentId, RequestCommentDTO requestCommentDTO) {
        Comment comment = mapToEntity(requestCommentDTO);
        comment.setCreateDate(getActualTime());
        comment.setActive(true);
        repository.save(comment);
        return mapToDto(comment);
    }

    @Override
    public ResponseCommentDTO update(Long commentId, Long entityId, RequestCommentDTO requestCommentDTO) {
        boolean active = Boolean.TRUE.equals(requestCommentDTO.isActive());
        Comment comment = repository.findById(commentId).orElseThrow();

        if (!active) {

            comment.setComment(requestCommentDTO.getComment());
            comment.setUpdateDate(getActualTime());

        } else {

            comment.setActive(false);
            comment.setUpdateDate(getActualTime());
            commentMappingService.delete(commentId, entityId);
        }
        repository.save(comment);

        return mapToDto(comment);
    }

    public ResponseCommentDTO mapToDto(Comment comment) {

        return ResponseCommentDTO.builder()
                .id(comment.getId())
                .author(userMapper.mapToDtoShort(comment.getAuthorId()))
                .comment(comment.getComment())
                .createDate(comment.getCreateDate().toInstant().toEpochMilli())
                .updateDate(comment.getUpdateDate().toInstant().toEpochMilli())
                .active(comment.isActive())
                .build();
    }

    public Comment mapToEntity(RequestCommentDTO requestCommentDTO) {
        Comment comment = new Comment();
        comment.setId(comment.getId());
        comment.setAuthorId(userMapper.mapToEntity(userMapper.getById(requestCommentDTO.getAuthor())));
        comment.setComment(requestCommentDTO.getComment());
        comment.setActive(requestCommentDTO.isActive());
        return comment;
    }

    public Comment mapToEntity(ResponseCommentDTO responseCommentDTO) {
        Comment comment = new Comment();
        comment.setId(comment.getId());
        comment.setAuthorId(userMapper.mapToEntity(responseCommentDTO.getAuthor()));
        comment.setComment(responseCommentDTO.getComment());
        comment.setCreateDate(new Timestamp(responseCommentDTO.getCreateDate()));
        comment.setUpdateDate(new Timestamp(responseCommentDTO.getUpdateDate()));
        comment.setActive(responseCommentDTO.isActive());
        return comment;
    }
}
