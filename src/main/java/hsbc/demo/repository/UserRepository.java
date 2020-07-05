package hsbc.demo.repository;

import hsbc.demo.cache.impl.CacheManagerImpl;
import hsbc.demo.dao.UserDAO;
import hsbc.demo.entity.UserEntity;
import hsbc.demo.exception.UnavailableUserException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheManagerImpl.class);

    private final UserDAO userDAO;

    public void createUserIfAbsent(String username) {
        userDAO.putUserEntity(username);
    }

    public void addFollower(String follower, String following) throws UnavailableUserException {
        LOGGER.trace("For follower={} add following={}", follower, following);
        getFollowers(follower).add(getUserByName(following));
    }

    public Set<UserEntity> getFollowers(String userName) throws UnavailableUserException {
        LOGGER.trace("Get followers for username={}", userName);
        return getUserByName(userName).getFollowers();

    }

    private UserEntity getUserByName(String userName) throws UnavailableUserException {
        LOGGER.trace("Get userEntity by userName={}", userName);
        return Optional.ofNullable(userDAO.getUserEntity(userName))
                       .orElseThrow(() -> new UnavailableUserException(String.format("User=%s does not exist",
                                                                                     userName)));
    }
}
