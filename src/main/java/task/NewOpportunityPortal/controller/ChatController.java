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
        log.info("Send chat {}", chat.toString());
        return service.createChat(chat);
    }

    @DeleteMapping("/{chatId}")
    public boolean remove(@PathVariable("chatId") long chatId){
        log.info("Remove chat {}", chatId);
        return service.removeChat(chatId);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Chat update(@RequestBody Chat chat){
        log.info("Update chat {}", chat.toString());
        return service.updateChat(chat);
    }

    @GetMapping("/{chatId}")
    public Chat get(@PathVariable("chatId") long chatId){
        log.info("Get chat {}", chatId);
        return service.getChat(chatId);
    }

    @GetMapping("getMessages/{chatId}")
    public Chat getMessages(@PathVariable("chatId") long chatId){
        log.info("Get messages from chat {}", chatId);
        return service.getChat(chatId);
    }
}
