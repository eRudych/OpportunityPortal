package task.NewOpportunityPortal.service.impl;

import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.repository.MessageRepository;
import task.NewOpportunityPortal.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MessageServiceImpl implements MessageService{

    private final MessageRepository repository;

    @Override
    public Long createMessage(Message message) {
        log.info("create message "+message.getId());
        return repository.createMessage(message);
    }

    @Override
    public Message getMessage(Long userId) {
        log.info("get message "+userId);
        return repository.getMessage(userId);
    }

    @Override
    public Message updateMessage(Message message) {
        log.info("update message "+message.getId());
        return repository.updateMessage(message);
    }

    @Override
    public boolean removeMessage(Long userId) {
        log.info("remove message "+userId);
        return repository.removeMessage(userId);
    }
}
