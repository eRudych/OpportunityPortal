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
        return repository.createChat(chat);
    }

    @Override
    public Chat getChat(Long chatId) {
        return repository.getChat(chatId);
    }

    @Override
    public Chat updateChat(Chat chat) {
        return repository.updateChat(chat);
    }

    @Override
    public boolean removeChat(Long chatId) {
        return repository.removeChat(chatId);
    }

    @Override
    public LinkedHashSet<Long> getMessages(Long chatId) {
        return repository.getMessages(chatId);
    }
}
