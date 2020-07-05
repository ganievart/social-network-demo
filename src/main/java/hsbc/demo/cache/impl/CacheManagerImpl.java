package hsbc.demo.cache.impl;

import hsbc.demo.cache.CacheManager;
import hsbc.demo.entity.MessageEntity;
import hsbc.demo.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CacheManagerImpl implements CacheManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheManagerImpl.class);

    private static final Map<String, Set<MessageEntity>> MESSAGE_ENTITY_CACHE = new HashMap<>();
    private static final Map<String, UserEntity> USER_ENTITY_CACHE = new HashMap<>();

    @Override
    public Map<String, Set<MessageEntity>> getMessageEntitiesCache() {
        LOGGER.trace("Get MessageEntityCache");
        return MESSAGE_ENTITY_CACHE;
    }

    @Override
    public Map<String, UserEntity> getUserEntityCache() {
        LOGGER.trace("Get UserEntityCache");
        return USER_ENTITY_CACHE;
    }

    @Override
    public void clearCache() {
        MESSAGE_ENTITY_CACHE.clear();
        USER_ENTITY_CACHE.clear();
        LOGGER.info("Cache is completely cleared");
    }
}
