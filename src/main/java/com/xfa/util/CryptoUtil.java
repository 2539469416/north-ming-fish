package com.xfa.util;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author 北冥有鱼
 */
@Component
public class CryptoUtil {
    public static String base64Encode(String text) {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        byte[] encodedBytes = Base64.getEncoder().encode(bytes);
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }

    public static String base64Decode(String encodedText) {
        byte[] encodedBytes = encodedText.getBytes(StandardCharsets.UTF_8);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
