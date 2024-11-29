package content.plan.board.mapper;

import content.plan.board.dto.DictDTO;
import content.plan.board.structure.ContentField;
import content.plan.board.structure.ContentType;
import content.plan.board.structure.PermissionType;
import content.plan.board.structure.Types;
import content.plan.users.structure.Icons;
import org.springframework.stereotype.Component;

@Component
public class DictDTOMapper {

    public static DictDTO mapContentField(ContentField fieldType) {
        return DictDTO.builder()
                .id(fieldType.getId())
                .title(fieldType.getTitle())
                .build();
    }

    public static DictDTO mapContent(ContentType type) {
        return DictDTO.builder()
                .id(type.getId())
                .title(type.getTitle())
                .build();
    }

    public static DictDTO mapPermissions(PermissionType permission) {
        return DictDTO.builder()
                .id(permission.getId())
                .title(permission.getTitle())
                .build();
    }

    public static DictDTO mapType(Types type) {
        return DictDTO.builder()
                .id(type.getId())
                .title(type.getTitle())
                .build();
    }

    public static DictDTO mapIcons(Icons icon) {
        return DictDTO.builder()
                .id(icon.getId())
                .title(icon.getTitle())
                .build();
    }
}
