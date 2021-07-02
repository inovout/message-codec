package org.inovout.message.codec.block;

import org.inovout.message.CodecSettings;

public interface Decoder<R> {
    Class<?> getType();

    R decode(byte bytes[]);
    R decode(byte bytes[], CodecSettings settings);


}
