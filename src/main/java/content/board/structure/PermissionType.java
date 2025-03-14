package content.board.structure;

import content.board.structure.enums.BoardPermissionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dict_permission_type", schema = "content_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PermissionType {

    @Id
    @GeneratedValue(generator = "dict_permission_type_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "dict_permission_type_id_seq", sequenceName = "dict_permission_type_id_seq", allocationSize = 1, schema = "content_plan")
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "title", updatable = false, nullable = false)
    private String title;

    @Column(name = "code")
    @Enumerated(EnumType.STRING)
    private BoardPermissionEnum code;
}
