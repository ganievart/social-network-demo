package hsbc.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MessageDTO {

    @NotNull
    @Size(max = 140)
    private String message;
}
