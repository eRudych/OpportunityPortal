package task.NewOpportunityPortal.ability.service;

import task.NewOpportunityPortal.ability.entity.CommentAbility;

public interface CommentAbilityService {

    Long createCommentAbilityRate( CommentAbility comment);

    boolean removeCommentAbilityRate( Long commentId);

    CommentAbility updateCommentAbilityRate( CommentAbility comment);

    CommentAbility getCommentAbilityRate(Long commentId);
}
