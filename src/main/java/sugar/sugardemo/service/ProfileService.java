package sugar.sugardemo.service;

import org.springframework.stereotype.Service;
import sugar.sugardemo.domain.User;
import sugar.sugardemo.repo.UserDetailsRepository;

import java.util.Set;

@Service
public class ProfileService {
  private final UserDetailsRepository userDetailsRepository;


  public ProfileService(UserDetailsRepository userDetailsRepository) {
    this.userDetailsRepository = userDetailsRepository;
  }

  public User changeSubscription(User channel, User subscriber) {
    Set<User> subscribers = channel.getSubscribers();

    if (subscribers.contains(subscriber)) {
      subscribers.remove(subscriber);
    } else {
      subscribers.add(subscriber);
    }

    return userDetailsRepository.save(channel);
  }
}
