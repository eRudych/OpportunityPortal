package task.NewOpportunityPortal.ability.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import task.NewOpportunityPortal.ability.entity.UserAbility;
import task.NewOpportunityPortal.ability.repository.UserAbilityRepository;
import task.NewOpportunityPortal.db.tables.records.UserAbilityRecord;

import java.sql.Timestamp;
import java.util.List;

import static task.NewOpportunityPortal.db.tables.UserAbility.USER_ABILITY;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserAbilityRepositoryImpl implements UserAbilityRepository {

    private final DSLContext dsl;

    private Long insert(UserAbility userAbility) {
        UserAbilityRecord userAbilityRecord = dsl.insertInto(USER_ABILITY, USER_ABILITY.AUTHORID, USER_ABILITY.USERID, USER_ABILITY.ASSESSMENT, USER_ABILITY.CREATED_AT)
                .values(userAbility.getAuthorId(), userAbility.getUserId(), userAbility.getAssessment(), userAbility.getCreateAt())
                .returning(USER_ABILITY.ID)
                .fetchOne();
        log.info("Insert into db: {}", userAbility.toString());
        return userAbilityRecord.getValue(USER_ABILITY.ID);
    }

    @Override
    public Long createUserAbilityRate(UserAbility userAbility) {
        log.info("Create user ability rate: {}", userAbility.toString());
        return insert(userAbility);
    }

    @Override
    public UserAbility updateUserAbilityRate(UserAbility userAbility) {
        log.info("Update user ability rate: {}", userAbility.getId());
        return getUserAbilityRate((long) dsl.update(USER_ABILITY)
                .set(USER_ABILITY.AUTHORID, userAbility.getAuthorId())
                .set(USER_ABILITY.USERID, userAbility.getUserId())
                .set(USER_ABILITY.ASSESSMENT, userAbility.getAssessment())
                .where(USER_ABILITY.ID.eq(userAbility.getId())).execute());
    }

    @Override
    public void removeUserAbilityRate(Long userAbilityId) {
        log.info("Remove user ability rate: {}", userAbilityId);
        dsl.deleteFrom(USER_ABILITY)
                .where(USER_ABILITY.ID.eq(userAbilityId)).execute();
    }

    @Override
    public UserAbility getUserAbilityRate(Long userAbilityId) {
        log.info("Get user ability rate by id: {}", userAbilityId);
        return dsl.selectFrom(USER_ABILITY)
                .where(USER_ABILITY.ID.eq(userAbilityId))
                .fetchOne(r -> new UserAbility(
                        r.get(0, Long.class),
                        r.get(1, Long.class),
                        r.get(2, Long.class),
                        r.get(4, Integer.class),
                        r.get(5, Timestamp.class)));
    }

    @Override
    public List<Long> getAllAbilityRate(Long userId) {
        log.info("Get all abilities rate for user with userId: {}", userId);
        return dsl.select(USER_ABILITY.ID).from(USER_ABILITY)
                .where(USER_ABILITY.USERID.eq(userId))
                .fetch(r -> (r.get(0, Long.class)));
    }
}
