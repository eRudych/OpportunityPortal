package task.NewOpportunityPortal.ability.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import task.NewOpportunityPortal.ability.entity.UserAbility;
import task.NewOpportunityPortal.ability.repository.UserAbilityRepository;
import task.NewOpportunityPortal.ability.service.UserAbilityService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAbilityServiceImpl implements UserAbilityService {

    private final UserAbilityRepository repository;

    @Override
    public Long createUserAbilityRate(UserAbility userAbility) {
        Date now = new java.util.Date();
        log.info("Set time creates: {}",  now);
        userAbility.setCreateAt(new java.sql.Timestamp(now.getTime()));
        log.info("Create user ability rate: {}", userAbility.toString());
        return repository.createUserAbilityRate(userAbility) ;
    }

    @Override
    @CachePut(value = "abilities", key = "#userAbility.id")
    public UserAbility updateUserAbilityRate(UserAbility userAbility) {
        log.info("Update user ability rate: {}", userAbility.getId());
        return repository.updateUserAbilityRate(userAbility);
    }

    @Override
    @CacheEvict(value = "abilities")
    public boolean removeUserAbilityRate(Long userAbilityId) {
        log.info("Remove user ability rate: {}", userAbilityId);
        return repository.removeUserAbilityRate(userAbilityId);
    }

    @Override
    @Cacheable("abilities")
    public UserAbility getUserAbilityRate(Long userAbilityId) {
        log.info("Get user ability rate by id: {}", userAbilityId);
        return repository.getUserAbilityRate(userAbilityId);
    }

    @Override
    public List<Long> getAllAbilitiesRate(Long userId) {
        log.info("Get all abilities rate for user with userId: {}", userId);
        return repository.getAllAbilityRate(userId);
    }
}
