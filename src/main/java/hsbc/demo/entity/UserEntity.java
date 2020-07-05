package hsbc.demo.entity;

import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserEntity {

    @NonNull
    private final String name;
    private final Set<UserEntity> followers = new HashSet<>();

}
