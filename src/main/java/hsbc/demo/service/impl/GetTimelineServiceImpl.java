package hsbc.demo.service.impl;

import hsbc.demo.entity.MessageEntity;
import hsbc.demo.entity.UserEntity;
import hsbc.demo.exception.UnavailableUserException;
import hsbc.demo.repository.MessageRepository;
import hsbc.demo.repository.UserRepository;
import hsbc.demo.service.GetTimelineService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetTimelineServiceImpl implements GetTimelineService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetTimelineServiceImpl.class);

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public List<MessageEntity> execute(String username) throws UnavailableUserException {
        LOGGER.debug("Get usernames for={}", username);
        Set<String> userNames = userRepository.getFollowers(username)
                                              .stream()
                                              .map(UserEntity::getName)
                                              .collect(Collectors.toSet());
        LOGGER.debug("Get messages for={}", userNames);
        return messageRepository.getMessages(userNames);
    }
}
