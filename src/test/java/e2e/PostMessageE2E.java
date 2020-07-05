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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class PostMessageE2E {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void willPostMessageAndReceive200OK() throws Exception {
        mockMvc.perform(put("/api/{username}/post", "posttest")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"message\":\"test message\"}"))
               .andExpect(status().isOk());
    }

    @Test
    void willThrowExceptionForMessageWithMoreThan140Chars() throws Exception {
        String messageWithMoreThan140Chars = "ajshdjashdjkahsdjhaskjhdajhdkasldkaj"
                                             + "shdjashdjkahsdjhaskjhdajhsdjkahsdkas"
                                             + "ldkajshdjashdjkahsdjhaskjhdajhsdjkahs"
                                             + "dkasldkajshdjashdjkahsdjhaskjhdajhsdjkahsdkasldk";
        mockMvc.perform(put("/api/{username}/post", "posttest")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(String.format("{\"message\":\"%s\"}", messageWithMoreThan140Chars)))
               .andExpect(status().isBadRequest());
    }

}
