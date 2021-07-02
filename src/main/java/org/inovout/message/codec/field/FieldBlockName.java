package org.inovout.message.codec.field;

import org.inovout.message.codec.EncodeContext;

public interface FieldBlockName extends FieldName {
    void setName(EncodeContext context);
}
