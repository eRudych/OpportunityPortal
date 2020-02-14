package task.NewOpportunityPortal.repository.impl;

import org.jooq.DSLContext;
import task.NewOpportunityPortal.db.tables.records.Comments_Record;
import task.NewOpportunityPortal.entity.Comment;
import task.NewOpportunityPortal.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static task.NewOpportunityPortal.db.tables.Comments_.COMMENTS_;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CommentRepositoryImpl implements CommentRepository {

    private final DSLContext dsl;

    private Long insert(Comment comment) {
        Comments_Record commentsRecord = dsl.insertInto(COMMENTS_, COMMENTS_.CREATORID, COMMENTS_.ADVERTID, COMMENTS_.TEXT, COMMENTS_.CREATED_AT)
                .values(comment.getCreatorId(), comment.getAdvertId(), comment.getText(), comment.getCreateAt())
                .returning(COMMENTS_.ID)
                .fetchOne();
        log.info("Insert into db: {}", comment.toString());
        return commentsRecord.getValue(COMMENTS_.ID);
    }

    @Override
    public Long createComment(Comment comment) {
        log.info("Create comment: {}", comment.getId());
        return insert(comment);
    }

    @Override
    public Comment getComment(Long commentId) {
        log.info("Select comment: {}", commentId);
        Comment comment = dsl.selectFrom(COMMENTS_)
                .where(COMMENTS_.ID.eq(commentId))
                .fetchOneInto(Comment.class);
        log.info("Set selected data: {}", commentId);
        comment.setCreateAt(dsl.select(COMMENTS_.CREATED_AT).from(COMMENTS_).where(COMMENTS_.ID.eq(commentId)).fetchOneInto((Timestamp.class)));
        return comment;
    }

    @Override
    public Comment updateComment(Comment comment) {
        log.info("Update text comment: {}", comment.getId());
        return getComment((long) dsl.update(COMMENTS_)
                .set(COMMENTS_.TEXT, comment.getText())
                .where(COMMENTS_.ID.eq(comment.getId())).execute());
    }

    @Override
    public boolean removeComment(Long commentId) {
        log.info("Remove comment: {}", commentId);
        try {
            dsl.deleteFrom(COMMENTS_)
                    .where(COMMENTS_.ID.eq(commentId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Comment> getCommentAdverts(Long advertId){
        log.info("Get comment adverts: {}", advertId);
        return dsl.selectFrom(COMMENTS_)
                .where(COMMENTS_.ADVERTID.eq(advertId))
                .orderBy(COMMENTS_.ID.desc())
                .fetch(r -> new Comment(r.get(0, Long.class), r.get(1, Long.class), r.get(2, Long.class), r.get(3, String.class), r.get(4, Timestamp.class)));
    }
}
