package task.NewOpportunityPortal.repository.impl;

import org.jooq.DSLContext;
import task.NewOpportunityPortal.db.tables.records.Messages__Record;
import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

import static task.NewOpportunityPortal.db.tables.Messages__.MESSAGES__;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MessageRepositoryImpl implements MessageRepository {

    private final DSLContext dsl;
    
    private Long insert(Message message) {
        Messages__Record messagesRecord = dsl.insertInto(MESSAGES__, MESSAGES__.CREATORID, MESSAGES__.CHATID, MESSAGES__.TEXT, MESSAGES__.CREATED_AT)
                .values(message.getAuthorId(), message.getChatId(), message.getText(), message.getCreateAt())
                .returning(MESSAGES__.ID)
                .fetchOne();
        log.info("Insert into db: {}", message.toString());
        return messagesRecord.getValue(MESSAGES__.ID);
    }


    @Override
    public Long createMessage(Message message) {
        log.info("Create message: {}", message.getId());
        return insert(message);
    }

    @Override
    public Message getMessage(Long messageId) {
        log.info("Select message: {}", messageId);
        Message message = dsl.selectFrom(MESSAGES__)
                .where(MESSAGES__.ID.eq(messageId))
                .fetchOneInto(Message.class);
        log.info("Set selected data: {}", messageId);
        message.setCreateAt(dsl.select(MESSAGES__.CREATED_AT).from(MESSAGES__).where(MESSAGES__.ID.eq(messageId)).fetchOneInto((Timestamp.class)));
        return message;
    }

    @Override
    public Message updateMessage(Message message) {
        log.info("Update text message: {}", message.getId());
        return getMessage((long) dsl.update(MESSAGES__)
               .set(MESSAGES__.TEXT, message.getText())
               .where(MESSAGES__.ID.eq(message.getId())).execute());
    }

    @Override
    public boolean removeMessage(Long messageId) {
        log.info("Remove message: {}", messageId);
        try {
            dsl.deleteFrom(MESSAGES__)
                    .where(MESSAGES__.ID.eq(messageId)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }
}
