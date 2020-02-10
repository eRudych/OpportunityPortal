package task.NewOpportunityPortal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.entity.User;
import task.NewOpportunityPortal.service.UserService;

import java.util.List;


@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long create(@RequestBody User user){
        log.info("Create user {}", user.toString());
        return service.createUser(user);
    }

    @DeleteMapping("/{userId}")
    public boolean remove(@PathVariable("userId") long userId){
        log.info("Remove user {}", userId);
        return service.removeUser(userId);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User update(@RequestBody User user){
        log.info("Update user {}", user.toString());
        return service.updateUser(user);
    }

    @GetMapping("/{userId}")
    public User get(@PathVariable("userId") long userId){
        log.info("Get user {}", userId);
        return service.getUserById(userId);
    }

    @GetMapping("/{userId}/chats")
    public List getAllAvailChats(@PathVariable("userId") long userId){
        log.info("Get user {}", userId);
        return service.getAllAvailChats(userId);
    }
}
