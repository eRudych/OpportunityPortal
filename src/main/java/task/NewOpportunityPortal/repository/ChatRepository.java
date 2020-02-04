package task.NewOpportunityPortal.repository;

import task.NewOpportunityPortal.entity.Chat;

import java.util.LinkedHashSet;


public interface ChatRepository {

    Long createChat(Chat chat);

    Chat getChat(Long chatId);

    Chat updateChat(Chat chat);

    boolean removeChat(Long chatId);

    LinkedHashSet<Long> getMessages(Long chatId);
}
