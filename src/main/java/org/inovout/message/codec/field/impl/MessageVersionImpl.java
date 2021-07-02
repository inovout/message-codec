package org.inovout.message.codec.field.impl;


import lombok.NonNull;
import org.inovout.message.MessageVersion;
import org.inovout.message.codec.EncodeContext;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.converter.CodecConverters;
import org.inovout.message.converter.impl.ContractCodecConverter;

public class MessageVersionImpl
        extends MessageFieldImpl
        implements org.inovout.message.codec.MessageVersion {

    @NonNull
    private MessageVersion<?>[] versions;

    protected MessageVersionImpl(@NonNull ContractName name,
                                 BlockValue value,
                                 @NonNull ContractCodecConverter<? extends MessageVersion<?>, ?> converter) {
        super(name, value, converter, new MessageBlock[]{value});
        this.versions = converter.getValues();
    }

    public static <N extends Number> MessageVersionImpl of(@NonNull String fieldName, @NonNull MessageVersion<N>[] version) {
        ContractCodecConverter<MessageVersion<N>, N> converter = CodecConverters.CONTRACT.create(version);

        return new MessageVersionImpl(
                ContractName.of(fieldName, fieldName),
                BlockValue.of(converter.getCodec(), ContractLength.of(converter.getBlockType().getLength())),
                converter

        );
    }


    @Override
    public void setVersion(EncodeContext context) {
        Object version = super.getFieldData(context);
        if (null != version) {
            context.setMessageVersion((MessageVersion<?>) version);
        }
    }
}
