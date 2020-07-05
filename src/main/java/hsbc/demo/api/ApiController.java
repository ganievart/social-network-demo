package hsbc.demo.api;

import hsbc.demo.dto.FollowerDTO;
import hsbc.demo.dto.MessageDTO;
import hsbc.demo.entity.MessageEntity;
import hsbc.demo.exception.UnavailableUserException;
import hsbc.demo.service.FollowService;
import hsbc.demo.service.GetTimelineService;
import hsbc.demo.service.GetWallService;
import hsbc.demo.service.PostMessageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

//TODO Add javadoc
@RestController
@RequestMapping(value = "api/{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    private final PostMessageService postMessageService;
    private final GetWallService getWallService;
    private final FollowService followService;
    private final GetTimelineService getTimelineService;

    @PutMapping(path = "/post", consumes = "application/json")
    public void postMessage(@PathVariable String username,
                            @RequestBody @Valid MessageDTO requestDTO) {
        //TODO Implement ExceptionHandler to handle @Valid constraints

        LOGGER.info("Execute /post for username={}", username);
        postMessageService.execute(username, requestDTO.getMessage());
    }

    @GetMapping(path = "/wall")
    public List<MessageEntity> getWall(@PathVariable String username) {
        LOGGER.info("Execute /wall for username={}", username);
        return getWallService.execute(username);
    }

    @PostMapping(path = "/follow")
    public void setFollow(@PathVariable String username,
                          @RequestBody @Valid FollowerDTO followerDTO) throws UnavailableUserException {
        LOGGER.info("Execute /follow for username={}", username);
        followService.execute(username, followerDTO.getUsername());
    }

    @GetMapping(path = "/timeline")
    public List<MessageEntity> getTimeline(@PathVariable String username) throws UnavailableUserException {
        LOGGER.info("Execute /timeline for username={}", username);
        return getTimelineService.execute(username);
    }
}
