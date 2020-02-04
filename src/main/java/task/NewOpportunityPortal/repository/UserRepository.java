package task.NewOpportunityPortal.repository;

import task.NewOpportunityPortal.entity.User;

public interface UserRepository {

    Long createUser(User user);

    User getUserById(Long userId);

    User getUserByLogin(String userLogin);

    User updateUser(User user);

    boolean removeUser(Long userId);
}