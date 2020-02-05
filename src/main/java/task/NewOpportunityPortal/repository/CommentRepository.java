package task.NewOpportunityPortal.repository;

import task.NewOpportunityPortal.entity.Comment;

import java.util.List;

public interface CommentRepository {

    Long createComment(Comment comment);

    Comment getComment(Long commentId);

    Comment updateComment(Comment comment);

    boolean removeComment(Long commentId);

    List<Comment> getCommentAdverts(Long advertId);
}
