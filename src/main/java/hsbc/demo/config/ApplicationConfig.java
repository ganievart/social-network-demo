package hsbc.demo.config;

import hsbc.demo.cache.CacheManager;
import hsbc.demo.cache.impl.CacheManagerImpl;
import hsbc.demo.dao.MessageDAO;
import hsbc.demo.dao.UserDAO;
import hsbc.demo.dao.impl.MessageDAOImpl;
import hsbc.demo.dao.impl.UserDAOImpl;
import hsbc.demo.repository.MessageRepository;
import hsbc.demo.repository.UserRepository;
import hsbc.demo.service.FollowService;
import hsbc.demo.service.GetTimelineService;
import hsbc.demo.service.GetWallService;
import hsbc.demo.service.PostMessageService;
import hsbc.demo.service.impl.FollowServiceImpl;
import hsbc.demo.service.impl.GetTimelineServiceImpl;
import hsbc.demo.service.impl.GetWallServiceImpl;
import hsbc.demo.service.impl.PostMessageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CacheManager cacheManager() {
        return new CacheManagerImpl();
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAOImpl(cacheManager());
    }

    @Bean
    public MessageDAO messageDAO() {
        return new MessageDAOImpl(cacheManager());
    }

    @Bean
    public MessageRepository messageRepository(MessageDAO messageDAO) {
        return new MessageRepository(messageDAO);
    }

    @Bean
    public UserRepository userRepository(UserDAO userDAO) {
        return new UserRepository(userDAO);
    }

    @Bean
    public FollowService followService(UserRepository userRepository) {
        return new FollowServiceImpl(userRepository);
    }

    @Bean
    public GetTimelineService getTimelineService(MessageRepository messageRepository,
                                                 UserRepository userRepository) {
        return new GetTimelineServiceImpl(messageRepository, userRepository);
    }

    @Bean
    public GetWallService getWallService(MessageRepository messageRepository) {
        return new GetWallServiceImpl(messageRepository);
    }

    @Bean
    public PostMessageService postMessageService(UserRepository userRepository,
                                                 MessageRepository messageRepository) {
        return new PostMessageServiceImpl(userRepository, messageRepository);
    }
}
