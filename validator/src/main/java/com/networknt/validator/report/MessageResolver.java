package com.networknt.validator.report;

import java.util.ResourceBundle;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

/**
 * Resolves a message key to a {@link ValidationReport.Message} object.
 * <p>
 * Message Strings are resolved from the <code>messages</code> resource bundle.
 *
 */
public class MessageResolver {

    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    /**
     * Create a new instance (all messages will be emitted at the ERROR level).
     *
     */
    public MessageResolver() {

    }

    /**
     * Get the message with the given key.
     * <p>
     * If no message is found for the key will return <code>null</code>.
     *
     * @param key The key of the message to retrieve.
     * @param args Arguments to use when resolving the message String.
     *
     * @return The message for the given key, or <code>null</code> if no message is found
     */
    public ValidationReport.Message get(final String key, final Object... args) {
        requireNonNull(key, "A message key is required.");
        if (!messages.containsKey(key)) {
            return null;
        }
        return new ImmutableMessage(key, format(messages.getString(key), args));
    }

    /**
     * Create a message with the given key and message.
     * <p>
     * Used when translating validation messages from other sources (e.g. JSON schema validation)
     * where a message has already been generated.
     *
     * @param key The key to include in the message.
     * @param message The message to include
     *
     * @return A message that contains the given key and message string.
     */
    public ValidationReport.Message create(final String key, final String message) {
        requireNonNull(key, "A message key is required.");
        return new ImmutableMessage(key, message);
    }

}
