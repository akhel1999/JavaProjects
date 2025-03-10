package Fiorano;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptPassword {
    public static void main(String[] args) throws Exception {try {
        // Sample values for testing
        String msgId = "FIOACCQRYGT20231208141522757";
        String password = "Oracle@123";

        // Call the encryptPassword method
        String encryptedPassword = EncryptPassword.encryptPassword(msgId, password);

        // Print the results
        System.out.println("Original Password: " + password);
        System.out.println("Encrypted Password: " + encryptedPassword);
    } catch (Exception e) {
        e.printStackTrace();
    }
    	
    	 
    }

    public static String encryptPassword(String msgId, String password) throws Exception {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-512");
        byte[] digestSeed = mDigest.digest(msgId.getBytes());
        byte[] seedEncArray = Arrays.copyOf(digestSeed, 24);
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

        SecretKeySpec skspec = new SecretKeySpec(seedEncArray, "DESede");

        IvParameterSpec iv = new IvParameterSpec(new byte[8]);

        cipher.init(Cipher.ENCRYPT_MODE, skspec, iv);

        byte[] finalByteArray = cipher.doFinal(password.getBytes());

        String finalValue = new String(Base64.getEncoder().encode(finalByteArray));

        return finalValue;
}}
