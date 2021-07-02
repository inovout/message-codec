package org.inovout.message;

import org.inovout.message.converter.CodecConverter;

public interface MessageField {
    String getName();

    String getAlias();

    FieldType getType();

    default int getLength() {
        if (null != this.getType()) {
            return this.getType().getLength();
        }
        return 0;
    }

    default CodecConverter<?, ?> getConverter() {
        return null;
    }

    default byte[] getEndTag() {
        return new byte[0];
    }


}
