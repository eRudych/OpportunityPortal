package task.NewOpportunityPortal.service;

import task.NewOpportunityPortal.entity.Message;

public interface MessageService {

    Long createMessage(Message message);

    Message getMessage(Long userId);

    Message updateMessage(Message message);

    boolean removeMessage(Long userId);
}
