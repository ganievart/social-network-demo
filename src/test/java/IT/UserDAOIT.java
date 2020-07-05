package IT;

import hsbc.demo.cache.CacheManager;
import hsbc.demo.config.ApplicationConfig;
import hsbc.demo.dao.UserDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
class UserDAOIT {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private UserDAO userDAO;

    @Test
    void willCreateUserEntityIfNotExist() {
        userDAO.putUserEntity("test");
        assertNotNull(userDAO.getUserEntity("test"), "Must have value");
        assertEquals("test", userDAO.getUserEntity("test").getName());
    }
}
