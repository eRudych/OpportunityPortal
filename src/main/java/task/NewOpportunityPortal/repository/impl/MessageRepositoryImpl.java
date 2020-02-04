package task.NewOpportunityPortal.repository.impl;

import org.jooq.DSLContext;
import task.NewOpportunityPortal.db.tables.records.MessageRecord;
import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

import static task.NewOpportunityPortal.db.tables.Message.MESSAGE;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MessageRepositoryImpl implements MessageRepository {

    private final DSLContext dsl;

    private Long insert(Message message) {
        MessageRecord messagesRecord = dsl.insertInto(MESSAGE, MESSAGE.CREATORID, MESSAGE.CHATID, MESSAGE.TEXT, MESSAGE.CREATED_AT)
                .values(message.getAuthorId(), message.getChatId(), message.getText(), message.getCreateAt())
                .returning(MESSAGE.ID)
                .fetchOne();
        log.info("Insert into db: {}", message.toString());
        return messagesRecord.getValue(MESSAGE.ID);
    }


    @Override
    public Long createMessage(Message message) {
        log.info("create message "+message.getId());
        return insert(message);
    }

    @Override
    public Message getMessage(Long messageId) {
        log.info("select message "+messageId);
        Message message = dsl.selectFrom(MESSAGE)
                .where(MESSAGE.ID.eq(messageId))
                .fetchOneInto(Message.class);
        log.info("set selected data "+messageId);
        message.setCreateAt(dsl.select(MESSAGE.CREATED_AT).from(MESSAGE).where(MESSAGE.ID.eq(messageId)).fetchOneInto((Timestamp.class)));
        return message;
    }

    @Override
    public Message updateMessage(Message message) {
        log.info("update text message "+message.getId());
        return getMessage((long) dsl.update(MESSAGE)
               .set(MESSAGE.TEXT, message.getText())
               .where(MESSAGE.ID.eq(message.getId())).execute());
    }

    @Override
    public boolean removeMessage(Long messageId) {
        log.info("remove message "+messageId);
        try {
            dsl.deleteFrom(MESSAGE)
                    .where(MESSAGE.ID.eq(messageId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }
}
