package task.NewOpportunityPortal.service;

import task.NewOpportunityPortal.entity.User;

public interface UserService {

    Long createUser(User user);

    User getUserById(Long userId);

    User getUserByLogin(String userLogin);

    User updateUser(User user);

    boolean removeUser(Long userId);
}
