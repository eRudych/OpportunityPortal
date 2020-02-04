package task.NewOpportunityPortal.service.impl;

import task.NewOpportunityPortal.entity.User;
import task.NewOpportunityPortal.repository.UserRepository;
import task.NewOpportunityPortal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public Long createUser(User user) {
        log.info("create user "+user.getId());
        return repository.createUser(user);
    }

    @Override
    public User getUserById(Long userId) {
        log.info("get user by id"+ userId);
        return repository.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String userLogin) {
        log.info("get user by login "+ userLogin);
        return repository.getUserByLogin(userLogin);
    }

    @Override
    public User updateUser(User user) {
        log.info("update user "+user.getId());
        return repository.updateUser(user);
    }

    @Override
    public boolean removeUser(Long userId) {
        log.info("remove user "+userId);
        return repository.removeUser(userId);
    }
}
