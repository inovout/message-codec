package org.inovout.message.codec;

import org.inovout.message.exception.CodecException;

public interface MessageBlock {
   // byte[] getExampleData();

    void decode(DecodeContext context) throws CodecException;

    void encode(EncodeContext context) throws CodecException;
}
