package task.NewOpportunityPortal.cryp;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@Component
@Slf4j
public class EncryptDecrypt {

    private final IvParameterSpec ivParameterSpec;
    private final SecretKeySpec secretKeySpec;
    private final Cipher cipher;

    public EncryptDecrypt() throws NoSuchPaddingException, NoSuchAlgorithmException {
        String initVector = System.getenv("INIT_VECTOR");
        this.ivParameterSpec = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
        String secretKey = System.getenv("SECRET_KEY");
        this.secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }


    public String encrypt(String toBeEncrypt) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        cipher = Cipher.getInstance("AES");
        secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encrypted = cipher.doFinal(toBeEncrypt.getBytes());
        log.info("encrypted: {}", Base64.encodeBase64String(encrypted));
        return Base64.encodeBase64String(encrypted);
    }

    public String decrypt(String encrypted) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        cipher = Cipher.getInstance("AES");
        secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(encrypted));
        return new String(decryptedBytes);
    }
}
