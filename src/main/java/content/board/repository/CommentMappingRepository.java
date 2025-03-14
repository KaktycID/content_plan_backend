package content.board.repository;

import content.board.structure.CommentMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMappingRepository extends JpaRepository<CommentMapping, Long> {


}
