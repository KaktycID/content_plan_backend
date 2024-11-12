package content.plan.board.structure;

import content.plan.users.structure.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "content")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Content {

    @Id
    @GeneratedValue(generator = "content_seq", strategy = GenerationType.SEQUENCE)
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
    private DictContentType typeId;

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

    @Column(name = "create_date", updatable = false, nullable = false)
    private Instant createDate;

    @Column(name = "update_date", updatable = false, nullable = true)
    private Instant updateDate;

    @Column(name = "is_active", updatable = false, nullable = false)
    private boolean isActive;
}
