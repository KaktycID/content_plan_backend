package content.plan.board.repository;

import content.plan.board.structure.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionTypeRepository extends JpaRepository<PermissionType, Integer> {
}
