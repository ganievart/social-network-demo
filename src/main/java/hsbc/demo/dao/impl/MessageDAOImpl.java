package hsbc.demo.dao.impl;

import com.google.common.collect.Sets;
import hsbc.demo.cache.CacheManager;
import hsbc.demo.dao.MessageDAO;
import hsbc.demo.entity.MessageEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MessageDAOImpl implements MessageDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDAOImpl.class);

    private final CacheManager cacheManager;

    @Override
    public void putMessageEntity(final String username, final MessageEntity entity) {
        LOGGER.trace("Post MessageEntity by username={}", username);
        cacheManager.getMessageEntitiesCache().computeIfAbsent(username, key -> Sets.newHashSet(entity))
                    .add(entity);
    }

    @Override
    public List<MessageEntity> getMessageEntitiesSortedByTime(final Set<String> usernames) {
        LOGGER.trace("Get messageEntities for usernames={}", usernames);
        return Optional.ofNullable(usernames)
                       .map(users -> users.stream()
                                          .map(e -> cacheManager.getMessageEntitiesCache().get(e))
                                          .filter(Objects::nonNull)
                                          .flatMap(Collection::stream)
                                          .sorted((o1, o2) -> o2.getTime().compareTo(o1.getTime()))
                                          .collect(Collectors.toList()))
                       .orElse(Collections.emptyList());
    }
}
