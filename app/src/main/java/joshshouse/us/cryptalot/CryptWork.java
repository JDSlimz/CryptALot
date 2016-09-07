package joshshouse.us.cryptalot;

import android.util.Log;

import org.apache.commons.codec.binary.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Josh on 8/11/2016.
 */
public class CryptWork {

    private static final String ALGORITHM = "AES";

    public static String encrypt(String value, String userKey) throws Exception
    {
        Key key = generateKey(userKey);
        Cipher cipher = Cipher.getInstance(CryptWork.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        String encryptedValue64 = android.util.Base64.encodeToString(encryptedByteValue, 16);
        return encryptedValue64;

    }

    public static String decrypt(String value, String userKey) throws Exception
    {
        Key key = generateKey(userKey);
        Cipher cipher = Cipher.getInstance(CryptWork.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte [] decryptedValue64 = Base64.decodeBase64(value.getBytes());
        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue,"utf-8");
        return decryptedValue;
    }

    private static Key generateKey(String userKey) throws Exception
    {
        Key key = new SecretKeySpec(userKey.getBytes(),CryptWork.ALGORITHM);
        return key;
    }

}
