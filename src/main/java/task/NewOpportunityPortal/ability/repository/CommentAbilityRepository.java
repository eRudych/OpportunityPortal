package task.NewOpportunityPortal.ability.repository;

import task.NewOpportunityPortal.ability.entity.CommentAbility;

public interface CommentAbilityRepository {

    Long createCommentAbilityRate( CommentAbility comment);

    void removeCommentAbilityRate(Long commentId);

    CommentAbility updateCommentAbilityRate( CommentAbility comment);

    CommentAbility getCommentAbilityRate(Long commentId);
}
