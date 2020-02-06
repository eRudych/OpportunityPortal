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
import java.io.UnsupportedEncodingException;
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
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            log.error(e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("Create message: {}", message.getId());
        return repository.createMessage(message);
    }

    @Override
    public Message getMessage(Long userId) {
        log.info("Get message: {}", userId);
        Message message = repository.getMessage(userId);
        log.info("Encoder message");
        try {
            log.info("Encoder message");
            message.setText(encryptDecrypt.decrypt(message.getText()));
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            log.error(e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public Message updateMessage(Message message) {
        log.info("Update message: {}", message.getId());
        return repository.updateMessage(message);
    }

    @Override
    public boolean removeMessage(Long userId) {
        log.info("Remove message: {}", userId);
        return repository.removeMessage(userId);
    }
}
