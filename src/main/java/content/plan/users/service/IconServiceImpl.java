package content.plan.users.service;

import content.plan.board.dto.DictDTO;
import content.plan.users.repository.IconsRepository;
import content.plan.users.structure.Icons;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static content.plan.board.mapper.DictionaryMapper.mapIcons;

@RequiredArgsConstructor
@Slf4j
@Service
public class IconServiceImpl implements IconService{

    private final IconsRepository repository;

    @Override
    public Icons getById(int id) {
       Icons icon = repository.findById(id).orElseThrow();
       return icon;
    }

    public DictDTO mapIconToDTO(Icons icon) {
        return DictDTO.builder()
                .id(icon.getId())
                .title(icon.getTitle())
                .build();
    }

    public Icons mapIconToEntity(DictDTO iconDto) {
        Icons icon = new Icons();
        icon.setId(iconDto.getId());
        icon.setTitle(iconDto.getTitle());
        return icon;
    }
}
