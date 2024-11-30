package content.plan.board.mapper;

import content.plan.board.dto.DictDTO;
import content.plan.board.repository.ContentTypeRepository;
import content.plan.board.repository.FieldRepository;
import content.plan.board.structure.FieldType;
import content.plan.board.structure.ContentType;
import content.plan.board.structure.PermissionType;
import content.plan.users.structure.Icons;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class Mapper {

    private static FieldRepository fieldTypes;
    private static ContentTypeRepository contentTypes;


    public static Timestamp getActualTime() {
        Instant instant = Instant.now();
        return Timestamp.from(instant);
    }

    public static DictDTO getFieldType(int typeId) {
        FieldType type = fieldTypes.findById(typeId).orElseThrow();
        return mapType(type);
    }

    public static DictDTO getContentType(int typeId) {
        ContentType type = contentTypes.findById(typeId).orElseThrow();
        return mapType(type);
    }

    public static DictDTO mapContentField(FieldType fieldType) {
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

    public static DictDTO mapType(FieldType type) {
        return DictDTO.builder()
                .id(type.getId())
                .title(type.getTitle())
                .build();
    }

    public static DictDTO mapType(ContentType type) {
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
