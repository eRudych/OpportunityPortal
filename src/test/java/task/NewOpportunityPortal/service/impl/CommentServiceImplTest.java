package task.NewOpportunityPortal.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.NewOpportunityPortal.entity.Comment;
import task.NewOpportunityPortal.repository.CommentRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

    @InjectMocks
    CommentServiceImpl service;

    @Mock
    CommentRepository repository;

    @Test
    public void createComment() {
        LocalDateTime now = LocalDateTime.now();
        Comment comment = new Comment(1L, 5L, 1L, "text", Timestamp.valueOf(now));
        service.createComment(comment);
        verify(repository, times(1)).createComment(comment);
    }

    @Test
    public void getComment() {
        when(repository.getComment(1L)).thenReturn(new Comment(1L, 5L, 1L, "text", null));
        Comment comment = service.getComment(1L);
        assertEquals(1, comment.getId().intValue());
        assertEquals(5, comment.getCreatorId().intValue());
        assertEquals(1, comment.getAdvertId().intValue());
        assertEquals("text", comment.getText());
    }

    @Test
    public void updateComment() {
        LocalDateTime now = LocalDateTime.now();
        Comment commentCreate = new Comment(1L, 5L, 1L, "text", Timestamp.valueOf(now));
        Comment commentUpdate = new Comment(1L, 5L, 1L, "text123", Timestamp.valueOf(now));
        service.createComment(commentCreate);
        service.updateComment(commentUpdate);
        verify(repository, times(1)).updateComment(commentUpdate);
    }

    @Test
    public void getCommentAdverts() {
        List<Comment> list = new ArrayList<>();
        Comment commentOne = new Comment(1L, 5L, 1L, "text", null);
        Comment commentTwo = new Comment(2L, 5L, 1L, "tet2", null);
        list.add(commentOne);
        list.add(commentTwo);
        when(repository.getCommentAdverts(1L)).thenReturn(list);
        List<Comment> empList = service.getCommentAdverts(1L);
        assertEquals(2, empList.size());
        verify(repository, times(1)).getCommentAdverts(1L);
    }
}