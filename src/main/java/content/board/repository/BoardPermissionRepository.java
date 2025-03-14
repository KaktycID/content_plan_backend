package content.board.repository;

import content.board.structure.BoardPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardPermissionRepository extends JpaRepository<BoardPermission, Long> {
}
