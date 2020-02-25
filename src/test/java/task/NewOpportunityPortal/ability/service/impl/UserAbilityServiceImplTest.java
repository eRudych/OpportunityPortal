package task.NewOpportunityPortal.ability.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.ability.entity.UserAbility;
import task.NewOpportunityPortal.ability.repository.UserAbilityRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAbilityServiceImplTest {

    @InjectMocks
    UserAbilityServiceImpl service;

    @Mock
    UserAbilityRepository repository;

    @Test
    public void createUserAbilityRate() {
        UserAbility UserAbility = new UserAbility(1L, 5L, 1L, 3, null);
        service.createUserAbilityRate(UserAbility);
        verify(repository, times(1)).createUserAbilityRate(UserAbility);
    }

    @Test
    public void updateUserAbilityRate() {
        UserAbility UserAbilityCreate = new UserAbility(1L, 5L, 1L, 3, null);
        UserAbility UserAbilityUpdate = new UserAbility(1L, 5L, 1L, 4, null);
        service.createUserAbilityRate(UserAbilityCreate);
        service.updateUserAbilityRate(UserAbilityUpdate);
        verify(repository, times(1)).updateUserAbilityRate(UserAbilityUpdate);

    }

    @Test
    public void getUserAbilityRate() {
        when(repository.getUserAbilityRate(1L)).thenReturn(new UserAbility(1L, 5L, 1L, 3, null));
        UserAbility UserAbility = service.getUserAbilityRate(1L);
        assertEquals(1, UserAbility.getId().intValue());
        assertEquals(5, UserAbility.getAuthorId().intValue());
        assertEquals(1, UserAbility.getUserId().intValue());
        assertEquals(3, UserAbility.getAssessment());
    }

    @Test
    public void getAllAbilitiesRate() {
        List<Long> list = new ArrayList<>();
        UserAbility UserAbilityOne = new UserAbility(1L, 5L, 1L, 3, null);
        UserAbility UserAbilityTwo = new UserAbility(1L, 6L, 1L, 3, null);
        UserAbility UserAbilityTree = new UserAbility(1L, 9L, 1L, 3, null);
        list.add(UserAbilityOne.getId());
        list.add(UserAbilityTwo.getId());
        list.add(UserAbilityTree.getId());
        when(repository.getAllAbilityRate(1L)).thenReturn(list);
        List<Long> empList = service.getAllAbilitiesRate(1L);
        assertEquals(3, empList.size());
        verify(repository, times(1)).getAllAbilityRate(1L);
    }
}