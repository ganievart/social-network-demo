package hsbc.demo.service;

import hsbc.demo.entity.MessageEntity;

import java.util.List;
import java.util.Set;

//TODO Add javadoc
public interface GetWallService {

    List<MessageEntity> execute(String username);

}
