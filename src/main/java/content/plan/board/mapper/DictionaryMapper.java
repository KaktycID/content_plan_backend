package content.plan.board.mapper;

import content.plan.board.dto.DictionaryDTO;
import content.plan.board.repository.ContentTypeRepository;
import content.plan.board.repository.FieldRepository;
import content.plan.board.structure.FieldType;
import content.plan.board.structure.ContentType;
import content.plan.board.structure.PermissionType;
import content.plan.users.structure.Icons;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@RequiredArgsConstructor
@Component
public class DictionaryMapper {

    private final FieldRepository fieldTypes;
    private final ContentTypeRepository contentTypes;


    public static Timestamp getActualTime() {
        Instant instant = Instant.now();
        return Timestamp.from(instant);
    }

    public DictionaryDTO getFieldType(int typeId) {
        FieldType type = fieldTypes.findById(typeId).orElseThrow();
        return mapType(type);
    }

    public DictionaryDTO getContentType(int typeId) {
        ContentType type = contentTypes.findById(typeId).orElseThrow();
        return mapType(type);
    }

    public DictionaryDTO mapContentField(FieldType fieldType) {
        return DictionaryDTO.builder()
                .id(fieldType.getId())
                .title(fieldType.getTitle())
                .build();
    }

    public DictionaryDTO mapContent(ContentType type) {
        return DictionaryDTO.builder()
                .id(type.getId())
                .title(type.getTitle())
                .build();
    }

    public DictionaryDTO mapPermissions(PermissionType permission) {
        return DictionaryDTO.builder()
                .id(permission.getId())
                .title(permission.getTitle())
                .build();
    }

    public DictionaryDTO mapType(FieldType type) {
        return DictionaryDTO.builder()
                .id(type.getId())
                .title(type.getTitle())
                .build();
    }

    public DictionaryDTO mapType(ContentType type) {
        return DictionaryDTO.builder()
                .id(type.getId())
                .title(type.getTitle())
                .build();
    }

    public DictionaryDTO mapIcons(Icons icon) {
        return DictionaryDTO.builder()
                .id(icon.getId())
                .title(icon.getTitle())
                .build();
    }

    public DictionaryDTO mapPermissionTypes(PermissionType type) {
        return DictionaryDTO.builder()
                .id(type.getId())
                .title(type.getTitle())
                .build();
    }

    public PermissionType mapPermissionTypesToEntity(DictionaryDTO type) {
        PermissionType permissionType = new PermissionType();
        permissionType.setId(type.getId());
        permissionType.setTitle(type.getTitle());
        return permissionType;
    }

    public DictionaryDTO mapFieldTypeToDto(FieldType type) {
        return DictionaryDTO.builder()
                .id(type.getId())
                .title(type.getTitle())
                .build();
    }

    public FieldType mapFieldTypeToEntity(DictionaryDTO type) {
        FieldType fieldType = new FieldType();
        fieldType.setId(type.getId());
        fieldType.setTitle(type.getTitle());
        return fieldType;
    }

    public DictionaryDTO mapContentTypeToDto(ContentType type) {
        return DictionaryDTO.builder()
                .id(type.getId())
                .title(type.getTitle())
                .build();
    }

    public ContentType mapContentTypeToEntity(DictionaryDTO type) {
        ContentType contentType = new ContentType();
        contentType.setId(type.getId());
        contentType.setTitle(type.getTitle());
        return contentType;
    }
}
