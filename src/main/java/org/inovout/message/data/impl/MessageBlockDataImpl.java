package org.inovout.message.data.impl;

import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.data.MessageBlockData;

public class MessageBlockDataImpl<Block> implements MessageBlockData<Block> {
    @NonNull
    @Getter
    private byte[] bufferData;
    @NonNull
    @Getter
    private Block blockData;

    public MessageBlockDataImpl(byte[] bytes, Block data) {
        this.bufferData = bytes;
        this.blockData = data;
    }
}

