package content.plan.board.structure;

import content.plan.users.structure.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "board_permission")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardPermission {

    @Id
    @GeneratedValue(generator = "board_permission_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", updatable = false, nullable = false)
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "permission_type", updatable = false, nullable = false)
    private int permissionType;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    private Long userId;

    @Column(name = "is_active", updatable = false, nullable = false)
    private boolean active;
}
