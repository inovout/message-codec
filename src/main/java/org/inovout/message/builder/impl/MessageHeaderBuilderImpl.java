package org.inovout.message.builder.impl;

import lombok.NonNull;
import org.inovout.message.DefaultFieldNames;
import org.inovout.message.FieldType;
import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;
import org.inovout.message.builder.MessageFieldBuilder;
import org.inovout.message.builder.MessageHeaderBuilder;
import org.inovout.message.codec.MessageField;
import org.inovout.message.codec.MessageHeader;
import org.inovout.message.codec.field.PartLength;
import org.inovout.message.codec.field.impl.MessageLengthImpl;
import org.inovout.message.codec.field.impl.MessageTypeImpl;
import org.inovout.message.codec.field.impl.MessageVersionImpl;
import org.inovout.message.codec.impl.MessageHeaderImpl;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.converter.CodecConverters;

import java.util.HashMap;
import java.util.Map;

public class MessageHeaderBuilderImpl
        extends MessagePartBuilderImple<MessageHeader>
        implements MessageHeaderBuilder {


    private MessageVersion<?>[] versions;
    // private Map<MessageVersionType, MessageField[]> versionTypeFields;

    public MessageHeaderBuilderImpl() {
        this.versionTypes = new HashMap<>();
        //this.versionTypeFields = new HashMap<>();
    }

    @Override
    public MessageHeader build() {
        return MessageHeaderImpl.of(super.getBlocks()
        );
    }


    @Override
    public <N extends Number> MessageHeaderBuilder addMessageVersions(@NonNull String versionFieldName, @NonNull MessageVersion<N>... messageVersions) {
        this.versions = messageVersions;
        if (1 == messageVersions.length &&
                messageVersions[0].value() == MessageVersion.NO_MESSAGE_VERSION.value()) {
            return this;
        }
        MessageVersionImpl messageVersion = MessageVersionImpl.of(versionFieldName,
                messageVersions);
        this.addBlock(messageVersion);
        return this;
    }

    private Map<MessageVersion<?>, MessageType<?>[]> versionTypes;

    public <N extends Number> MessageHeaderBuilder addMessageTypes(@NonNull MessageVersion<?> version,
                                                                   @NonNull String typeFieldName,
                                                                   MessageType<N>... types) {
        if (null == this.versions) {
            this.addMessageVersions(DefaultFieldNames.MESSAGE_VERSION, version);
        }

        MessageTypeImpl messageType = MessageTypeImpl.of(typeFieldName, types);
        this.addBlock(messageType);

        this.versionTypes.put(version, types);


        return this;
    }


    @Override
    public MessageHeaderBuilder setBodyLength(@NonNull String fieldName, @NonNull FieldType type) {
        // BlockCodecConverter<? extends Number> codecConverter = (BlockCodecConverter<? extends Number>) super.getCodecConverter(type);
        return setBodyLength(fieldName, (CodecConverter<? extends Number, ? extends Number>) CodecConverters.getCodecConverter(type));
    }

    @Override
    public MessageHeaderBuilder setBodyLength(@NonNull String fieldName,
                                              @NonNull CodecConverter<? extends Number, ? extends Number> converter) {
        MessageLengthImpl bodylength =
                MessageLengthImpl.of(PartLength.BODY, fieldName, converter);

        super.addBlock(bodylength);

        return this;
    }

    @Override
    public MessageHeaderBuilder addMessageField(String name, String alias, FieldType type) {
        MessageField field = MessageFieldBuilder.create()
                .noStartTag()
                .name(name, alias)
                .value(type)
                .noEndTag();
        super.addBlock(field);
        return this;
    }


}
