package content.plan.board.mapper;

import content.plan.board.dto.DictDTO;
import content.plan.board.structure.DictContentFieldType;
import content.plan.board.structure.DictContentType;
import content.plan.board.structure.DictPermissionType;
import org.springframework.stereotype.Component;

@Component
public class DictDTOMapper {

    public DictDTO mapContentField(DictContentFieldType fieldType) {
        return DictDTO.builder()
                .id(fieldType.getId())
                .title(fieldType.getTitle())
                .build();
    }

    public DictDTO mapContent(DictContentType type) {
        return DictDTO.builder()
                .id(type.getId())
                .title(type.getTitle())
                .build();
    }

    public DictDTO mapPermissions(DictPermissionType permission) {
        return DictDTO.builder()
                .id(permission.getId())
                .title(permission.getTitle())
                .build();
    }
}
