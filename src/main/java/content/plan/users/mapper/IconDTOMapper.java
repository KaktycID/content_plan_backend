package content.plan.users.mapper;

import content.plan.board.dto.DictDTO;
import content.plan.users.repository.IconsRepository;
import content.plan.users.structure.Icons;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
public class IconDTOMapper {


    public DictDTO mapIconToDTO(Icons icon) {
        return DictDTO.builder()
                .id(icon.getId())
                .title(icon.getTitle())
                .build();
    }

    public Icons mapIcon(DictDTO icon) {
        return Icons.builder()
                .id(icon.getId())
                .title(icon.getTitle())
                .build();
    }

}
