package hsbc.demo.service;

import hsbc.demo.entity.MessageEntity;
import hsbc.demo.exception.UnavailableUserException;

import java.util.List;

//TODO Add javadoc
public interface GetTimelineService {

    List<MessageEntity> execute(String username) throws UnavailableUserException;
}
