package task.NewOpportunityPortal.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import task.NewOpportunityPortal.db.tables.records.ChatsRecord;
import task.NewOpportunityPortal.entity.Chat;
import task.NewOpportunityPortal.repository.ChatRepository;

import java.sql.Timestamp;
import java.util.List;

import static task.NewOpportunityPortal.db.tables.Chats.CHATS;
import static task.NewOpportunityPortal.db.tables.Messages.MESSAGES;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ChatRepositoryImpl implements ChatRepository {

    private final DSLContext dsl;

    private Long insert(Chat chat) {
        ChatsRecord chatsRecord = dsl.insertInto(CHATS, CHATS.CREATORID, CHATS.ADVERTID, CHATS.NAME, CHATS.USERSID, CHATS.CREATED_AT)
                .values(chat.getCreatorId(), chat.getAdvertId(), chat.getName(), chat.getUsersId().toArray(new Long[chat.getUsersId().size()]), chat.getCreateAt())
                .returning(CHATS.ID)
                .fetchOne();
        log.info("Insert into db: {}", chat.toString());
        return chatsRecord.getValue(CHATS.ID);
    }

    @Override
    public Long createChat(Chat chat) {
        log.info("Create chat: {}", chat.toString());
        return insert(chat);
    }

    @Override
    public Chat getChat(Long chatId) {
        log.info("Select chat {}", chatId);
        return dsl.selectFrom(CHATS)
                .where(CHATS.ID.eq(chatId))
                .fetchOne(r -> new Chat(
                        r.get(0, Long.class),
                        r.get(1, Long.class),
                        r.get(2, Long.class),
                        r.get(3, String.class),
                        r.get(4, List.class),
                        r.get(5, Timestamp.class)
                ));
    }

    @Override
    public Chat updateChat(Chat chat) {
        log.info("Update text chat {}", chat.toString());
        return getChat((long) dsl.update(CHATS)
                .set(CHATS.NAME, chat.getName())
                .set(CHATS.USERSID, chat.getUsersId().toArray(new Long[chat.getUsersId().size()]))
                .where(CHATS.ID.eq(chat.getId())).execute());
    }

    @Override
    public void removeChat(Long chatId) {
        log.info("Remove chat {}", chatId);
        dsl.deleteFrom(CHATS)
                .where(CHATS.ID.eq(chatId))
                .execute();
    }

    @Override
    public List<Long> getMessages(Long chatId) {
        log.info("Get messages from chat {}", chatId);
        return dsl.selectFrom(MESSAGES)
                .where(MESSAGES.CHATID.eq(chatId))
                .orderBy(MESSAGES.CREATED_AT)
                .fetch(r -> (r.get(0, Long.class)));
    }

    @Override
    public List<Long> getAllAvailChats(Long userId) {
        log.info("Get all avail chats for user: {}", userId);
        return dsl.select(CHATS.ID).from(CHATS)
                .where(CHATS.USERSID.in(new Long[]{userId}))
                .fetch(r -> (r.get(0, Long.class)));
    }
}
