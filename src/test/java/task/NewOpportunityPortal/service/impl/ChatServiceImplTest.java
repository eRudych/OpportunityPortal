package task.NewOpportunityPortal.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.repository.ChatRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ChatServiceImplTest {

    @InjectMocks
    private ChatServiceImpl service;

    @Mock
    private ChatRepository repository;

    private List<Long> list;
    private Chat chat;
    private Chat chatUpdate;
    private Long chatId;

    @Before
    public void init() {
        this.list = new ArrayList<>();
        Message messageOne = new Message(1L, 5L, 1L, "chat", null);
        Message messageTwo = new Message(2L, 6L, 1L, "chat12", null);
        Message messageTree = new Message(3L, 9L, 1L, "chat12", null);
        this.list.add(messageOne.getId());
        this.list.add(messageTwo.getId());
        this.list.add(messageTree.getId());
        this.chat = new Chat(null, 5L, 1L, "chat", Arrays.asList(5L, 6L), null);
        this.chatUpdate = new Chat(null, 5L, 1L, "chat12", Arrays.asList(5L, 6L, 9L), null);
        this.chatId = 1L;
    }

    @Test
    public void testCreateChat() {
        ArgumentCaptor<Chat> chatArgs = ArgumentCaptor.forClass(Chat.class);
        service.createChat(chat);
        verify(repository).createChat(chatArgs.capture());
    }

    @Test
    public void testGetChat() {
        when(repository.getChat(eq(chatId))).thenReturn(chat);
        Chat getChat = service.getChat(chatId);
        assertThat(chat, samePropertyValuesAs(getChat, "create_at"));
    }

    @Test
    public void testUpdateChat() {
        service.updateChat(chatUpdate);
        verify(repository).updateChat(chatUpdate);
    }

    @Test
    public void testGetMessages() {
        when(repository.getMessages(eq(chatId))).thenReturn(list);
        List<Long> empList = service.getMessages(chatId);
        assertThat(3, is(empList.size()));
        verify(repository).getMessages(chatId);
    }
}