package task.NewOpportunityPortal.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import task.NewOpportunityPortal.cryp.EncryptDecrypt;
import task.NewOpportunityPortal.entity.Message;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class CryptAOP {

    private final EncryptDecrypt encryptDecrypt;

    @Around("@annotation(EncryptMethod) && args(message,..)")
    public long beforeEncryptMessage(ProceedingJoinPoint joinPoint, Message message) throws Throwable {
        log.info("Start encoding...");
        try {
            String encodedText = encryptDecrypt.encrypt(message.getText());
            message = new Message(message.getId(),
                    message.getAuthorId(),
                    message.getChatId(),
                    encodedText,
                    message.getCreateAt());
            log.info("... end of encoding");
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            log.error("Error during text encryption: ", e);
        }
        return (long) joinPoint.proceed(new Object[]{message});
    }

    @Around(value = "@annotation(DecryptMethod) && args(messageId)")
    public Message decryptMethod(ProceedingJoinPoint joinPoint, Long messageId) throws Throwable {
        Message message = null;
        log.info("Start decoding");
        try {
            message = (Message) joinPoint.proceed(new Object[]{messageId});
            log.info("Decoder message");
            message = new Message(
                    message.getId(),
                    message.getAuthorId(),
                    message.getChatId(),
                    encryptDecrypt.decrypt(message.getText()),
                    message.getCreateAt());
            log.info("... end of decoding");
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            log.error("Error during text decryption: ", e);
        }
        return message;
    }
}
