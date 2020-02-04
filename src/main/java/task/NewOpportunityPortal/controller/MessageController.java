package task.NewOpportunityPortal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.service.MessageService;

@RestController
@RequestMapping(value = "/messages")
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long send(@RequestBody Message message){
        return service.createMessage(message);
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable("id") long id){
        return service.removeMessage(id);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Message update(@RequestBody Message message){
        return service.updateMessage(message);
    }

    @GetMapping("/{id}")
    public Message get(@PathVariable("id") long id){
        return service.getMessage(id);
    }
}
