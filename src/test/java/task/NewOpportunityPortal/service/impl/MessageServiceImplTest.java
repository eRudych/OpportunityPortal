package task.NewOpportunityPortal.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.repository.MessageRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceImplTest {

    @InjectMocks
    MessageServiceImpl service;

    @Mock
    MessageRepository repository;

    @Test
    public void createMessage() {
        LocalDateTime now = LocalDateTime.now();
        Message message = new Message(1L, 5L, 1L, "text", Timestamp.valueOf(now));
        service.createMessage(message);
        verify(repository, times(1)).createMessage(message);
    }

    @Test
    public void getMessage() {
        when(repository.getMessage(1L)).thenReturn(new Message(1L, 5L, 1L, "text", null));
        Message message = service.getMessage(1L);
        assertEquals(1, message.getId().intValue());
        assertEquals(5, message.getAuthorId().intValue());
        assertEquals(1, message.getChatId().intValue());
        assertEquals("text", message.getText());
    }

    @Test
    public void updateMessage() {
        LocalDateTime now = LocalDateTime.now();
        Message messageCreate = new Message(1L, 5L, 1L, "text", Timestamp.valueOf(now));
        Message messageUpdate = new Message(1L, 5L, 1L, "text123", Timestamp.valueOf(now));
        service.createMessage(messageCreate);
        service.updateMessage(messageUpdate);
        verify(repository, times(1)).updateMessage(messageUpdate);
    }
}