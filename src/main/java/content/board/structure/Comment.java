package content.board.structure;

import content.users.structure.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments", schema = "content_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment extends ContentPlanAbstractBaseEntity{

    @Id
    @GeneratedValue(generator = "comment_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", allocationSize = 1, schema = "content_plan")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private Users authorId;

    @Column(name = "comment", nullable = false)
    private String comment;
}
