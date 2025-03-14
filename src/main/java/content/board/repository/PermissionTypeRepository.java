package content.board.repository;

import content.board.structure.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionTypeRepository extends JpaRepository<PermissionType, Integer> {
}
