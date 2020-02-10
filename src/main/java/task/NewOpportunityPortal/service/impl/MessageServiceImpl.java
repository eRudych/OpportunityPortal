package task.NewOpportunityPortal.service.impl;

import task.NewOpportunityPortal.cryp.EncryptDecrypt;
import task.NewOpportunityPortal.entity.Message;
import task.NewOpportunityPortal.repository.MessageRepository;
import task.NewOpportunityPortal.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MessageServiceImpl implements MessageService{

    private final MessageRepository repository;
    private final EncryptDecrypt encryptDecrypt;

    @Override
    public Long createMessage(Message message) {
        Date now = new java.util.Date();
        log.info("Set time creates: {}",  now);
        message.setCreateAt(new java.sql.Timestamp(now.getTime()));
        try {
            log.info("Encoder message");
            message.setText(encryptDecrypt.encrypt(message.getText()));
        } catch (BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException  e) {
            log.error("Error during text encryption: ", e);
        }
        log.info("Create message: {}", message.getId());
        return repository.createMessage(message);
    }

    @Override
    public Message getMessage(Long messageId) {
        log.info("Get message: {}", messageId);
        Message message = repository.getMessage(messageId);
        try {
            log.info("Decoder message");
            message.setText(encryptDecrypt.decrypt(message.getText()));
        } catch (BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException  e) {
            log.error("Error during text decryption: ", e);
        }
        return message;
    }

    @Override
    public Message updateMessage(Message message) {
        log.info("Update message: {}", message.getId());
        return repository.updateMessage(message);
    }

    @Override
    public boolean removeMessage(Long messageId) {
        log.info("Remove message: {}", messageId);
        return repository.removeMessage(messageId);
    }
}
