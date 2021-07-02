package org.inovout.message.codec.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;
import org.inovout.message.codec.*;
import org.inovout.message.data.MessageBlockData;
import org.inovout.message.exception.CodecException;
import org.inovout.message.validator.BlockValidator;

import java.util.Map;

public class MessageBodyImpl
        extends MessagePartImpl
        implements MessageBody {

    @NonNull
    private Map<MessageVersionType, MessageField[]> versionTypeFields;


    public MessageBodyImpl(Map<MessageVersionType, MessageField[]> versionTypeFields,
                           MessageBlock[] blocks) {
        super(blocks);
        this.versionTypeFields = versionTypeFields;

    }

    public static MessageBodyImpl of(Map<MessageVersionType, MessageField[]> versionTypeFields,
                                     MessageBlock[] blocks) {
        return new MessageBodyImpl(versionTypeFields, blocks);

    }

    @Override
    protected void encodePart(EncodeContext context) throws CodecException {
        MessageField[] fields = getFields(context.getMessageVersion(), context.getMessageType());
        for (MessageField field : fields) {
            field.encode(context);
        }
    }

    @Override
    protected void decodePart(DecodeContext context) throws CodecException {
        MessageField[] fields = getFields(context.getMessageVersion(), context.getMessageType());
        for (MessageField field : fields) {
            field.decode(context);
        }
    }

    @NonNull
    public MessageField[] getFields(MessageVersion<?> messageVersion, MessageType<?> messageType) {
        return this.versionTypeFields.get(MessageVersionType.of(messageVersion, messageType));
    }

    @Override
    public int getBufferLength(DecodeContext context) {
        int length = 0;
        for (MessageField field : getFields(context.getMessageVersion(), context.getMessageType())) {
            length += field.getBufferLength(context);
        }
        return length;
    }

    @Getter
    @Setter
    public MessagePacket packet;


}
