package task.NewOpportunityPortal.service.impl;

import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.repository.ChatRepository;
import task.NewOpportunityPortal.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatRepository repository;

    @Override
    public Long createChat(Chat chat) {
        Date now = new java.util.Date();
        log.info("Set time creates: {}",  now);
        chat.setCreateAt(new java.sql.Timestamp(now.getTime()));
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
    public List<Long> getMessages(Long chatId) {
        log.info("Get messages from chat: {}",  chatId);
        return repository.getMessages(chatId);
    }
}
