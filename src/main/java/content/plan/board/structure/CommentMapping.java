package content.plan.board.structure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment_mapping")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentMapping extends ContentPlanAbstractBaseEntity{

    @Id
    @GeneratedValue(generator = "comment_mapping_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "content_id", updatable = false, nullable = false)
    private Long contentId;

    @ManyToOne
    @JoinColumn(name = "comment_id", updatable = false, nullable = false)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "type_id", updatable = false, nullable = false)
    private int type;



}


