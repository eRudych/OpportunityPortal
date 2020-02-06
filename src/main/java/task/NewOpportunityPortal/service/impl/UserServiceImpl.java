package task.NewOpportunityPortal.service.impl;

import task.NewOpportunityPortal.entity.User;
import task.NewOpportunityPortal.repository.UserRepository;
import task.NewOpportunityPortal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public Long createUser(User user) {
        Date now = new java.util.Date();
        log.info("Set time creates: {}",  now);
        user.setCreateAt(new java.sql.Timestamp(now.getTime()));
        log.info("Create user: {}", user.getId());
        return repository.createUser(user);
    }

    @Override
    public User getUserById(Long userId) {
        log.info("Get user by id"+ userId);
        return repository.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String userLogin) {
        log.info("Get user by login: {}",  userLogin);
        return repository.getUserByLogin(userLogin);
    }

    @Override
    public User updateUser(User user) {
        log.info("Update user: {}", user.getId());
        return repository.updateUser(user);
    }

    @Override
    public boolean removeUser(Long userId) {
        log.info("Remove user: {}", userId);
        return repository.removeUser(userId);
    }
}
