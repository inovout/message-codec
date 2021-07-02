package org.inovout.message.codec.field.impl;

import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.EncodeContext;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.codec.field.FieldLength;
import org.inovout.message.codec.field.FieldValue;
import org.inovout.message.codec.impl.MessageBlockImpl;
import org.inovout.message.data.MessageBlockData;
import org.inovout.message.exception.CodecException;

@Getter
public class BlockValue
        extends MessageBlockImpl
        implements FieldValue, MessageBlock {
    @NonNull
    private FieldLength length;

    public BlockValue(@NonNull Codec<?> codec, @NonNull FieldLength length, byte[] exampleData) {
        super(codec, length::getLength, null, exampleData);
        this.length = length;
    }

    public static BlockValue of(@NonNull Codec<? extends Number> codec) {
        return of(codec, ContractLength.of(codec.getBlockType().getLength()));
    }

    public static BlockValue of(@NonNull Codec<?> codec, @NonNull FieldLength length) {

        return new BlockValue(codec, length, null);
    }


    @Override
    public MessageBlockData<?> read(DecodeContext context) {
        MessageBlockData<?> data = super.decode(this.length.getLength(context), context);
        context.addBlockData(this, data);
        return data;
    }


    @Override
    public <T> void write(T data, EncodeContext context) throws CodecException {
        byte[] buffer = super.encode(data, context);
        context.getByteBuffer().writeBytes(buffer);
    }

}
