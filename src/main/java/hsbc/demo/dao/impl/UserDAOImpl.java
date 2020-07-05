package hsbc.demo.dao.impl;

import hsbc.demo.cache.CacheManager;
import hsbc.demo.dao.UserDAO;
import hsbc.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    private CacheManager cacheManager;

    @Override
    public void putUserEntity(final String username) {
        LOGGER.trace("Put UserEntity with username={}", username);
        cacheManager.getUserEntityCache().computeIfAbsent(username, key -> new UserEntity(username));
    }

    @Override
    public UserEntity getUserEntity(final String username) {
        LOGGER.trace("Get UserEntity with username={}", username);
        return cacheManager.getUserEntityCache().get(username);
    }
}
