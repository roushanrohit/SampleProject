package org.gitvcs.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicLong;

public final class HashUtil {

    private static final AtomicLong SEQUENCE = new AtomicLong();

    private HashUtil() {
    }

    // Uniqueness is guaranteed by the monotonic sequence baked into the input before hashing,
    // so two commits with identical content/author/message never collide.
    public static String nextCommitId(String seed) {
        String input = seed + "#" + SEQUENCE.incrementAndGet();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.substring(0, 10);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
