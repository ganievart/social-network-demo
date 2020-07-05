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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class GetWallE2E {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void willGetWallAndReceive200OK() throws Exception {
        mockMvc.perform(put("/api/{username}/post", "walltest")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"message\":\"test message\"}"));

        mockMvc.perform(get("/api/{username}/wall", "walltest")
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().string(containsString("test message")));
    }
}
