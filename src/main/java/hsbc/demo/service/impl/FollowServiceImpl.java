package hsbc.demo.service.impl;

import hsbc.demo.cache.impl.CacheManagerImpl;
import hsbc.demo.exception.UnavailableUserException;
import hsbc.demo.repository.UserRepository;
import hsbc.demo.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FollowServiceImpl.class);

    private final UserRepository userRepository;

    public void execute(String follower, String following) throws UnavailableUserException {
        LOGGER.debug("For follower={} add following={}", follower, following);
        userRepository.addFollower(follower, following);
    }
}
