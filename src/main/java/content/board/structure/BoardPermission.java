package content.board.structure;

import content.users.structure.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "board_permission", schema = "content_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardPermission extends ContentPlanAbstractBaseEntity{

    @Id
    @GeneratedValue(generator = "board_permission_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "board_permission_id_seq", sequenceName = "board_permission_id_seq", allocationSize = 1, schema = "content_plan")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", updatable = false, nullable = false)
    private Board boardId;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    private Users userId;

    @ManyToOne
    @JoinColumn(name = "permission_type", updatable = false, nullable = false)
    private PermissionType permissionType;

}
