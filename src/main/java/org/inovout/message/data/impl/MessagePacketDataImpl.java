package org.inovout.message.data.impl;

import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.codec.MessageField;
import org.inovout.message.data.MessageBlockData;
import org.inovout.message.data.MessageFieldData;
import org.inovout.message.data.MessagePacketData;

import java.util.HashMap;
import java.util.Map;

public class MessagePacketDataImpl
        extends HashMap<String, Object>
        implements MessagePacketData {
    @Getter
    @NonNull
    private Map<MessageBlock, MessageBlockData<?>> blocks = new HashMap<>();

    @NonNull
    @Getter
    private Map<MessageField, MessageFieldData<?>> fields = new HashMap<>();

    @Override
    public MessageFieldData<?>[] getFiledDatas() {
        MessageFieldData<?>[] fielddatas = new MessageFieldData<?>[this.fields.size()];
        this.fields.values().toArray(fielddatas);
        return fielddatas;
    }

    @Override
    public Object get(String name) {
        return super.get(name);
    }


    @Override
    public void addBlockData(MessageBlock block, MessageBlockData<?> data) {
        this.blocks.put(block, data);
    }

    @Override
    public MessageBlockData<?> getBlockData(MessageBlock block) {
        return this.blocks.get(block);
    }


    @Override
    public MessageFieldData<?> getFieldData(MessageField field) {
        return this.fields.get(field);
    }

    @Override
    public void addFiledData(MessageField field, MessageFieldData<?> fieldData) {
        this.fields.put(field, fieldData);
        super.put(fieldData.getName(), fieldData.getData());
    }
}
