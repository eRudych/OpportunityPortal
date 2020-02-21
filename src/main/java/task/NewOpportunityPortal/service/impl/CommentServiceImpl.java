package task.NewOpportunityPortal.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import task.NewOpportunityPortal.entity.Comment;
import task.NewOpportunityPortal.repository.CommentRepository;
import task.NewOpportunityPortal.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    public Long createComment(Comment comment) {
        LocalDateTime now = LocalDateTime.now();
        log.info("Create comment: {}", comment.toString());
        return repository.createComment(new Comment(
                comment.getId(),
                comment.getCreatorId(),
                comment.getAdvertId(),
                comment.getText(),
                java.sql.Timestamp.valueOf(now)));
    }

    @Override
    @Cacheable("comments")
    public Comment getComment(Long commentId) {
        log.info("Get comment: {}", commentId);
        return repository.getComment(commentId);
    }

    @Override
    @CachePut(value = "comments", key = "#comment.id")
    public Comment updateComment(Comment comment) {
        log.info("Update comment: {}", comment.toString());
        return repository.updateComment(comment);
    }

    @Override
    @CacheEvict("comments")
    public void removeComment(Long commentId) {
        log.info("Remove comment: {}", commentId);
        repository.removeComment(commentId);
    }


    @Override
    public List<Comment> getCommentAdverts(Long advertId) {
        log.info("Get comment adverts: {}", advertId);
        return repository.getCommentAdverts(advertId);
    }
}
