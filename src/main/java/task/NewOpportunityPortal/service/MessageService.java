package task.NewOpportunityPortal.service;

import task.NewOpportunityPortal.entity.Message;

public interface MessageService {

    Long createMessage(Message message);

    Message getMessage(Long messageId);

    Message updateMessage(Message message);

    void removeMessage(Long messageId);
}
