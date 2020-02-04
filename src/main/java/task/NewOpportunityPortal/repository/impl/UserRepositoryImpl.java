package task.NewOpportunityPortal.repository.impl;

import org.jooq.DSLContext;
import task.NewOpportunityPortal.db.tables.records.UserRecord;
import task.NewOpportunityPortal.entity.User;
import task.NewOpportunityPortal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

import static task.NewOpportunityPortal.db.tables.User.USER;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
        log.info("create user "+user.getId());
        return insert(user);
    }

    @Override
    public User getUserById(Long userId) {
        log.info("select user by id"+userId);
        User user = dsl.selectFrom(USER)
                .where(USER.ID.eq(userId))
                .fetchOneInto(User.class);
        log.info("set selected data "+userId);
        user.setCreateAt(dsl.select(USER.CREATED_AT).from(USER).where(USER.ID.eq(userId)).fetchOneInto(Timestamp.class));
        return user;
    }

    @Override
    public User getUserByLogin(String userLogin) {
        log.info("select user by id"+userLogin);
        User user = dsl.selectFrom(USER)
                .where(USER.LOGIN.eq(userLogin))
                .fetchOneInto(User.class);
        log.info("set selected data "+userLogin);
        user.setCreateAt(dsl.select(USER.CREATED_AT).from(USER).where(USER.LOGIN.eq(userLogin)).fetchOneInto(Timestamp.class));
        return user;
    }

    @Override
    public User updateUser(User user) {
        log.info("update text user "+user.getId());
        return getUserById((long) dsl.update(USER)
                .set(USER.LOGIN, user.getLogin())
                .set(USER.NAME, user.getName())
                .set(USER.NICK, user.getNick())
                .set(USER.PASSWORD, user.getPassword())
                .where(USER.ID.eq(user.getId())).execute());
    }

    @Override
    public boolean removeUser(Long userId) {
        log.info("remove user "+userId);
        try {
            dsl.deleteFrom(USER)
                    .where(USER.ID.eq(userId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }
}
