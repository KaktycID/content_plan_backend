package content.plan.users.repository;

import content.plan.users.structure.Icons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IconsRepository extends JpaRepository<Icons, Integer> {

}
