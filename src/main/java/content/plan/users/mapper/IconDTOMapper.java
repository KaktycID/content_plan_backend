package content.plan.users.mapper;

import content.plan.board.dto.DictDTO;
import content.plan.users.structure.DictIcons;
import org.springframework.stereotype.Component;

@Component
public class IconDTOMapper {

    public DictDTO mapIconToDTO(DictIcons icon) {
        return DictDTO.builder()
                .id(icon.getId())
                .title(icon.getTitle())
                .build();
    }

    public DictIcons mapIcon(DictDTO icon) {
        return DictIcons.builder()
                .id(icon.getId())
                .title(icon.getTitle())
                .build();
    }
}
