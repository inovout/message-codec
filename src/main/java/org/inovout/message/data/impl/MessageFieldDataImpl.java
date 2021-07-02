package org.inovout.message.data.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;
import org.inovout.message.codec.MessageField;
import org.inovout.message.data.MessageBlockData;
import org.inovout.message.data.MessageFieldData;

@RequiredArgsConstructor
public class MessageFieldDataImpl<T> implements MessageFieldData<T> {
    @NonNull
    @Getter
    private String name;
    private MessageVersion messageVersion;
    private MessageType<?> messageType;
    @NonNull
    private MessageField field;
    @NonNull
    @Getter
    private T data;
    @NonNull
    private MessageBlockData<?> blockData;

    @NonNull
    public MessageFieldDataImpl(MessageBlockData<?> blockData, T fieldData) {
        this.blockData = blockData;
        this.data = fieldData;
    }


    public byte[] getBufferData() {
        return this.blockData.getBufferData();
    }


    @Override
    public int getBufferLength() {
        return this.blockData.getBufferLength();
    }
}
