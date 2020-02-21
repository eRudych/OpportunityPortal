package task.NewOpportunityPortal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.ability.entity.CommentAbility;
import task.NewOpportunityPortal.ability.entity.UserAbility;
import task.NewOpportunityPortal.ability.service.CommentAbilityService;
import task.NewOpportunityPortal.ability.service.UserAbilityService;
import task.NewOpportunityPortal.entity.User;
import task.NewOpportunityPortal.service.UserService;

import java.util.List;


@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserAbilityService userAbilityService;
    private final CommentAbilityService commentAbilityService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long createUser(@RequestBody User user) {
        log.info("Create user {}", user.toString());
        return userService.createUser(user);
    }

    @DeleteMapping("/{userId}")
    public void removeUser(@PathVariable("userId") long userId) {
        log.info("Remove user {}", userId);
        userService.removeUser(userId);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User updateUser(@RequestBody User user) {
        log.info("Update user {}", user.toString());
        return userService.updateUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") long userId) {
        log.info("Get user {}", userId);
        return userService.getUserById(userId);
    }

    @GetMapping("/{userId}/chats")
    public List<Long> getAllAvailChats(@PathVariable("userId") long userId) {
        log.info("Get user {}", userId);
        return userService.getAllAvailChats(userId);
    }

    @PostMapping(value = "/abilities", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long createUserAbilityRate(@RequestBody UserAbility userAbility) {
        log.info("Create user ability rate {}", userAbility.toString());
        return userAbilityService.createUserAbilityRate(userAbility);
    }

    @DeleteMapping("/abilities/{userAbilityId}")
    public void removeUserAbilityRate(@PathVariable("userAbilityId") long userAbilityId) {
        log.info("Remove user ability rate {}", userAbilityId);
        userAbilityService.removeUserAbilityRate(userAbilityId);
    }

    @PutMapping(value = "/abilities", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserAbility updateUserAbilityRate(@RequestBody UserAbility userAbility) {
        log.info("Update user ability rate {}", userAbility.toString());
        return userAbilityService.updateUserAbilityRate(userAbility);
    }

    @GetMapping("/abilities/{userAbilityId}")
    public UserAbility getUserAbilityRate(@PathVariable("userAbilityId") long userAbilityId) {
        log.info("Get user ability rate {}", userAbilityId);
        return userAbilityService.getUserAbilityRate(userAbilityId);
    }

    @GetMapping("/{userId}/abilities")
    public List<Long> getAllAbilityRate(@PathVariable("userId") long userId) {
        log.info("Get all ability rate for user {}", userId);
        return userAbilityService.getAllAbilitiesRate(userId);
    }

    @PostMapping(value = "/abilities/comments", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long createCommentAbilityRate(@RequestBody CommentAbility comment) {
        log.info("Create comment ability rate {}", comment.toString());
        return commentAbilityService.createCommentAbilityRate(comment);
    }

    @DeleteMapping("/abilities/comments/{commentId}")
    public void removeCommentAbilityRate(@PathVariable("commentId") long commentId) {
        log.info("Remove comment ability rate {}", commentId);
        commentAbilityService.removeCommentAbilityRate(commentId);
    }

    @PutMapping(value = "/abilities/comments", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CommentAbility updateCommentAbilityRate(@RequestBody CommentAbility comment) {
        log.info("Update comment ability rate {}", comment.toString());
        return commentAbilityService.updateCommentAbilityRate(comment);
    }

    @GetMapping("/abilities/comments/{commentId}")
    public CommentAbility getCommentAbilityRate(@PathVariable("commentId") long commentId) {
        log.info("Get comment ability rate {}", commentId);
        return commentAbilityService.getCommentAbilityRate(commentId);
    }
}
