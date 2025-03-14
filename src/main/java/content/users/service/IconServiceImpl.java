package content.users.service;

import content.board.dto.DictionaryDTO;
import content.users.repository.IconsRepository;
import content.users.structure.Icons;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public DictionaryDTO mapIconToDTO(Icons icon) {
        return DictionaryDTO.builder()
                .id(icon.getId())
                .title(icon.getTitle())
                .build();
    }

    public Icons mapIconToEntity(DictionaryDTO iconDto) {
        Icons icon = new Icons();
        icon.setId(iconDto.getId());
        icon.setTitle(iconDto.getTitle());
        return icon;
    }
}
