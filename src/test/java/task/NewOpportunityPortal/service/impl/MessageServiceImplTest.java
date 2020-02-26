package task.NewOpportunityPortal.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.repository.MessageRepository;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceImplTest {

    @InjectMocks
    private MessageServiceImpl service;

    @Mock
    private MessageRepository repository;

    private Message message;
    private Message messageUpdate;
    private Long messageId;

    @Before
    public void init() {
        this.message = new Message(null, 5L, 1L, "text", null);
        this.messageUpdate = new Message(null, 5L, 1L, "text123", null);
        this.messageId = 1L;
    }

    @Test
    public void testCreateMessage() {
        ArgumentCaptor<Message> messageArgs = ArgumentCaptor.forClass(Message.class);
        service.createMessage(message);
        verify(repository).createMessage(messageArgs.capture());
    }

    @Test
    public void testGetMessage() {
        when(repository.getMessage(eq(messageId))).thenReturn(message);
        Message getMessage = service.getMessage(messageId);
        assertThat(message, samePropertyValuesAs(getMessage, "create_at"));
    }

    @Test
    public void testUpdateMessage() {
        service.updateMessage(messageUpdate);
        verify(repository).updateMessage(messageUpdate);
    }
}