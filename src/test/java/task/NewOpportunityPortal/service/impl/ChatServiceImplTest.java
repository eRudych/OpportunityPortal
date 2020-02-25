package task.NewOpportunityPortal.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.repository.ChatRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ChatServiceImplTest {

    @InjectMocks
    ChatServiceImpl service;

    @Mock
    ChatRepository repository;

    @Test
    public void createChat() {
        LocalDateTime now = LocalDateTime.now();
        Chat chat = new Chat(1L, 5L, 1L, "chat", Arrays.asList(5L, 6L), Timestamp.valueOf(now));
        service.createChat(chat);
        verify(repository, times(1)).createChat(chat);
    }

    @Test
    public void getChat() {
        when(repository.getChat(1L)).thenReturn(new Chat(1L, 5L, 1L, "chat", Arrays.asList(5L, 6L), null));
        Chat chat = service.getChat(1L);
        assertEquals(1, chat.getId().intValue());
        assertEquals(5, chat.getCreatorId().intValue());
        assertEquals(1, chat.getAdvertId().intValue());
        assertEquals("chat", chat.getName());
        assertEquals(Arrays.asList(5L, 6L), chat.getUsersId());
    }

    @Test
    public void updateChat() {
        LocalDateTime now = LocalDateTime.now();
        Chat chatCreate = new Chat(1L, 5L, 1L, "chat", Arrays.asList(5L, 6L), Timestamp.valueOf(now));
        Chat chatUpdate = new Chat(1L, 5L, 1L, "chat12", Arrays.asList(5L, 6L, 9L), Timestamp.valueOf(now));
        service.createChat(chatCreate);
        service.updateChat(chatUpdate);
        verify(repository, times(1)).updateChat(chatUpdate);
    }

    @Test
    public void getMessages() {
        List<Long> list = new ArrayList<>();
        Message messageOne = new Message(1L, 5L, 1L, "chat", null);
        Message messageTwo = new Message(2L, 6L, 1L, "chat12", null);
        Message messageTree = new Message(3L, 9L, 1L, "chat12", null);
        list.add(messageOne.getId());
        list.add(messageTwo.getId());
        list.add(messageTree.getId());
        when(repository.getMessages(1L)).thenReturn(list);
        List<Long> empList = service.getMessages(1L);
        assertEquals(3, empList.size());
        verify(repository, times(1)).getMessages(1L);
    }
}