package org.inovout.message.codec.field;

import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.EncodeContext;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.data.MessageBlockData;
import org.inovout.message.exception.CodecException;

public interface FieldValue extends MessageBlock {
    MessageBlockData<?> read(DecodeContext context);

    <T> void write(T data, EncodeContext context) throws CodecException;

    FieldLength getLength();


}
