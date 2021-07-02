package org.inovout.message.builder.action;

import lombok.NonNull;
import org.inovout.message.DefaultFieldNames;
import org.inovout.message.FieldType;
import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;
import org.inovout.message.codec.MessageHeader;
import org.inovout.message.converter.impl.BlockCodecConverter;

public interface HeaderAction extends EndAction<MessageHeader>, BuildAction {


    @NonNull <N extends Number> HeaderAction messageType(MessageVersion<?> version, String typeFieldName, MessageType<N>... values);

    default <N extends Number> HeaderAction messageType(MessageType<N>... values) {
        return messageType(MessageVersion.NO_MESSAGE_VERSION, DefaultFieldNames.MESSAGE_TYPE, values);
    }

    HeaderAction field(String name, String alias, FieldType type);

    default HeaderAction field(String name, FieldType type) {
        return field(name, name, type);
    }

    HeaderAction packetLength(String fieldName, Class<? extends Number> type);

    default HeaderAction packetLength(Class<? extends Number> type) {
        return packetLength(DefaultFieldNames.PACKET_LENGTH, type);
    }


    HeaderAction bodyLength(String fieldName, FieldType type);

    HeaderAction bodyLength(String fieldName, BlockCodecConverter<? extends Number> codec);

    default HeaderAction bodyLength(FieldType type) {
        return bodyLength(DefaultFieldNames.BODY_LENGTH, type);
    }

    default HeaderAction bodyLength(Class<? extends Number> type) {
        return bodyLength(DefaultFieldNames.BODY_LENGTH, FieldType.getFieldType(type));
    }

    default HeaderAction bodyLength(BlockCodecConverter<? extends Number> converter) {
        return bodyLength(DefaultFieldNames.BODY_LENGTH, converter);
    }


    public interface First extends HeaderAction {
    }

    public interface Start extends StartAction<First> {
    }


    public interface End extends EndAction<MessageHeader> {
    }
}

