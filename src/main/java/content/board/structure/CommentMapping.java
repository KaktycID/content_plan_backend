package content.board.structure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment_mapping", schema = "content_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentMapping extends ContentPlanAbstractBaseEntity{

    @Id
    @GeneratedValue(generator = "comment_mapping_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "comment_mapping_id_seq", sequenceName = "comment_mapping_id_seq", allocationSize = 1, schema = "content_plan")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "content_id", updatable = false, nullable = false)
    private Content fieldId;

    @ManyToOne
    @JoinColumn(name = "comment_id", updatable = false, nullable = false)
    private Comment commentId;

    @ManyToOne
    @JoinColumn(name = "type_id", updatable = false, nullable = false)
    private FieldType type;



}


