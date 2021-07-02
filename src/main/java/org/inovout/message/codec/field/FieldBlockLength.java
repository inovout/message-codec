package org.inovout.message.codec.field;

import org.inovout.message.codec.EncodeContext;

public interface FieldBlockLength extends FieldLength {
    void setLength(EncodeContext context);
}
