package task.NewOpportunityPortal.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import task.NewOpportunityPortal.db.tables.records.MessagesRecord;
import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.repository.MessageRepository;

import java.sql.Timestamp;

import static task.NewOpportunityPortal.db.tables.Messages.MESSAGES;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MessageRepositoryImpl implements MessageRepository {

    private final DSLContext dsl;

    private Long insert(Message message) {
        MessagesRecord messagesRecord = dsl.insertInto(MESSAGES, MESSAGES.CREATORID, MESSAGES.CHATID, MESSAGES.TEXT, MESSAGES.CREATED_AT)
                .values(message.getAuthorId(), message.getChatId(), message.getText(), message.getCreateAt())
                .returning(MESSAGES.ID)
                .fetchOne();
        log.info("Insert into db: {}", message.toString());
        return messagesRecord.getValue(MESSAGES.ID);
    }


    @Override
    public Long createMessage(Message message) {
        log.info("Create message: {}", message.toString());
        return insert(message);
    }

    @Override
    public Message getMessage(Long messageId) {
        log.info("Select message: {}", messageId);
        return dsl.selectFrom(MESSAGES)
                .where(MESSAGES.ID.eq(messageId))
                .fetchOne(r -> new Message(
                        r.get(MESSAGES.ID, Long.class),
                        r.get(MESSAGES.CREATORID, Long.class),
                        r.get(MESSAGES.CHATID, Long.class),
                        r.get(MESSAGES.TEXT, String.class),
                        r.get(MESSAGES.CREATED_AT, Timestamp.class)
                ));
    }

    @Override
    public Message updateMessage(Message message) {
        log.info("Update text message: {}", message.toString());
        return getMessage((long) dsl.update(MESSAGES)
                .set(MESSAGES.TEXT, message.getText())
                .where(MESSAGES.ID.eq(message.getId())).execute());
    }

    @Override
    public void removeMessage(Long messageId) {
        log.info("Remove message: {}", messageId);
        dsl.deleteFrom(MESSAGES)
                .where(MESSAGES.ID.eq(messageId))
                .execute();
    }
}
