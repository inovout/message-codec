package org.inovout.message.codec.field;

import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.EncodeContext;

public interface FieldName {
    String getName(DecodeContext context);
    String getName(EncodeContext context);
    String getAlias();
}
