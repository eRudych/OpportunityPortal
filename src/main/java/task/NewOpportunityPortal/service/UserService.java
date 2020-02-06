package task.NewOpportunityPortal.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import task.NewOpportunityPortal.entity.User;

import java.util.List;

public interface UserService {

    Long createUser(User user);

    User getUserById(Long userId);

    User getUserByLogin(String userLogin);

    User updateUser(User user);

    boolean removeUser(Long userId);

    List getAllAvailChats(Long userId);
}
