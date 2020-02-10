package task.NewOpportunityPortal.repository;

import task.NewOpportunityPortal.entity.Chat;

import java.util.List;

public interface ChatRepository {

    Long createChat(Chat chat);

    Chat getChat(Long chatId);

    Chat updateChat(Chat chat);

    boolean removeChat(Long chatId);

    List<Long> getMessages(Long chatId);

    List<Long> getAllAvailChats(Long userId);
}
