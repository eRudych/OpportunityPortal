package task.NewOpportunityPortal.repository;

import task.NewOpportunityPortal.entity.Chat;

import java.util.Set;


public interface ChatRepository {

    Long createChat(Chat chat);

    Chat getChat(Long chatId);

    Chat updateChat(Chat chat);

    boolean removeChat(Long chatId);

    Set<Long> getMessages(Long chatId);
}
