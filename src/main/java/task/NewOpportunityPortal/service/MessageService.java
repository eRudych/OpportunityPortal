package task.NewOpportunityPortal.service;

import task.NewOpportunityPortal.entity.Message;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.List;

public interface MessageService {

    Long createMessage(Message message) throws BadPaddingException, IllegalBlockSizeException;

    Message getMessage(Long messageId) throws BadPaddingException, IllegalBlockSizeException;

    Message updateMessage(Message message);

    boolean removeMessage(Long messageId);
}
