package content.plan.board.structure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "boards")
@Getter
@Setter
public class Board extends ContentPlanAbstractBaseEntity{

    @Id
    @GeneratedValue(generator = "board_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private Long authorId;

    @Column(name = "name", nullable = false)
    private String name;

}
