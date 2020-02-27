package task.NewOpportunityPortal.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.entity.User;
import task.NewOpportunityPortal.repository.ChatRepository;
import task.NewOpportunityPortal.repository.UserRepository;

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
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChatRepository chatRepository;

    private List<Long> ids;
    private User user;
    private User userUpdate;
    private Long userId;
    private Long creatorId;

    @Before
    public void init() {
        this.ids = new ArrayList<>();
        Chat chatOne = new Chat(1L, 5L, 1L, "chat", Arrays.asList(5L, 6L), null);
        Chat chatTwo = new Chat(2L, 5L, 1L, "chat12", Arrays.asList(5L, 6L, 9L), null);
        this.ids.add(chatOne.getId());
        this.ids.add(chatTwo.getId());
        this.user = new User(null, "login", "pass", "name", "nick", 1, null);
        this.userUpdate = new User(null, "logidsdsn", "padsdss", "nddddme", "nicddddk", 1, null);
        this.userId = 1L;
        this.creatorId = 5L;
    }

    @Test
    public void testCreateUser() {
        ArgumentCaptor<User> userArgs = ArgumentCaptor.forClass(User.class);
        service.createUser(user);
        verify(userRepository).createUser(userArgs.capture());
        User userArgsValue = userArgs.getValue();
        assertThat(userArgsValue, samePropertyValuesAs(user, "createAt"));
    }

    @Test
    public void testGetUserById() {
        when(userRepository.getUserById(eq(userId))).thenReturn(user);
        User userById = service.getUserById(userId);
        assertThat(userById, samePropertyValuesAs(user));
    }

    @Test
    public void testGetUserByLogin() {
        when(userRepository.getUserByLogin(eq(user.getLogin()))).thenReturn(user);
        User userByLogin = service.getUserByLogin(user.getLogin());
        assertThat(userByLogin, samePropertyValuesAs(user));
    }

    @Test
    public void testUpdateUser() {
        service.updateUser(userUpdate);
        verify(userRepository).updateUser(userUpdate);
    }

    @Test
    public void testGetAllAvailChats() {
        when(chatRepository.getAllAvailChats(eq(creatorId))).thenReturn(ids);
        List<Long> empList = service.getAllAvailChats(creatorId);
        assertThat(empList.size(), is(ids.size()));
        verify(chatRepository).getAllAvailChats(creatorId);
    }

}