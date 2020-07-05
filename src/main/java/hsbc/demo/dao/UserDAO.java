package hsbc.demo.dao;

import hsbc.demo.entity.UserEntity;

//TODO Add javadoc
public interface UserDAO {

    void putUserEntity(String username);

    UserEntity getUserEntity(String username);
}
