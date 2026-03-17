package co.istad.chhaya.webmvc.util;

import java.util.concurrent.ThreadLocalRandom;

public class GenerateUtil {

    public static String generateProductCode() {
        // Generate a random number between 0 (inclusive) and 100,000 (exclusive)
        int randomNumber = ThreadLocalRandom.current().nextInt(100000);

        // %05d ensures the number is always 5 digits, padding with 0s if needed
        return String.format("%s-%05d", "ISTADP", randomNumber);
    }

}
