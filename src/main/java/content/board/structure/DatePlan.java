package content.board.structure;

import content.users.structure.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "date_plan", schema = "content_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DatePlan extends ContentPlanAbstractBaseEntity{

    @Id
    @GeneratedValue(generator = "date_plan_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "date_plan_id_seq", sequenceName = "date_plan_id_seq", allocationSize = 1, schema = "content_plan")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", updatable = false, nullable = false)
    private Board boardId;

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private Users authorId;

    @Column(name = "planned_date", updatable = false, nullable = false)
    private Timestamp plannedDate;

}
