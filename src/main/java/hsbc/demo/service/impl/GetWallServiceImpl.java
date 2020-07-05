package hsbc.demo.service.impl;

import hsbc.demo.entity.MessageEntity;
import hsbc.demo.repository.MessageRepository;
import hsbc.demo.service.GetWallService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class GetWallServiceImpl implements GetWallService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetTimelineServiceImpl.class);

    private final MessageRepository messageRepository;

    public List<MessageEntity> execute(String username) {
        LOGGER.debug("Get messages for username={}", username);
        return messageRepository.getMessages(Collections.singleton(username));
    }
}
