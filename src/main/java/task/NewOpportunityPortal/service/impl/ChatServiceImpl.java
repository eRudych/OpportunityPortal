package task.NewOpportunityPortal.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.repository.ChatRepository;
import task.NewOpportunityPortal.service.ChatService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatRepository repository;

    @Override
    public Long createChat(Chat chat) {
        LocalDateTime now = LocalDateTime.now();
        log.info("Create chat: {}", chat.toString());
        return repository.createChat(new Chat(
                chat.getId(),
                chat.getCreatorId(),
                chat.getAdvertId(),
                chat.getName(),
                chat.getUsersId(),
                java.sql.Timestamp.valueOf(now)));
    }

    @Override
    @Cacheable("chats")
    public Chat getChat(Long chatId) {
        log.info("Get chat: {}", chatId);
        return repository.getChat(chatId);
    }

    @Override
    @CachePut(value = "chats", key = "#chat.id")
    public Chat updateChat(Chat chat) {
        log.info("Update chat: {}", chat.toString());
        return repository.updateChat(chat);
    }

    @Override
    @CacheEvict("chats")
    public void removeChat(Long chatId) {
        log.info("Remove chat: {}", chatId);
        repository.removeChat(chatId);
    }


    @Override
    public List<Long> getMessages(Long chatId) {
        log.info("Get messages from chat: {}", chatId);
        return repository.getMessages(chatId);
    }
}
