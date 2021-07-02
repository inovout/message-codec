package org.inovout.message.exception;

public class CodecException extends Exception {
    public CodecException(String message, Exception inner) {
        super(message, inner);
    }
}
