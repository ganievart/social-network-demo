package hsbc.demo.service;

import hsbc.demo.exception.UnavailableUserException;

//TODO Add javadoc
public interface FollowService {

    void execute(String follower, String following) throws UnavailableUserException;

}
