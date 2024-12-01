package content.plan.board.repository;

import content.plan.board.structure.BoardPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardPermissionRepository extends JpaRepository<BoardPermission, Long> {
}
