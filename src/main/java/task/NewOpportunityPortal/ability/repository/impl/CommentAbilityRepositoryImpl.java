package task.NewOpportunityPortal.ability.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import task.NewOpportunityPortal.ability.entity.CommentAbility;
import task.NewOpportunityPortal.ability.repository.CommentAbilityRepository;
import task.NewOpportunityPortal.db.tables.records.AbilityCommentRecord;

import static task.NewOpportunityPortal.db.tables.AbilityComment.ABILITY_COMMENT;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CommentAbilityRepositoryImpl implements CommentAbilityRepository {

    private final DSLContext dsl;

    private Long insert(CommentAbility comment) {
        AbilityCommentRecord commentRecord = dsl.insertInto(ABILITY_COMMENT, ABILITY_COMMENT.ID, ABILITY_COMMENT.TEXT)
                .values(comment.getId(), comment.getText())
                .returning(ABILITY_COMMENT.ID)
                .fetchOne();
        log.info("Insert into db: {}", comment.toString());
        return commentRecord.getValue(ABILITY_COMMENT.ID);
    }

    @Override
    public Long createCommentAbilityRate(CommentAbility comment) {
        log.info("Create comment ability rate {}", comment);
        return insert(comment);
    }

    @Override
    public void removeCommentAbilityRate(Long commentId) {
        log.info("Remove comment ability rate {}", commentId);
        dsl.deleteFrom(ABILITY_COMMENT)
                .where(ABILITY_COMMENT.ID.eq(commentId)).execute();
    }

    @Override
    public CommentAbility updateCommentAbilityRate(CommentAbility comment) {
        log.info("Update comment ability rate {}", comment.toString());
        return getCommentAbilityRate((long) dsl.update(ABILITY_COMMENT)
                .set(ABILITY_COMMENT.TEXT, comment.getText())
                .where(ABILITY_COMMENT.ID.eq(comment.getId())).execute());
    }

    @Override
    public CommentAbility getCommentAbilityRate(Long commentId) {
        log.info("Get comment ability rate {}", commentId);
        CommentAbility comment = dsl.selectFrom(ABILITY_COMMENT)
                .where(ABILITY_COMMENT.ID.eq(commentId))
                .fetchOneInto(CommentAbility.class);
        log.info("Set selected data: {}", commentId);
        return comment;
    }
}
