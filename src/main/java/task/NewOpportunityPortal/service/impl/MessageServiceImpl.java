package task.NewOpportunityPortal.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import task.NewOpportunityPortal.aop.DecryptMethod;
import task.NewOpportunityPortal.aop.EncryptMethod;
import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.repository.MessageRepository;
import task.NewOpportunityPortal.service.MessageService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;

    @Override
    @EncryptMethod
    public Long createMessage(Message message) {
        LocalDateTime now = LocalDateTime.now();
        log.info("Create message: {}", message.toString());
        return repository.createMessage(new Message(
                message.getId(),
                message.getAuthorId(),
                message.getChatId(),
                message.getText(),
                java.sql.Timestamp.valueOf(now)));
    }

    @Override
    @Cacheable("messages")
    @DecryptMethod
    public Message getMessage(Long messageId) {
        log.info("Get message: {}", messageId);
        Message message = repository.getMessage(messageId);
        return message;
    }

    @Override
    @CachePut(value = "messages", key = "#message.id")
    public Message updateMessage(Message message) {
        log.info("Update message: {}", message.toString());
        return repository.updateMessage(message);
    }

    @Override
    @CacheEvict("messages")
    public void removeMessage(Long messageId) {
        log.info("Remove message: {}", messageId);
        repository.removeMessage(messageId);
    }
}
