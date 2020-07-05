package IT;

import com.google.common.collect.Sets;
import hsbc.demo.cache.CacheManager;
import hsbc.demo.config.ApplicationConfig;
import hsbc.demo.dao.MessageDAO;
import hsbc.demo.entity.MessageEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class MessageDAOIT {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private MessageDAO messageDAO;

    @AfterEach
    void after() {
        cacheManager.clearCache();
    }

    @Test
    void willPutMessageEntityIfKeyAlreadyExist() {
        messageDAO.putMessageEntity("test", new MessageEntity("test", "msg", OffsetDateTime.now()));
        messageDAO.putMessageEntity("test", new MessageEntity("test", "msg", OffsetDateTime.now().plusDays(1)));
        assertEquals(2, messageDAO.getMessageEntitiesSortedByTime(Collections.singleton("test")).size());
    }

    @Test
    void willGetMessageEntitiesForEachProvidedExistingUsername() {
        populateMessageEntities("test1", OffsetDateTime.now());
        populateMessageEntities("test2", OffsetDateTime.now());
        populateMessageEntities("test3", OffsetDateTime.now());
        List<MessageEntity> messageEntities = messageDAO.getMessageEntitiesSortedByTime(Sets.newHashSet("test1",
                                                                                                        "test2",
                                                                                                        "test3",
                                                                                                        "null"));
        assertEquals(9, messageEntities.size(), "Must have 9 entities, 3 for each existing username");
    }

    @Test
    void willReturnMessageEntitiesInReverseChronologicalOrder() {
        OffsetDateTime offsetDateTimeNow = OffsetDateTime.now();
        populateMessageEntities("test", offsetDateTimeNow);
        List<MessageEntity> messageEntities = messageDAO.getMessageEntitiesSortedByTime(Collections.singleton("test"));

        assertFalse(messageEntities.isEmpty(), "Must not be empty");

        Iterator<MessageEntity> iterator = messageEntities.iterator();

        assertEquals(OffsetDateTime.MAX, iterator.next().getTime());
        assertEquals(offsetDateTimeNow, iterator.next().getTime());
        assertEquals(OffsetDateTime.MIN, iterator.next().getTime());
    }

    private void populateMessageEntities(String username, OffsetDateTime offsetDateTime) {
        messageDAO.putMessageEntity(username, new MessageEntity(username, "now", offsetDateTime));
        messageDAO.putMessageEntity(username, new MessageEntity(username, "max", OffsetDateTime.MAX));
        messageDAO.putMessageEntity(username, new MessageEntity(username, "min", OffsetDateTime.MIN));
    }
}
