package task.NewOpportunityPortal.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.entity.Comment;
import task.NewOpportunityPortal.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

    @InjectMocks
    private CommentServiceImpl service;

    @Mock
    private CommentRepository repository;

    private List<Comment> list;
    private Comment comment;
    private Comment commentUpdate;
    private Long commentId;

    @Before
    public void init() {
        this.list = new ArrayList<>();
        Comment commentOne = new Comment(null, 5L, 1L, "text", null);
        Comment commentTwo = new Comment(null, 5L, 1L, "tet2", null);
        this.list.add(commentOne);
        this.list.add(commentTwo);
        this.comment = new Comment(1L, 5L, 1L, "text", null);
        this.commentUpdate = new Comment(1L, 5L, 1L, "text123", null);
        this.commentId = 1L;
    }

    @Test
    public void testCreateComment() {
        ArgumentCaptor<Comment> commentArgs = ArgumentCaptor.forClass(Comment.class);
        service.createComment(comment);
        verify(repository).createComment(commentArgs.capture());
    }

    @Test
    public void testGetComment() {
        when(repository.getComment(eq(commentId))).thenReturn(comment);
        Comment getComment = service.getComment(commentId);
        assertThat(comment, samePropertyValuesAs(getComment, "create_at"));
    }

    @Test
    public void testUpdateComment() {
        service.updateComment(commentUpdate);
        verify(repository).updateComment(commentUpdate);
    }

    @Test
    public void testGetCommentAdverts() {
        when(repository.getCommentAdverts(eq(commentId))).thenReturn(list);
        List<Comment> empList = service.getCommentAdverts(commentId);
        assertThat(2, is(empList.size()));
        verify(repository).getCommentAdverts(commentId);
    }
}