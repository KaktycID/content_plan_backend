package content.plan.board.service;

import content.plan.board.dto.content.RequestContentDTO;
import content.plan.board.dto.content.ResponseContentDTO;
import content.plan.board.mapper.DictionaryMapper;
import content.plan.board.repository.ContentRepository;
import content.plan.board.structure.Content;
import content.plan.users.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ContentServiceImp implements ContentService{

    private final ContentRepository repository;
    private static DatePlanServiceImp datePlanService;
    private static UserServiceImpl userService;
    private static UserServiceImpl userMapper;
    private static DictionaryMapper dictionaryMapper;

    @Override
    public ResponseContentDTO getById(Long id) {
        Content content = repository.findById(id).orElseThrow();
        if(content.isActive()) {
            return mapToDto(content);
        }
        return null;
    }

    @Override
    public ResponseContentDTO create(RequestContentDTO requestContentDTO) {
        Content content = mapToEntity(requestContentDTO);
        content.setCreateDate(dictionaryMapper.getActualTime());
        content.setActive(true);
        repository.save(content);
        return mapToDto(content);
    }

    @Override
    public List<ResponseContentDTO> getAllByDatePlanId(Long datePlanId) {
        List<Content> contentList = repository.findAll()
                .stream()
                .filter(i -> i.getDatePlanId().getId().equals(datePlanId) && i.isActive())
                .toList();
        List<ResponseContentDTO> contentDtoList = new ArrayList<>();
        for (Content content : contentList) {
            contentDtoList.add(mapToDto(content));
        }
        return contentDtoList;
    }

    @Override
    public ResponseContentDTO update(Long id, RequestContentDTO responseContentDTO) {
        boolean active = Boolean.TRUE.equals(responseContentDTO.isActive());
        Content content = repository.findById(id).orElseThrow();

        if (!active) {

            if(!responseContentDTO.getTitle().equals(content.getTitle())){
                content.setTitle(responseContentDTO.getTitle());
            }

            if (!responseContentDTO.getContentFile().equals(content.getContentFile())){
                content.setContentFile(responseContentDTO.getContentFile());
            }

            if (!responseContentDTO.getDescription().equals(content.getDescription())){
                content.setDescription((responseContentDTO.getDescription()));
            }

            if (!responseContentDTO.getHashtags().equals(content.getHashtags())) {
                content.setHashtags(responseContentDTO.getHashtags());
            }
            if (!responseContentDTO.getAudio().equals(content.getAudio())) {
                content.setAudio(responseContentDTO.getAudio());
            }

        } else {
            content.setActive(false);
        }

        content.setUpdateDate(dictionaryMapper.getActualTime());
        repository.save(content);

        return mapToDto(content);
    }

    public ResponseContentDTO mapToDto(Content content) {

        return ResponseContentDTO.builder()
                .id(content.getId())
                .datePlan(datePlanService.mapToDto(content.getDatePlanId()))
                .author(userMapper.mapToDtoShort((content.getAuthorId())))
                .type(dictionaryMapper.mapContentTypeToDto(content.getTypeId()))
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

    public Content mapToEntity(RequestContentDTO requestContentDTO) {
        Content content = new Content();
        content.setDatePlanId(datePlanService.mapToEntity(datePlanService.getById(requestContentDTO.getDatePlan())));
        content.setAuthorId(userMapper.mapToEntity(userMapper.getById(requestContentDTO.getAuthor())));
        content.setTypeId(dictionaryMapper.mapContentTypeToEntity(dictionaryMapper.getContentType(requestContentDTO.getType())));
        content.setTitle(requestContentDTO.getTitle());
        content.setContentFile(requestContentDTO.getContentFile());
        content.setDescription(requestContentDTO.getDescription());
        content.setHashtags(requestContentDTO.getHashtags());
        content.setAudio(requestContentDTO.getAudio());
        content.setActive(requestContentDTO.isActive());
        return content;
    }

    public Content mapToEntity(ResponseContentDTO responseContentDTO) {
        Content content = new Content();
        content.setId(responseContentDTO.getId());
        content.setDatePlanId(datePlanService.mapToEntity(responseContentDTO.getDatePlan()));
        content.setAuthorId(userMapper.mapToEntity(responseContentDTO.getAuthor()));
        content.setTypeId(dictionaryMapper.mapContentTypeToEntity(responseContentDTO.getType()));
        content.setTitle(responseContentDTO.getTitle());
        content.setContentFile(responseContentDTO.getContentFile());
        content.setDescription(responseContentDTO.getDescription());
        content.setHashtags(responseContentDTO.getHashtags());
        content.setAudio(responseContentDTO.getAudio());
        content.setCreateDate(new Timestamp(responseContentDTO.getCreateDate()));
        content.setUpdateDate(new Timestamp(responseContentDTO.getUpdateDate()));
        content.setActive(responseContentDTO.isActive());
        return content;
    }
}
