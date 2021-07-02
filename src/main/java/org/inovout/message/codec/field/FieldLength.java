package org.inovout.message.codec.field;

import org.inovout.message.codec.DecodeContext;

public interface FieldLength {
    Integer getLength(DecodeContext context);

}
