package hsbc.demo.service.impl;

import hsbc.demo.repository.MessageRepository;
import hsbc.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class PostMessageServiceImpl implements hsbc.demo.service.PostMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetTimelineServiceImpl.class);

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public void execute(String username, String messageText) {
        LOGGER.debug("Create user with username={}", username);
        userRepository.createUserIfAbsent(username);

        LOGGER.debug("Create message for username={}", username);
        messageRepository.createMessage(username, messageText);
    }
}
