package org.inovout.message.codec;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.inovout.message.CodecSettings;
import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;
import org.inovout.message.data.MessageBlockData;
import org.inovout.message.data.MessageFieldData;
import org.inovout.message.data.MessagePacketData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@RequiredArgsConstructor(staticName = "of")
public class DecodeContext {

    @NonNull
    private CodecSettings settings;
    @NonNull
    private ByteBuf byteBuf;
    @NonNull
    private MessagePacketData result;
    private List<String> errors = new ArrayList<>();

    @Getter
    @Setter
    private MessageVersion<?> messageVersion = MessageVersion.NO_MESSAGE_VERSION;
    @Getter
    @Setter
    private MessageType<?> messageType;


    public void addBlockData(MessageBlock block, MessageBlockData<?> data) {
        this.result.addBlockData(block, data);
    }

    public MessageBlockData<?> getBlockData(MessageBlock block) {
        return this.result.getBlockData(block);
    }


    public MessageFieldData<?> getFieldData(MessageField field) {
        return this.result.getFieldData(field);
    }

    public void addFiledData(MessageField field, MessageFieldData<?> fieldDat) {
        this.result.addFiledData(field, fieldDat);
    }
}
