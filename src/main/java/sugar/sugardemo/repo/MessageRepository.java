package sugar.sugardemo.repo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import sugar.sugardemo.domain.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
  @EntityGraph(attributePaths = {"comments"})
  List<Message> findAll();
}
