package org.inovout.message.codec;

import org.inovout.message.validator.BlockLengthValidator;

public interface MessageLength
        extends BlockLengthValidator {
    void setHeader(MessageHeader header);
}
