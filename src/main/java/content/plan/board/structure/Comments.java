package content.plan.board.structure;

import content.plan.users.structure.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comments extends ContentPlanAbstractBaseEntity{

    @Id
    @GeneratedValue(generator = "comment_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "content_id", updatable = false, nullable = false)
    private Content contentId;

    @ManyToOne
    @JoinColumn(name = "content_field_id", updatable = false, nullable = false)
    private DictContentFieldType contentFieldId;

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private Users authorId;

    @Column(name = "comment", updatable = false, nullable = false)
    private String comment;
}
