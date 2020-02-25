package task.NewOpportunityPortal.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.entity.User;
import task.NewOpportunityPortal.repository.ChatRepository;
import task.NewOpportunityPortal.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl service;

    @Mock
    UserRepository userRepository;

    @Mock
    ChatRepository chatRepository;

    @Test
    public void createUser() {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1L, "login", "pass", "name", "nick", 1, Timestamp.valueOf(now));
        service.createUser(user);
        verify(userRepository, times(1)).createUser(user);
    }

    @Test
    public void getUserById() {
        when(userRepository.getUserById(1L)).thenReturn(new User(1L, "login", "pass", "name", "nick", 1, null));
        User userById = service.getUserById(1L);
        assertEquals(1, userById.getId().intValue());
        assertEquals("login", userById.getLogin());
        assertEquals("pass", userById.getPassword());
        assertEquals("name", userById.getName());
        assertEquals("nick", userById.getNick());
    }

    @Test
    public void getUserByLogin() {
        when(userRepository.getUserByLogin("loin")).thenReturn(new User(1L, "login", "pass", "name", "nick", 1, null));
        User userByLogin = service.getUserByLogin("login");
        assertEquals("login", userByLogin.getLogin());
        assertEquals("pass", userByLogin.getPassword());
        assertEquals("name", userByLogin.getName());
        assertEquals("nick", userByLogin.getNick());
    }

    @Test
    public void updateUser() {
        LocalDateTime now = LocalDateTime.now();
        User userCreate = new User(1L, "login", "pass", "name", "nick", 1, Timestamp.valueOf(now));
        User userUpdate = new User(1L, "logidsdsn", "padsdss", "nddddme", "nicddddk", 1, Timestamp.valueOf(now));
        service.createUser(userCreate);
        service.updateUser(userUpdate);
        verify(userRepository, times(1)).updateUser(userUpdate);
    }

    @Test
    public void getAllAvailChats() { List<Long> list = new ArrayList<>();
        Chat chatOne = new Chat(1L, 5L, 1L, "chat", Arrays.asList(5L, 6L), null);
        Chat chatTwo = new Chat(1L, 5L, 1L, "chat12", Arrays.asList(5L, 6L, 9L), null);
        list.add(chatOne.getId());
        list.add(chatTwo.getId());
        when(chatRepository.getAllAvailChats(5L)).thenReturn(list);
        List<Long> empList = service.getAllAvailChats(5L);
        assertEquals(2, empList.size());
        verify(chatRepository, times(1)).getAllAvailChats(5L);
    }
}