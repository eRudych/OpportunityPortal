package task.NewOpportunityPortal.repository.impl;

import org.jooq.DSLContext;
import task.NewOpportunityPortal.db.tables.records.CommentRecord;
import task.NewOpportunityPortal.entity.Comment;
import task.NewOpportunityPortal.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static task.NewOpportunityPortal.db.tables.Comment.COMMENT;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CommentRepositoryImpl implements CommentRepository {

    private final DSLContext dsl;

    private Long insert(Comment comment) {
        CommentRecord commentsRecord = dsl.insertInto(COMMENT, COMMENT.CREATORID, COMMENT.ADVERTID, COMMENT.TEXT, COMMENT.CREATED_AT)
                .values(comment.getCreatorId(), comment.getAdvertId(), comment.getText(), comment.getCreateAt())
                .returning(COMMENT.ID)
                .fetchOne();
        log.info("Insert into db: {}", comment.toString());
        return commentsRecord.getValue(COMMENT.ID);
    }
    
    @Override
    public Long createComment(Comment comment) {
        log.info("Create comment: {}", comment.getId());
        return insert(comment);
    }

    @Override
    public Comment getComment(Long commentId) {
        log.info("Select comment: {}", commentId);
        Comment comment = dsl.selectFrom(COMMENT)
                .where(COMMENT.ID.eq(commentId))
                .fetchOneInto(Comment.class);
        log.info("Set selected data: {}", commentId);
        comment.setCreateAt(dsl.select(COMMENT.CREATED_AT).from(COMMENT).where(COMMENT.ID.eq(commentId)).fetchOneInto((Timestamp.class)));
        return comment;
    }

    @Override
    public Comment updateComment(Comment comment) {
        log.info("Update text comment: {}", comment.getId());
        return getComment((long) dsl.update(COMMENT)
                .set(COMMENT.TEXT, comment.getText())
                .where(COMMENT.ID.eq(comment.getId())).execute());
    }

    @Override
    public boolean removeComment(Long commentId) {
        log.info("Remove comment: {}", commentId);
        try {
            dsl.deleteFrom(COMMENT)
                    .where(COMMENT.ID.eq(commentId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Comment> getCommentAdverts(Long advertId){
        log.info("Get comment adverts: {}", advertId);
        return dsl.selectFrom(COMMENT)
                .where(COMMENT.ADVERTID.eq(advertId))
                .orderBy(COMMENT.ID.desc())
                .fetch(r -> new Comment(r.get(0, Long.class), r.get(1, Long.class), r.get(2, Long.class), r.get(3, String.class), r.get(4, Timestamp.class)));
    }
}
