package task.NewOpportunityPortal.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import task.NewOpportunityPortal.db.tables.records.UserRecord;
import task.NewOpportunityPortal.entity.User;
import task.NewOpportunityPortal.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;

import static task.NewOpportunityPortal.db.tables.User.USER;
import static task.NewOpportunityPortal.db.tables.UserAbility.USER_ABILITY;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final DSLContext dsl;

    private Long insert(User user) {
        UserRecord usersRecord = dsl.insertInto(USER, USER.LOGIN, USER.NAME, USER.NICK, USER.PASSWORD, USER.CREATED_AT)
                .values(user.getLogin(), user.getName(), user.getNick(), user.getPassword(), user.getCreateAt())
                .returning(USER.ID)
                .fetchOne();
        log.info("Insert into db: {}", user.toString());
        return usersRecord.getValue(USER.ID);
    }

    @Override
    public Long createUser(User user) {
        log.info("Create user: {}", user.toString());
        return insert(user);
    }

    @Override
    public User getUserById(Long userId) {
        log.info("Select user by id {}", userId);
        return dsl.selectFrom(USER)
                .where(USER.ID.eq(userId))
                .fetchOne(r -> new User(
                        r.get(USER.ID, Long.class),
                        r.get(USER.LOGIN, String.class),
                        r.get(USER.PASSWORD, String.class),
                        r.get(USER.NAME, String.class),
                        r.get(USER.NICK, String.class),
                        calculateAverageAssessment(userId),
                        r.get(USER.CREATED_AT, Timestamp.class)
                ));
    }

    @Override
    public User getUserByLogin(String userLogin) {
        log.info("Select user by id {}", userLogin);
        User user = dsl.selectFrom(USER)
                .where(USER.LOGIN.eq(userLogin))
                .fetchOne(r -> new User(
                        r.get(USER.ID, Long.class),
                        r.get(USER.LOGIN, String.class),
                        r.get(USER.PASSWORD, String.class),
                        r.get(USER.NAME, String.class),
                        r.get(USER.NICK, String.class),
                        0,
                        r.get(USER.CREATED_AT, Timestamp.class)
                ));
        return new User(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getNick(),
                calculateAverageAssessment(user.getId()),
                user.getCreateAt());
    }

    @Override
    public User updateUser(User user) {
        log.info("Update text user: {}", user.toString());
        return getUserById((long) dsl.update(USER)
                .set(USER.LOGIN, user.getLogin())
                .set(USER.NAME, user.getName())
                .set(USER.NICK, user.getNick())
                .set(USER.PASSWORD, user.getPassword())
                .where(USER.ID.eq(user.getId())).execute());
    }

    @Override
    public void removeUser(Long userId) {
        log.info("Remove user: {}", userId);
        dsl.deleteFrom(USER)
                .where(USER.ID.eq(userId))
                .execute();
    }

    private int calculateAverageAssessment(Long userId) {
        log.info("Calculate average assessment for user: {}", userId);
        List<Integer> assessments = dsl.select(USER_ABILITY.ASSESSMENT)
                .from(USER_ABILITY)
                .where(USER_ABILITY.USERID.eq(userId))
                .fetch((r -> (r.get(0, Integer.class))));
        return assessments.stream().reduce(0, Integer::sum) / assessments.size();
    }
}
