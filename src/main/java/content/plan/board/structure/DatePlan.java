package content.plan.board.structure;

import content.plan.users.structure.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "date_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DatePlan extends ContentPlanAbstractBaseEntity{

    @Id
    @GeneratedValue(generator = "date_plan_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", updatable = false, nullable = false)
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private Long authorId;

    @Column(name = "planned_date", updatable = false, nullable = false)
    private Timestamp plannedDate;

}
