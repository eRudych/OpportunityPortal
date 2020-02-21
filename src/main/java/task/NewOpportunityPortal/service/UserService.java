package task.NewOpportunityPortal.service;

import org.springframework.security.core.userdetails.UserDetails;
import task.NewOpportunityPortal.entity.User;

import java.util.List;

public interface UserService {

    Long createUser(User user);

    User getUserById(Long userId);

    User getUserByLogin(String userLogin);

    User updateUser(User user);

    void removeUser(Long userId);

    List<Long> getAllAvailChats(Long userId);

    UserDetails loadUserByUsername(String login);
}
