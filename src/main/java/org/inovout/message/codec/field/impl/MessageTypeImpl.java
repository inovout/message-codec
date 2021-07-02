package org.inovout.message.codec.field.impl;

import lombok.NonNull;
import lombok.Setter;
import org.inovout.message.MessageType;
import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.EncodeContext;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.codec.MessageHeader;
import org.inovout.message.codec.impl.MessageHeaderImpl;
import org.inovout.message.converter.CodecConverters;
import org.inovout.message.converter.impl.ContractCodecConverter;
import org.inovout.message.data.MessageFieldData;
import org.inovout.message.exception.CodecException;

public class MessageTypeImpl extends MessageFieldImpl
        implements org.inovout.message.codec.MessageType {

    @NonNull
    private MessageType<?>[] messageTypes;

    protected MessageTypeImpl(@NonNull ContractName name,
                              BlockValue value,
                              @NonNull ContractCodecConverter<? extends MessageType<?>, ?> converter) {
        super(name, value, converter, new MessageBlock[]{value});
        this.messageTypes = converter.getValues();
    }

    public static <N extends Number> MessageTypeImpl of(@NonNull String fieldName, @NonNull MessageType<N>[] types) {

        ContractCodecConverter<MessageType<N>, N> converter = CodecConverters.CONTRACT.create(types);

        return new MessageTypeImpl(
                ContractName.of(fieldName, fieldName),
                BlockValue.of(converter.getCodec(), ContractLength.of(converter.getBlockType().getLength())),
                converter

        );
    }

    @Override
    protected MessageFieldData<?> decodeField(DecodeContext context) throws CodecException {
        MessageFieldData<?> fieldData = super.decodeField(context);
        context.setMessageType((MessageType<?>) fieldData.getData());
        return fieldData;
    }

    @Override
    public void setType(EncodeContext context) {
        context.setMessageType((MessageType<?>) super.getFieldData(context));
    }


    @Setter
    private MessageHeader header;

}

