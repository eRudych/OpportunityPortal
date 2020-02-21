package task.NewOpportunityPortal.ability.service;

import task.NewOpportunityPortal.ability.entity.UserAbility;

import java.util.List;

public interface UserAbilityService {

    Long createUserAbilityRate(UserAbility userAbility);

    UserAbility updateUserAbilityRate(UserAbility userAbility);

    void removeUserAbilityRate(Long userAbilityId);

    UserAbility getUserAbilityRate(Long userAbilityId);

    List<Long> getAllAbilitiesRate(Long userId);
}
