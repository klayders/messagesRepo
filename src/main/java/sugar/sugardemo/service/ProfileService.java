package sugar.sugardemo.service;

import org.springframework.stereotype.Service;
import sugar.sugardemo.domain.User;
import sugar.sugardemo.domain.UserSubscription;
import sugar.sugardemo.repo.UserDetailsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
  private final UserDetailsRepository userDetailsRepository;


  public ProfileService(UserDetailsRepository userDetailsRepository) {
    this.userDetailsRepository = userDetailsRepository;
  }

  public User changeSubscription(User channel, User subscriber) {
    List<UserSubscription> subcriptions = channel.getSubscribers()
      .stream()
      .filter(subscription ->
        subscription.getSubscriber().equals(subscriber)
      )
      .collect(Collectors.toList());

    if (subcriptions.isEmpty()) {
      UserSubscription subscription = new UserSubscription(channel, subscriber);
      channel.getSubscribers().add(subscription);
    } else {
      channel.getSubscribers().removeAll(subcriptions);
    }

    return userDetailsRepository.save(channel);
  }
}
