package privateKeyExtraction;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ExtractPrivateKeyFromPFX {

    private static final String PFX_FILE_PATH = "C:\\Users\\Aditya\\Downloads\\dtb_umeme.pfx"; // Hardcoded file path
    private static final String PASSWORD = "D!@m0ndce3t"; // Hardcoded password

    public static void main(String[] args) {
        PrivateKey privateKey = loadPrivateKey();
        if (privateKey != null) {
            System.out.println("Private Key extracted successfully:");
            System.out.println(privateKeyToString(privateKey));
        } else {
            System.out.println("Failed to extract Private Key.");
        }
    }

    private static PrivateKey loadPrivateKey() {
        try (FileInputStream fis = new FileInputStream(PFX_FILE_PATH)) {
            KeyStore keystore = KeyStore.getInstance("PKCS12");
            keystore.load(fis, PASSWORD.toCharArray());

            String alias = keystore.aliases().nextElement();
            return (PrivateKey) keystore.getKey(alias, PASSWORD.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String privateKeyToString(PrivateKey privateKey) {
        byte[] privateKeyBytes = privateKey.getEncoded();
        return Base64.getEncoder().encodeToString(privateKeyBytes);
    }
}

