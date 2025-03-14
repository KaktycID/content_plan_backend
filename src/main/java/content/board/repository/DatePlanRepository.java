package content.board.repository;

import content.board.structure.DatePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatePlanRepository extends JpaRepository<DatePlan, Long> {
}
