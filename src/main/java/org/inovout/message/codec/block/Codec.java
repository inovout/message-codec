package org.inovout.message.codec.block;

import org.inovout.message.BlockType;

public interface Codec<V> extends Decoder<V>, Encoder<V> {
    BlockType getBlockType();
    @Override
    default Class<?> getType() {
        return this.getBlockType().getType();

    }
}
