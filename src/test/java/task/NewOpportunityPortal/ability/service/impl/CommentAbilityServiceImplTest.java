package task.NewOpportunityPortal.ability.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.ability.entity.CommentAbility;
import task.NewOpportunityPortal.ability.repository.CommentAbilityRepository;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentAbilityServiceImplTest {

    @InjectMocks
    private CommentAbilityServiceImpl service;

    @Mock
    private CommentAbilityRepository repository;

    private CommentAbility commentAbility;
    private CommentAbility commentAbilityUpdate;
    private Long commentId;

    @Before
    public void init() {
        this.commentAbility = new CommentAbility(null, "text");
        this.commentAbilityUpdate = new CommentAbility(null, "text464");
        this.commentId = 1L;
    }

    @Test
    public void testCreateCommentAbilityRate() {
        ArgumentCaptor<CommentAbility> commentAbilityArgs = ArgumentCaptor.forClass(CommentAbility.class);
        service.createCommentAbilityRate(commentAbility);
        verify(repository).createCommentAbilityRate(commentAbilityArgs.capture());
    }

    @Test
    public void testUpdateCommentAbilityRate() {
        service.updateCommentAbilityRate(commentAbilityUpdate);
        verify(repository).updateCommentAbilityRate(commentAbilityUpdate);
    }

    @Test
    public void testGetCommentAbilityRate() {
        when(repository.getCommentAbilityRate(eq(commentId))).thenReturn(commentAbility);
        CommentAbility getCommentAbility = service.getCommentAbilityRate(commentId);
        assertThat(commentAbility, samePropertyValuesAs(getCommentAbility));
    }
}