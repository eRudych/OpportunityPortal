package task.NewOpportunityPortal.service;

import task.NewOpportunityPortal.entity.Chat;

import java.util.List;

public interface ChatService {

    Long createChat(Chat chat);

    Chat getChat(Long chatId);

    Chat updateChat(Chat chat);

    void removeChat(Long chatId);

    List<Long> getMessages(Long chatId);
}
