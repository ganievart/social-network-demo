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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class FollowE2E {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void willPostMessageAndReceive200OK() throws Exception {
        postMessage("user1");
        postMessage("user2");

        mockMvc.perform(post("/api/{username}/follow", "user1")
                                .content("{\"username\":\"user2\"}")
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }

    private void postMessage(String user) throws Exception {
        mockMvc.perform(put("/api/{username}/post", user)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"message\":\"test message\"}"));
    }
}
