package hsbc.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FollowerDTO {

    @NotNull
    private String username;
}
