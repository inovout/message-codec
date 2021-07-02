package org.inovout.message.codec.field.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.inovout.message.BlockType;
import org.inovout.message.codec.*;
import org.inovout.message.codec.field.PartLength;
import org.inovout.message.codec.impl.MessageBlockImpl;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.data.impl.MessageFieldDataImpl;
import org.inovout.message.exception.CodecException;
import org.inovout.message.validator.BlockLengthValidator;

@Getter
public class MessageLengthImpl
        extends MessageFieldImpl
        implements MessageLength, BlockLengthValidator {
    @NonNull
    private PartLength partlength;
    @Setter
    private MessageHeader header;

    protected MessageLengthImpl(@NonNull PartLength partLength,
                                @NonNull ContractName name,
                                @NonNull BlockValue value,
                                @NonNull CodecConverter<? extends Number, ? extends Number> converter) {
        super(name,
                value,
                converter,
                new MessageBlock[]{value}
        );
        this.partlength = partLength;
    }


    public static MessageLengthImpl of(@NonNull PartLength partlength,
                                       @NonNull String name,
                                       @NonNull CodecConverter<? extends Number, ? extends Number> converter) {

        return new MessageLengthImpl(partlength,
                ContractName.of(name, name),
                BlockValue.of(converter.getCodec(), ContractLength.of(converter.getBlockType().getLength())),
                converter);
    }

    @Override
    public void encode(EncodeContext context) throws CodecException {
        int length = context.getByteBuffer().readableBytes();
        BlockType blockType = super.converter.getBlockType();
        switch (this.partlength) {
            case BODY:
                length = context.getBodyBuffer().length;
                break;
            case HEADER:
                length += blockType.getLength();
                break;
            case PACKET:
                length += context.getBodyBuffer().length + blockType.getLength();
        }
        byte[] buffer = MessageBlockImpl.encode(super.converter.getCodec(),
                this.toBlock(length),
                context);

        context.getByteBuffer().writeBytes(buffer);
    }

    private <Block extends Number> Block toBlock(int data) {
        Number lengthData = Integer.valueOf(data);
        BlockType blockType = super.converter.getBlockType();
        if (BlockType.BYTE.equals(blockType)) {
            lengthData = Byte.valueOf(lengthData.toString());
        } else if (BlockType.SHORT.equals(blockType)) {

            lengthData = Short.valueOf(lengthData.toString());
        } else if (BlockType.LONG.equals(blockType)) {
            lengthData = Long.valueOf(lengthData.toString());
        }
        return (Block) toBlock(super.converter, lengthData);

    }

    protected int getMessageLength(DecodeContext context) {
        MessageFieldDataImpl<Number> data = (MessageFieldDataImpl<Number>) context.getFieldData(this);
        return (int) Integer.valueOf(data.getData().toString());
    }

    @Override
    public boolean validate(DecodeContext context) {

        if (this.getMessageLength(context) != this.getDecodedPartLength(context)) {
            context.getErrors().add("消息长度有误！");
            return false;
        }
        return true;
    }


    protected int getDecodedPartLength(DecodeContext context) {
        int length = 0;
        switch (this.partlength) {
            case BODY:
                length = this.header.getPacket().getBody().getBufferLength(context);
                break;
        }
        return length;

    }

}
