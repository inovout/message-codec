package org.inovout.message.builder;

import lombok.NonNull;
import org.inovout.message.DefaultFieldNames;
import org.inovout.message.FieldType;
import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;
import org.inovout.message.builder.action.HeaderAction;
import org.inovout.message.builder.impl.MessageHeaderBuilderImpl;
import org.inovout.message.codec.MessageHeader;
import org.inovout.message.codec.impl.MessageTag;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.converter.impl.BlockCodecConverter;

public interface MessageHeaderBuilder
        extends MessagePartBuilder<MessageHeader>,
        HeaderAction.Start,
        HeaderAction.First,
        HeaderAction.End {

    @NonNull <N extends Number> MessageHeaderBuilder addMessageVersions(String versionFieldName, MessageVersion<N>... versions);

    <N extends Number> MessageHeaderBuilder addMessageTypes(@NonNull MessageVersion<?> version,
                                                            @NonNull String typeFieldName,
                                                            @NonNull MessageType<N>... types);

    @NonNull
    default <N extends Number> HeaderAction messageType(MessageVersion<?> version, String typeFieldName, MessageType<N>... types) {
        this.addMessageTypes(version,
                typeFieldName,
                types);
        return this;
    }

    default HeaderAction packetLength(String fieldName, Class<? extends Number> type) {
        return this;
    }


    MessageHeaderBuilder setBodyLength(@NonNull String fieldName, @NonNull FieldType type);

    MessageHeaderBuilder setBodyLength(@NonNull String fieldName, @NonNull CodecConverter<? extends Number, ? extends Number> converter);

    MessageHeaderBuilder addMessageField(String name, String alias, FieldType type);

    @Override
    default HeaderAction field(String name, String alias, FieldType type) {
        this.addMessageField(name, alias, type);
        return this;
    }

    @Override
    default HeaderAction bodyLength(@NonNull String fieldName, @NonNull FieldType type) {
        setBodyLength(DefaultFieldNames.BODY_LENGTH, type);
        return this;
    }


    @Override
    default HeaderAction bodyLength(@NonNull String fieldName, @NonNull BlockCodecConverter<? extends Number> converter) {
        setBodyLength(DefaultFieldNames.BODY_LENGTH, converter);
        return this;
    }

    default First startTag(@NonNull byte[] tag) {
        if (0 < tag.length) {
            this.addBlock(MessageTag.of(tag));
        }
        return this;
    }

    default MessageHeader endTag(@NonNull byte[] tag) {
        if (0 < tag.length) {
            this.addBlock(MessageTag.of(tag));
        }
        return this.build();
    }

    public static Start create() {
        return new MessageHeaderBuilderImpl();
    }


}
