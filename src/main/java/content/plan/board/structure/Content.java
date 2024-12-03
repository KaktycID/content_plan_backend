package content.plan.board.structure;

import content.plan.users.structure.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "content", schema = "content_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Content extends ContentPlanAbstractBaseEntity{

    @Id
    @GeneratedValue(generator = "content_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "content_id_seq", sequenceName = "content_id_seq", allocationSize = 1, schema = "content_plan")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "date_plan_id", updatable = false, nullable = false)
    private DatePlan datePlanId;

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private Users authorId;

    @ManyToOne
    @JoinColumn(name = "type_id", updatable = false, nullable = false)
    private ContentType typeId;

    @Column(name = "title", updatable = false, nullable = false)
    private String title;

    @Column(name = "content_file", updatable = false, nullable = false)
    private String contentFile;

    @Column(name = "description", updatable = false, nullable = false)
    private String description;

    @Column(name = "hashtags", updatable = false, nullable = false)
    private String hashtags;

    @Column(name = "audio", updatable = false, nullable = false)
    private String audio;

}
