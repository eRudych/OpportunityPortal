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

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;

    @Override
    @EncryptMethod
    public Long createMessage(Message message) {
        Date now = new java.util.Date();
        log.info("Set time creates: {}", now);
        message.setCreateAt(new java.sql.Timestamp(now.getTime()));
        log.info("Create message: {}", message.getId());
        return repository.createMessage(message);
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
        log.info("Update message: {}", message.getId());
        return repository.updateMessage(message);
    }

    @Override
    @CacheEvict("messages")
    public boolean removeMessage(Long messageId) {
        log.info("Remove message: {}", messageId);
        return repository.removeMessage(messageId);
    }
}
