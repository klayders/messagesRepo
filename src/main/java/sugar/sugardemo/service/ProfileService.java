package sugar.sugardemo.service;

import org.springframework.stereotype.Service;
import sugar.sugardemo.domain.User;
import sugar.sugardemo.domain.UserSubscription;
import sugar.sugardemo.repo.UserDetailsRepository;
import sugar.sugardemo.repo.UserSubscriptionsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
  private final UserDetailsRepository userDetailsRepository;
  private final UserSubscriptionsRepository userSubscriptionsRepository;

  public ProfileService(
    UserDetailsRepository userDetailsRepository,
    UserSubscriptionsRepository userSubscriptionsRepository
  ) {
    this.userDetailsRepository = userDetailsRepository;
    this.userSubscriptionsRepository = userSubscriptionsRepository;
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

  public List<UserSubscription> getSubscribers(User channel) {
    return userSubscriptionsRepository.findByChannel(channel);
  }

  public UserSubscription changeSubscriptionStatus(User channel, User subscriber) {
    UserSubscription subscription = userSubscriptionsRepository.findByChannelAndSubscriber(channel, subscriber);
    subscription.setActive(!subscription.isActive());

    return userSubscriptionsRepository.save(subscription);
  }
}
