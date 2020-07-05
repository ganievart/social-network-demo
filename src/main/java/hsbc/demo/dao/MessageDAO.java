package hsbc.demo.dao;

import hsbc.demo.entity.MessageEntity;

import java.util.List;
import java.util.Set;

//TODO Add javadoc
public interface MessageDAO {

    void putMessageEntity(String username, MessageEntity entity);

    List<MessageEntity> getMessageEntitiesSortedByTime(Set<String> usernames);
}
