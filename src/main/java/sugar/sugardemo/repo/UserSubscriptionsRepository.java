package sugar.sugardemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sugar.sugardemo.domain.User;
import sugar.sugardemo.domain.UserSubscription;
import sugar.sugardemo.domain.UserSubscriptionId;

import java.util.List;

public interface UserSubscriptionsRepository extends JpaRepository<UserSubscription, UserSubscriptionId> {
  List<UserSubscription> findBySubscriber(User user);
}
