package sugar.sugardemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sugar.sugardemo.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
