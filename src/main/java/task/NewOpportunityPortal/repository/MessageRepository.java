package task.NewOpportunityPortal.repository;


import task.NewOpportunityPortal.entity.Message;

public interface MessageRepository {

    Long createMessage(Message message);

    Message getMessage(Long messageId);

    Message updateMessage(Message message);

    boolean removeMessage(Long messageId);
}
