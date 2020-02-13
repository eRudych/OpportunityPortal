package task.NewOpportunityPortal.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.repository.ChatRepository;
import task.NewOpportunityPortal.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatRepository repository;

    @Override
    @Cacheable(value = "chats", key = "chat.id")
    public Long createChat(Chat chat) {
        Date now = new java.util.Date();
        log.info("Set time creates: {}",  now);
        chat.setCreateAt(new java.sql.Timestamp(now.getTime()));
        log.info("Create chat: {}",  chat.getId());
        return repository.createChat(chat);
    }

    @Override
    @Cacheable("chats")
    public Chat getChat(Long chatId) {
        log.info("Get chat: {}",  chatId);
        return repository.getChat(chatId);
    }

    @Override
    @CachePut("chats")
    public Chat updateChat(Chat chat) {
        log.info("Update chat: {}", chat.getId());
        return repository.updateChat(chat);
    }

    @Override
    @CacheEvict("chats")
    public boolean removeChat(Long chatId) {
        log.info("Remove chat: {}",  chatId);
        return repository.removeChat(chatId);
    }


    @Override
    public List<Long> getMessages(Long chatId) {
        log.info("Get messages from chat: {}",  chatId);
        return repository.getMessages(chatId);
    }
}
