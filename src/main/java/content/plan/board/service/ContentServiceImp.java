package content.plan.board.service;

import content.plan.board.dto.ContentDTO;
import content.plan.board.mapper.DictionaryMapper;
import content.plan.board.repository.ContentRepository;
import content.plan.board.structure.Content;
import content.plan.users.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static content.plan.board.mapper.DictionaryMapper.getActualTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class ContentServiceImp implements ContentService{

    private final ContentRepository repository;
    private static DatePlanServiceImp datePlanService;
    private static UserServiceImpl userService;
    private static UserServiceImpl userMapper;
    private static DictionaryMapper dictMapper;

    @Override
    public ContentDTO getById(Long id) {
        Content content = repository.findById(id).orElseThrow();
        if(content.isActive()) {
            return mapToDto(content);
        }
        return null;
    }

    @Override
    public ContentDTO create(ContentDTO contentDTO) {
        Content content = mapToEntity(contentDTO);
        content.setCreateDate(getActualTime());
        content.setActive(true);
        repository.save(content);
        return mapToDto(content);
    }

    @Override
    public List<ContentDTO> getAllByDatePlanId(Long datePlanId) {
        return repository.findAll()
                .stream()
                .filter(i -> i.getDatePlanId().equals(datePlanId) && i.isActive())
                .map(ContentServiceImp::mapToDto)
                .toList();
    }

    @Override
    public ContentDTO update(Long id, ContentDTO contentDTO) {
        boolean active = Boolean.TRUE.equals(contentDTO.isActive());
        Content content = repository.findById(id).orElseThrow();

        if (!active) {

            if(!contentDTO.getTitle().equals(content.getTitle())){
                content.setTitle(contentDTO.getTitle());
            }

            if (!contentDTO.getContentFile().equals(content.getContentFile())){
                content.setContentFile(contentDTO.getContentFile());
            }

            if (!contentDTO.getDescription().equals(content.getDescription())){
                content.setDescription((contentDTO.getDescription()));
            }

            if (!contentDTO.getHashtags().equals(content.getHashtags())) {
                content.setHashtags(contentDTO.getHashtags());
            }
            if (!contentDTO.getAudio().equals(content.getAudio())) {
                content.setAudio(contentDTO.getAudio());
            }

        } else {
            content.setActive(false);
        }

        content.setUpdateDate(getActualTime());
        repository.save(content);

        return mapToDto(content);
    }

    public static ContentDTO mapToDto(Content content) {

        return ContentDTO.builder()
                .id(content.getId())
                .datePlan(datePlanService.mapToDto(content.getDatePlanId()))
                .author(userMapper.mapToDto((content.getAuthorId())))
                .type(dictMapper.mapContentTypeToDto(content.getTypeId()))
                .title(content.getTitle())
                .contentFile(content.getContentFile())
                .description(content.getDescription())
                .hashtags(content.getHashtags())
                .audio(content.getAudio())
                .createDate(content.getCreateDate().toInstant().toEpochMilli())
                .updateDate(content.getUpdateDate().toInstant().toEpochMilli())
                .active(content.isActive())
                .build();
    }

    public static Content mapToEntity(ContentDTO contentDTO) {
        Content content = new Content();
        content.setId(contentDTO.getId());
        content.setDatePlanId(datePlanService.mapToEntity(contentDTO.getDatePlan()));
        content.setAuthorId(userMapper.mapToEntity(contentDTO.getAuthor()));
        content.setTypeId(dictMapper.mapContentTypeToEntity(contentDTO.getType()));
        content.setTitle(contentDTO.getTitle());
        content.setContentFile(contentDTO.getContentFile());
        content.setDescription(contentDTO.getDescription());
        content.setHashtags(contentDTO.getHashtags());
        content.setAudio(contentDTO.getAudio());
        content.setCreateDate(new Timestamp(contentDTO.getCreateDate()));
        content.setUpdateDate(new Timestamp(contentDTO.getUpdateDate()));
        content.setActive(contentDTO.isActive());
        return content;
    }
}
