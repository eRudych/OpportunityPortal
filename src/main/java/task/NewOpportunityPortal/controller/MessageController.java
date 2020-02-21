package task.NewOpportunityPortal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.service.MessageService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@RestController
@RequestMapping(value = "/messages")
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long send(@RequestBody Message message) {
        log.info("Send message {}", message.toString());
        return service.createMessage(message);
    }

    @DeleteMapping("/{messageId}")
    public void remove(@PathVariable("messageId") long messageId) {
        log.info("Remove message {}", messageId);
        service.removeMessage(messageId);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Message update(@RequestBody Message message) {
        log.info("Update message {}", message.toString());
        return service.updateMessage(message);
    }

    @GetMapping("/{messageId}")
    public Message get(@PathVariable("messageId") long messageId) throws BadPaddingException, IllegalBlockSizeException {
        log.info("Get message {}", messageId);
        return service.getMessage(messageId);
    }
}
