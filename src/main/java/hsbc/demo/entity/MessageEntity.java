package hsbc.demo.entity;

import lombok.Data;
import lombok.NonNull;

import java.time.OffsetDateTime;

@Data
public class MessageEntity {

    @NonNull
    private final String username;
    @NonNull
    private final String messageText;
    @NonNull
    private final OffsetDateTime time;
}
