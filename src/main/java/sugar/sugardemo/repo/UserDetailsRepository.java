package sugar.sugardemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sugar.sugardemo.domain.User;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
