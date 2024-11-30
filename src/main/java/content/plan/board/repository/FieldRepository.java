package content.plan.board.repository;

import content.plan.board.structure.FieldType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<FieldType, Integer> {
}
