package org.inovout.message.data;

import org.inovout.message.codec.MessageBlock;
import org.inovout.message.codec.MessageField;

public interface MessagePacketData {

    void addBlockData(MessageBlock block, MessageBlockData<?> data);

    MessageBlockData<?> getBlockData(MessageBlock block);

    void addFiledData(MessageField field, MessageFieldData<?> fieldDat);

    MessageFieldData<?> getFieldData(MessageField field);

    MessageFieldData<?>[] getFiledDatas();

    Object get(String name);
}
