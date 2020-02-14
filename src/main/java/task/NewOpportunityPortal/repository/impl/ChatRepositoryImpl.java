package task.NewOpportunityPortal.repository.impl;

import org.jooq.DSLContext;
import task.NewOpportunityPortal.db.tables.records.Chats_Record;
import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static task.NewOpportunityPortal.db.tables.Chats_.CHATS_;
import static task.NewOpportunityPortal.db.tables.Messages__.MESSAGES__;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ChatRepositoryImpl implements ChatRepository {

    private final DSLContext dsl;

    private Long insert(Chat chat) {
        Chats_Record chatsRecord = dsl.insertInto(CHATS_, CHATS_.CREATORID, CHATS_.ADVERTID, CHATS_.NAME, CHATS_.USERSID, CHATS_.CREATED_AT)
                .values(chat.getCreatorId(), chat.getAdvertId(), chat.getName(), chat.getUsersId().toArray(new Long[chat.getUsersId().size()]), chat.getCreateAt())
                .returning(CHATS_.ID)
                .fetchOne();
        log.info("Insert into db: {}", chat.toString());
        return chatsRecord.getValue(CHATS_.ID);
    }

    @Override
    public Long createChat(Chat chat) {
        log.info("Create chat: {}", chat.getId());
        return insert(chat);
    }

    @Override
    public Chat getChat(Long chatId) {
        log.info("Select chat {}", chatId);
        Chat chat = dsl.selectFrom(CHATS_)
                .where(CHATS_.ID.eq(chatId))
                .fetchOneInto(Chat.class);
        log.info("Set selected data {}", chatId);
        chat.setCreateAt(dsl.select(CHATS_.CREATED_AT).from(CHATS_).where(CHATS_.ID.eq(chatId)).fetchOneInto((Timestamp.class)));
        return chat;
    }

    @Override
    public Chat updateChat(Chat chat) {
        log.info("Update text chat {}", chat.getId());
        return getChat((long) dsl.update(CHATS_)
                .set(CHATS_.NAME, chat.getName())
                .set(CHATS_.USERSID, chat.getUsersId().toArray(new Long[chat.getUsersId().size()]))
                .where(CHATS_.ID.eq(chat.getId())).execute());
    }

    @Override
    public boolean removeChat(Long chatId) {
        log.info("Remove chat {}", chatId);
        try {
            dsl.deleteFrom(CHATS_)
                    .where(CHATS_.ID.eq(chatId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Long> getMessages(Long chatId) {
        log.info("Get messages from chat {}", chatId);
        return dsl.selectFrom(MESSAGES__)
                .where(MESSAGES__.CHATID.eq(chatId))
                .orderBy(MESSAGES__.CREATED_AT)
                .fetch(r -> (r.get(0, Long.class)));
    }

    @Override
    public List<Long> getAllAvailChats(Long userId) {
        log.info("Get all avail chats for user: {}", userId);
        return dsl.select(CHATS_.ID).from(CHATS_)
                .where(CHATS_.USERSID.in(new Long[]{userId}))
                .fetch(r->(r.get(0, Long.class)));
    }
}
