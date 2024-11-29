package content.plan.board.repository;

import content.plan.board.structure.Types;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Types, Integer> {
}
