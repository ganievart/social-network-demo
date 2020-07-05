package hsbc.demo.repository;

import hsbc.demo.dao.UserDAO;
import hsbc.demo.exception.UnavailableUserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserRepositoryTest {

    @Mock
    private UserDAO userDAO;

    private UserRepository userRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        userRepository = new UserRepository(userDAO);
    }

    @Test
    void willThrowExceptionIfUserNotFound() {
        when(userDAO.getUserEntity(any())).thenReturn(null);
        Assertions.assertThrows(UnavailableUserException.class, () ->
                userRepository.getFollowers("test"));

    }
}
