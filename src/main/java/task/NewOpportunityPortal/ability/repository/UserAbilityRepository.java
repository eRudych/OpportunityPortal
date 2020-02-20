package task.NewOpportunityPortal.ability.repository;

import task.NewOpportunityPortal.ability.entity.UserAbility;

import java.util.List;

public interface UserAbilityRepository {

    Long createUserAbilityRate( UserAbility userAbility);

    UserAbility updateUserAbilityRate( UserAbility userAbility);

    void removeUserAbilityRate(Long userAbilityId);

    UserAbility getUserAbilityRate(Long userAbilityId);

    List<Long> getAllAbilityRate(Long userId);
}
