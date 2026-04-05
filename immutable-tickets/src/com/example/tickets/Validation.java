package com.example.tickets;

import java.util.regex.Pattern;

/**
 * Central place for validation helpers.
 *
 * Students can extend this as needed.
 */
public final class Validation {

    private static final Pattern EMAIL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private static final Pattern TICKET_ID = Pattern.compile("^[A-Z0-9-]+$");

    private Validation() {}

    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }

    public static void requireMaxLen(String value, int max, String fieldName) {
        if (value != null && value.length() > max) {
            throw new IllegalArgumentException(fieldName + " must be <= " + max + " chars");
        }
    }

    public static void requireEmail(String email, String fieldName) {
        requireNonBlank(email, fieldName);
        if (!EMAIL.matcher(email).matches()) {
            throw new IllegalArgumentException(fieldName + " must be a valid email");
        }
    }

    public static void requireTicketId(String id) {
        requireNonBlank(id, "id");
        requireMaxLen(id, 20, "id");
        if (!TICKET_ID.matcher(id).matches()) {
            throw new IllegalArgumentException("id must match " + TICKET_ID.pattern());
        }
    }

    public static void requireOneOf(String value, String fieldName, String... allowed) {
        if (value == null) return; // optional
        for (String a : allowed) {
            if (a.equals(value)) return;
        }
        throw new IllegalArgumentException(fieldName + " must be one of: " + String.join(", ", allowed));
    }

    public static void requireRange(Integer value, int min, int max, String fieldName) {
        if (value == null) return; // optional
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max);
        }
    }
}
