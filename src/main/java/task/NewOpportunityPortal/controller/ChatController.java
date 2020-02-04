package task.NewOpportunityPortal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.service.ChatService;


@RestController
@RequestMapping(value = "/chats")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long send(@RequestBody Chat chat){
        return service.createChat(chat);
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable("id") long id){
        return service.removeChat(id);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Chat update(@RequestBody Chat chat){
        return service.updateChat(chat);
    }

    @GetMapping("/{id}")
    public Chat get(@PathVariable("id") long id){
        return service.getChat(id);
    }

    @GetMapping("getMessages/{chatId}")
    public Chat getMessages(@PathVariable("chatId") long chatId){
        return service.getChat(chatId);
    }
}
