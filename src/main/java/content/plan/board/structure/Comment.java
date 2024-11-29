package content.plan.board.structure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment extends ContentPlanAbstractBaseEntity{

    @Id
    @GeneratedValue(generator = "comment_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private Long authorId;

    @Column(name = "comment", nullable = false)
    private String comment;
}
