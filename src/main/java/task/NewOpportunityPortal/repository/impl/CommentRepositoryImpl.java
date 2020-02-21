package task.NewOpportunityPortal.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import task.NewOpportunityPortal.db.tables.records.CommentsRecord;
import task.NewOpportunityPortal.entity.Comment;
import task.NewOpportunityPortal.repository.CommentRepository;

import java.sql.Timestamp;
import java.util.List;

import static task.NewOpportunityPortal.db.tables.Comments.COMMENTS;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CommentRepositoryImpl implements CommentRepository {

    private final DSLContext dsl;

    private Long insert(Comment comment) {
        CommentsRecord commentsRecord = dsl.insertInto(COMMENTS, COMMENTS.CREATORID, COMMENTS.ADVERTID, COMMENTS.TEXT, COMMENTS.CREATED_AT)
                .values(comment.getCreatorId(), comment.getAdvertId(), comment.getText(), comment.getCreateAt())
                .returning(COMMENTS.ID)
                .fetchOne();
        log.info("Insert into db: {}", comment.toString());
        return commentsRecord.getValue(COMMENTS.ID);
    }

    @Override
    public Long createComment(Comment comment) {
        log.info("Create comment: {}", comment.toString());
        return insert(comment);
    }

    @Override
    public Comment getComment(Long commentId) {
        log.info("Select comment: {}", commentId);
        return dsl.selectFrom(COMMENTS)
                .where(COMMENTS.ID.eq(commentId))
                .fetchOne(r -> new Comment(
                        r.get(COMMENTS.ID, Long.class),
                        r.get(COMMENTS.CREATORID, Long.class),
                        r.get(COMMENTS.ADVERTID, Long.class),
                        r.get(COMMENTS.TEXT, String.class),
                        r.get(COMMENTS.CREATED_AT, Timestamp.class)
                ));
    }

    @Override
    public Comment updateComment(Comment comment) {
        log.info("Update text comment: {}", comment.toString());
        return getComment((long) dsl.update(COMMENTS)
                .set(COMMENTS.TEXT, comment.getText())
                .where(COMMENTS.ID.eq(comment.getId())).execute());
    }

    @Override
    public void removeComment(Long commentId) {
        log.info("Remove comment: {}", commentId);
        dsl.deleteFrom(COMMENTS)
                .where(COMMENTS.ID.eq(commentId))
                .execute();
    }

    @Override
    public List<Comment> getCommentAdverts(Long advertId) {
        log.info("Get comment adverts: {}", advertId);
        return dsl.selectFrom(COMMENTS)
                .where(COMMENTS.ADVERTID.eq(advertId))
                .orderBy(COMMENTS.ID.desc())
                .fetch(r -> new Comment(r.get(0, Long.class), r.get(1, Long.class), r.get(2, Long.class), r.get(3, String.class), r.get(4, Timestamp.class)));
    }
}
