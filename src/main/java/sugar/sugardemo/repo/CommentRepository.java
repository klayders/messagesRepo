package sugar.sugardemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sugar.sugardemo.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
