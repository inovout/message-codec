package org.inovout.message.codec;

import org.inovout.message.codec.field.FieldLength;
import org.inovout.message.codec.field.FieldName;
import org.inovout.message.codec.field.FieldValue;

public interface MessageField extends MessagePart {
    FieldName getName();

    FieldLength getLength();

    FieldValue getValue();

    int getBufferLength(DecodeContext context);
}
