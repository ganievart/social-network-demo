package hsbc.demo.cache;

import hsbc.demo.entity.MessageEntity;
import hsbc.demo.entity.UserEntity;

import java.util.Map;
import java.util.Set;

//TODO Add javadoc
public interface CacheManager {

    Map<String, Set<MessageEntity>> getMessageEntitiesCache();

    Map<String, UserEntity> getUserEntityCache();

    void clearCache();
}
