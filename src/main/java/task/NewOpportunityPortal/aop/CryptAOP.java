package task.NewOpportunityPortal.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task.NewOpportunityPortal.cryp.EncryptDecrypt;
import task.NewOpportunityPortal.entity.Message;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Objects;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class CryptAOP {

    private final EncryptDecrypt encryptDecrypt;

    @Before("@annotation(EncryptMethod) && args(message,..)")
    public void beforeEncryptMessage(Message message) throws InvalidAlgorithmParameterException {
        try {
            log.info("Encoder message");
            Objects.requireNonNull(message).setText(encryptDecrypt.encrypt(message.getText()));
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            log.error("Error during text encryption: ", e);
        }
    }

    @AfterReturning(value = "@annotation(DecryptMethod)", returning="retMessage")
    public void decryptMethod(Message retMessage) {
        try {
            log.info("Decoder message");
            retMessage.setText(encryptDecrypt.decrypt(retMessage.getText()));
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            log.error("Error during text decryption: ", e);
        }
    }
}
