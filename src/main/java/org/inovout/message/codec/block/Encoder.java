package org.inovout.message.codec.block;

import org.apache.commons.codec.DecoderException;
import org.inovout.message.CodecSettings;
import org.inovout.message.exception.CodecException;

public interface Encoder<V> {
    Class<?> getType();

    byte[] encode(V value) throws DecoderException, CodecException;
    byte[] encode(V value, CodecSettings settings) throws CodecException;
}
