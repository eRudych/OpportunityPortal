package task.NewOpportunityPortal.service.impl;

import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.repository.ChatRepository;
import task.NewOpportunityPortal.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatRepository repository;

    @Override
    public Long createChat(Chat chat) {
        log.info("Create chat: {}",  chat.getId());
        return repository.createChat(chat);
    }

    @Override
    public Chat getChat(Long chatId) {
        log.info("Get chat: {}",  chatId);
        return repository.getChat(chatId);
    }

    @Override
    public Chat updateChat(Chat chat) {
        log.info("Update chat: {}", chat.getId());
        return repository.updateChat(chat);
    }

    @Override
    public boolean removeChat(Long chatId) {
        log.info("Remove chat: {}",  chatId);
        return repository.removeChat(chatId);
    }

    @Override
    public Set<Long> getMessages(Long chatId) {
        log.info("Get messages from chat: {}",  chatId);
        return repository.getMessages(chatId);
    }
}
