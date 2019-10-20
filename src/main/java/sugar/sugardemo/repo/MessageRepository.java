package sugar.sugardemo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import sugar.sugardemo.domain.Message;
import sugar.sugardemo.domain.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
  @EntityGraph(attributePaths = {"comments"})
  Page<Message> findByAuthorIn(List<User> channels, Pageable pageable);
}
