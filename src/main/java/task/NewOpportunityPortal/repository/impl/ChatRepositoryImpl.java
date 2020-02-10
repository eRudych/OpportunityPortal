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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static task.NewOpportunityPortal.db.tables.Chat.CHAT;
import static task.NewOpportunityPortal.db.tables.Message.MESSAGE;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ChatRepositoryImpl implements ChatRepository {

    private final DSLContext dsl;

    private Long insert(Chat chat) {
        ChatRecord chatsRecord = dsl.insertInto(CHAT, CHAT.CREATORID, CHAT.ADVERTID, CHAT.NAME, CHAT.USERSID,CHAT.CREATED_AT)
                .values(chat.getCreatorId(), chat.getAdvertId(), chat.getName(), chat.getUsersId().toArray(new Long[chat.getUsersId().size()]), chat.getCreateAt())
                .returning(CHAT.ID)
                .fetchOne();
        log.info("Insert into db: {}", chat.toString());
        return chatsRecord.getValue(CHAT.ID);
    }
    
    @Override
    public Long createChat(Chat chat) {
        log.info("Create chat: {}", chat.getId());
        return insert(chat);
    }

    @Override
    public Chat getChat(Long chatId) {
        log.info("Select chat {}", chatId);
        Chat chat = dsl.selectFrom(CHAT)
                .where(CHAT.ID.eq(chatId))
                .fetchOneInto(Chat.class);
        log.info("Set selected data {}", chatId);
        chat.setCreateAt(dsl.select(CHAT.CREATED_AT).from(CHAT).where(CHAT.ID.eq(chatId)).fetchOneInto((Timestamp.class)));
        return chat;
    }

    @Override
    public Chat updateChat(Chat chat) {
        log.info("Update text chat {}", chat.getId());
        return getChat((long) dsl.update(CHAT)
                .set(CHAT.NAME, chat.getName())
                .set(CHAT.USERSID, chat.getUsersId().toArray(new Long[chat.getUsersId().size()]))
                .where(CHAT.ID.eq(chat.getId())).execute());
    }

    @Override
    public boolean removeChat(Long chatId) {
        log.info("Remove chat {}", chatId);
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
    public List<Long> getMessages(Long chatId) {
        log.info("Get messages from chat {}", chatId);
        return dsl.selectFrom(MESSAGE)
                .where(MESSAGE.CHATID.eq(chatId))
                .orderBy(MESSAGE.CREATED_AT)
                .fetch(r -> (r.get(0, Long.class)));
    }

    @Override
    public List<Long> getAllAvailChats(Long userId) {
        log.info("Get all avail chats for user: {}", userId);
        return dsl.select(CHAT.ID).from(CHAT)
                .where(CHAT.USERSID.in(new Long[]{userId}))
                .fetch(r->(r.get(0, Long.class)));
    }
}
