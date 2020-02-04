package task.NewOpportunityPortal.repository.impl;

import org.jooq.DSLContext;
import task.NewOpportunityPortal.db.tables.records.ChatRecord;
import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.LinkedHashSet;

import static task.NewOpportunityPortal.db.tables.Chat.CHAT;
import static task.NewOpportunityPortal.db.tables.Message.MESSAGE;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ChatRepositoryImpl implements ChatRepository {

    private final DSLContext dsl;

    private Long insert(Chat chat) {
        ChatRecord chatsRecord = dsl.insertInto(CHAT, CHAT.CREATORID, CHAT.ADVERTID, CHAT.NAME, CHAT.USERSID,CHAT.CREATED_AT)
                .values(chat.getCreatorId(), chat.getAdvertId(), chat.getName(), (Long[]) chat.getUsersId().toArray(), chat.getCreateAt())
                .returning(CHAT.ID)
                .fetchOne();
        log.info("Insert into db: {}", chat.toString());
        return chatsRecord.getValue(CHAT.ID);
    }
    
    @Override
    public Long createChat(Chat chat) {
        log.info("create chat "+chat.getId());
        return insert(chat);
    }

    @Override
    public Chat getChat(Long chatId) {
        log.info("select chat "+chatId);
        Chat chat = dsl.selectFrom(CHAT)
                .where(CHAT.ID.eq(chatId))
                .fetchOneInto(Chat.class);
        log.info("set selected data "+chatId);
        chat.setCreateAt(dsl.select(CHAT.CREATED_AT).from(CHAT).where(CHAT.ID.eq(chatId)).fetchOneInto((Timestamp.class)));
        return chat;
    }

    @Override
    public Chat updateChat(Chat chat) {
        log.info("update text chat "+chat.getId());
        return getChat((long) dsl.update(CHAT)
                .set(CHAT.NAME, chat.getName())
                .set(CHAT.USERSID, (Long[]) chat.getUsersId().toArray())
                .where(CHAT.ID.eq(chat.getId())).execute());
    }

    @Override
    public boolean removeChat(Long chatId) {
        log.info("remove chat "+chatId);
        try {
            dsl.deleteFrom(CHAT)
                    .where(CHAT.ID.eq(chatId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public LinkedHashSet<Long> getMessages(Long chatId) {
        return (LinkedHashSet<Long>) dsl.select(MESSAGE.ID)
                .from(MESSAGE)
                .where(MESSAGE.CHATID.eq(chatId))
                .orderBy(MESSAGE.CREATED_AT).fetchSet(String.valueOf(Long.class));
    }
}
