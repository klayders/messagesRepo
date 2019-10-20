package sugar.sugardemo.repo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import sugar.sugardemo.domain.User;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<User, String> {
  @EntityGraph(attributePaths = {"subscriptions", "subscribers"})
  Optional<User> findById(String s);
}
