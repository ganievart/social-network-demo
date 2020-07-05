package e2e;

import hsbc.demo.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class GetTimelineE2E {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void willGetTimelineForFollowedUsers() throws Exception {
        postMessage("timelineUser1");
        postMessage("timelineUser2");

        mockMvc.perform(post("/api/{username}/follow", "timelineUser1")
                                .content("{\"username\":\"timelineUser2\"}")
                                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/api/{username}/timeline", "timelineUser1")
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().string(containsString(
                       "username\":\"timelineUser2\",\"messageText\":\"test timeline message\"")));
    }

    private void postMessage(String user) throws Exception {
        mockMvc.perform(put("/api/{username}/post", user)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"message\":\"test timeline message\"}"));
    }
}
