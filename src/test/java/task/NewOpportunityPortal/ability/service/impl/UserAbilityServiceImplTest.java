package task.NewOpportunityPortal.ability.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.ability.entity.UserAbility;
import task.NewOpportunityPortal.ability.repository.UserAbilityRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserAbilityServiceImplTest {

    @InjectMocks
    private UserAbilityServiceImpl service;

    @Mock
    private UserAbilityRepository repository;

    private List<Long> ids;
    private UserAbility userAbility;
    private UserAbility userAbilityUpdate;
    private Long userId;

    @Before
    public void init() {
        this.ids = new ArrayList<>();
        UserAbility UserAbilityOne = new UserAbility(null, 5L, 1L, 3, null);
        UserAbility UserAbilityTwo = new UserAbility(null, 6L, 1L, 3, null);
        UserAbility UserAbilityTree = new UserAbility(null, 9L, 1L, 3, null);
        this.ids.add(UserAbilityOne.getId());
        this.ids.add(UserAbilityTwo.getId());
        this.ids.add(UserAbilityTree.getId());
        this.userAbility = new UserAbility(null, 5L, 1L, 3, null);
        this.userAbilityUpdate = new UserAbility(null, 5L, 1L, 4, null);
        this.userId = 1L;
    }

    @Test
    public void testCreateUserAbilityRate() {
        ArgumentCaptor<UserAbility> userAbilityArgs = ArgumentCaptor.forClass(UserAbility.class);
        service.createUserAbilityRate(userAbility);
        verify(repository).createUserAbilityRate(userAbilityArgs.capture());
        UserAbility userAbilityArgsValue = userAbilityArgs.getValue();
        assertThat(userAbilityArgsValue, samePropertyValuesAs(userAbility, "createAt"));
    }

    @Test
    public void testUpdateUserAbilityRate() {
        service.updateUserAbilityRate(userAbilityUpdate);
        verify(repository).updateUserAbilityRate(userAbilityUpdate);
    }

    @Test
    public void testGetUserAbilityRate() {
        when(repository.getUserAbilityRate(eq(userId))).thenReturn(userAbility);
        UserAbility getUserAbility = service.getUserAbilityRate(userId);
        assertThat(getUserAbility, samePropertyValuesAs(userAbility));
    }

    @Test
    public void testGetAllAbilitiesRate() {
        when(repository.getAllAbilityRate(eq(userId))).thenReturn(ids);
        List<Long> empList = service.getAllAbilitiesRate(userId);
        assertThat(empList.size(), is(ids.size()));
        verify(repository).getAllAbilityRate(userId);
    }
}