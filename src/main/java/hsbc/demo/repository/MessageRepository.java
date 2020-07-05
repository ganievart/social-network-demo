package hsbc.demo.repository;

import hsbc.demo.cache.impl.CacheManagerImpl;
import hsbc.demo.dao.MessageDAO;
import hsbc.demo.entity.MessageEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class MessageRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheManagerImpl.class);

    private final MessageDAO messageDAO;

    public void createMessage(String username, String messageText) {
        LOGGER.trace("Create message for user={}", username);
        MessageEntity entity = new MessageEntity(username, messageText, OffsetDateTime.now());
        messageDAO.putMessageEntity(username, entity);
    }

    public List<MessageEntity> getMessages(Set<String> usernames) {
        LOGGER.trace("Get messages for users={}", usernames);
        return messageDAO.getMessageEntitiesSortedByTime(usernames);
    }
}
