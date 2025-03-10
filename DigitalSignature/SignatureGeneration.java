import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class worldcheckSignature {

    public static void main(String[] args) throws Exception {}

    public static String generateWCSignature(String apiSecret, String apiKey, String httpMethod, String uriString, String contentType, String payload) {
        try {
            // Convert string to URI
            URI uri = new URI(uriString);
            String headers = (payload == null ? "(request-target) host date" : "(request-target) host date content-type content-length");
            String date = ZonedDateTime.now(UTC).format(RFC_1123_DATE_TIME);
            StringBuilder dataToSign = new StringBuilder(300)
                    .append("(request-target): ").append(httpMethod.toLowerCase()).append(' ').append(uri.getPath())
                    .append("\nhost: ").append(uri.getHost())
                    .append("\ndate: ").append(date);
            
            if (payload != null) {
                int contentLength = payload.length();
                dataToSign.append("\ncontent-type: ").append(contentType)
                          .append("\ncontent-length: ").append(contentLength)
                          .append('\n').append(payload);
            }

            // Sign WC data
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(apiSecret.getBytes(), "HmacSHA256"));

            byte[] hmac = mac.doFinal(dataToSign.toString().getBytes());
            String signature = Base64.getEncoder().encodeToString(hmac);
            String authorization = "Signature keyId=\"" + apiKey +
                    "\",algorithm=\"hmac-sha256\",headers=\"" + headers +
                    "\",signature=\"" + signature + '"';
            return authorization;

        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
