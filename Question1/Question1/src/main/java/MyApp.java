import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class MyApp {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java MyApp <PRN Number> <file location>");
            System.exit(1);
        }

        String prnNumber = args[0];
        String fileLocation = args[1];


        try {
            // Read and parse JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(fileLocation));

            // Extract value associated with the key "destination"
            String destinationValue = findDestinationValue(jsonNode, "destination");

            // Generate a random alphanumeric string
            String randomString = generateRandomString();

            // Generate the hash
            String hash = generateHash(prnNumber, destinationValue, randomString);

            // Append random string to the hash
            String output = hash + ";" + randomString;
            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[6]; // 6 bytes = 8 characters
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String generateHash(String prnNumber, String destination, String randomString) {
        String input = prnNumber + destination + randomString;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String findDestinationValue(JsonNode node, String key) {
        if (node.isObject()) {
            if (node.has(key)) {
                return node.get(key).asText();
            } else {
                for (JsonNode child : node) {
                    String value = findDestinationValue(child, key);
                    if (value != null) {
                        return value;
                    }
                }
            }
        }
        return null;
    }
}
