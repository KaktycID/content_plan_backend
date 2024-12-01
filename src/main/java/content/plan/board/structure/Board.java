package content.plan.board.structure;

import content.plan.users.structure.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "boards", schema = "content_plan")
@Getter
@Setter
public class Board extends ContentPlanAbstractBaseEntity{

    @Id
    @GeneratedValue(generator = "board_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private Users authorId;

    @Column(name = "name", nullable = false)
    private String name;

}
