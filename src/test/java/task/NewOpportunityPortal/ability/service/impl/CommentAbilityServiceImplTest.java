package task.NewOpportunityPortal.ability.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.ability.entity.CommentAbility;
import task.NewOpportunityPortal.ability.repository.CommentAbilityRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentAbilityServiceImplTest {

    @InjectMocks
    CommentAbilityServiceImpl service;

    @Mock
    CommentAbilityRepository repository;

    @Test
    public void createCommentAbilityRate() {
        CommentAbility commentAbility = new CommentAbility(1L, "text");
        service.createCommentAbilityRate(commentAbility);
        verify(repository, times(1)).createCommentAbilityRate(commentAbility);
    }

    @Test
    public void updateCommentAbilityRate() {
        CommentAbility commentAbilityCreate = new CommentAbility(1L, "text");
        CommentAbility commentAbilityUpdate = new CommentAbility(1L, "text464");
        service.createCommentAbilityRate(commentAbilityCreate);
        service.updateCommentAbilityRate(commentAbilityUpdate);
        verify(repository, times(1)).updateCommentAbilityRate(commentAbilityUpdate);
    }

    @Test
    public void getCommentAbilityRate() {
        when(repository.getCommentAbilityRate(1L)).thenReturn(new CommentAbility(1L, "text"));
        CommentAbility CommentAbility = service.getCommentAbilityRate(1L);
        assertEquals(1, CommentAbility.getId().intValue());
        assertEquals("text", CommentAbility.getText());
    }
}