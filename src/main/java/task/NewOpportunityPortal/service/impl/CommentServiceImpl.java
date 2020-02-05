package task.NewOpportunityPortal.service.impl;

import task.NewOpportunityPortal.entity.Comment;
import task.NewOpportunityPortal.repository.CommentRepository;
import task.NewOpportunityPortal.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    public Long createComment(Comment comment) {
        log.info("Create comment: {}", comment.getId());
        return repository.createComment(comment);
    }

    @Override
    public Comment getComment(Long commentId) {
        log.info("Get comment: {}", commentId);
        return repository.getComment(commentId);
    }

    @Override
    public Comment updateComment(Comment comment) {
        log.info("Update comment: {}", comment.getId());
        return repository.updateComment(comment);
    }

    @Override
    public boolean removeComment(Long commentId) {
        log.info("Remove comment: {}", commentId);
        return repository.removeComment(commentId);
    }
    @Override
    public List<Comment> getCommentAdverts(Long advertId){
        log.info("Get comment adverts: {}", advertId);
        return repository.getCommentAdverts(advertId);
    };
}
