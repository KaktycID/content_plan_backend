package content.plan.board.structure;

import content.plan.users.structure.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "boards")
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(generator = "board_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private Users authorId;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    @Column(name = "create_date", updatable = false, nullable = false)
    private Instant createDate;

    @Column(name = "update_date", updatable = false, nullable = true)
    private Instant updateDate;

    @Column(name = "is_active", updatable = false, nullable = false)
    private boolean isActive;
}
