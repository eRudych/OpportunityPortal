package task.NewOpportunityPortal.ability.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import task.NewOpportunityPortal.ability.entity.CommentAbility;
import task.NewOpportunityPortal.ability.repository.CommentAbilityRepository;
import task.NewOpportunityPortal.ability.service.CommentAbilityService;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentAbilityServiceImpl implements CommentAbilityService {

    private  final CommentAbilityRepository repository;

    @Override
    public Long createCommentAbilityRate(CommentAbility comment) {
        log.info("Create comment ability rate {}", comment);
        return repository.createCommentAbilityRate(comment);
    }

    @Override
    public void removeCommentAbilityRate(Long commentId) {
        log.info("Remove comment ability rate {}", commentId);
        repository.removeCommentAbilityRate(commentId);
    }

    @Override
    public CommentAbility updateCommentAbilityRate(CommentAbility comment) {
        log.info("Update comment ability rate {}", comment.toString());
        return repository.updateCommentAbilityRate(comment);
    }

    @Override
    public CommentAbility getCommentAbilityRate(Long commentId) {
        log.info("Get comment ability rate {}", commentId);
        return repository.getCommentAbilityRate(commentId);
    }
}
