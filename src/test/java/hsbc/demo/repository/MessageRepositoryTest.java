package hsbc.demo.repository;

import com.google.common.collect.Sets;
import hsbc.demo.dao.MessageDAO;
import hsbc.demo.entity.MessageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

class MessageRepositoryTest {

    @Mock
    private MessageDAO messageDAO;

    private MessageRepository messageRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        messageRepository = new MessageRepository(messageDAO);
    }

    @Test
    void willInvokeCacheManagerWithInputParamsDuringCreation() {
        messageRepository.createMessage("test", "text");
        verify(messageDAO).putMessageEntity(eq("test"), any(MessageEntity.class));
    }

    @Test
    void willInvokeCacheManagerDuringGetElements() {
        messageRepository.getMessages(Sets.newHashSet("test1", "test2"));
        verify(messageDAO).getMessageEntitiesSortedByTime(anySet());
    }
}
