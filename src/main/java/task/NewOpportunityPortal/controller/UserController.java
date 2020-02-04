package task.NewOpportunityPortal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.entity.User;
import task.NewOpportunityPortal.service.UserService;


@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long create(@RequestBody User user){
        return service.createUser(user);
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable("id") long id){
        return service.removeUser(id);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User update(@RequestBody User User){
        return service.updateUser(User);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") long id){
        return service.getUserById(id);
    }
}
