package org.inovout.message.codec.impl;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.EncodeContext;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.data.MessageBlockData;
import org.inovout.message.exception.CodecException;

import java.util.function.Function;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class MessageBlockImpl
        implements MessageBlock {

    @NonNull
    private Codec<?> codec;
    private Function<DecodeContext, Integer> getBlockLength;
    private Function<EncodeContext, ?> getBlockValue;

    private byte[] exampleData;

    @Override
    public void decode(DecodeContext context) {
        MessageBlockData<?> data = this.decode(
                this.getBlockLength.apply(context),
                context);
        context.addBlockData(this, data);
    }

    protected MessageBlockData<?> decode(Integer length, DecodeContext context) {
        byte[] bytes = new byte[length];
        ByteBuf buf = context.getByteBuf();
        buf.readBytes(bytes);
        MessageBlockData<?> data = MessageBlockData.create(bytes,
                this.codec.decode(bytes, context.getSettings()));

        // MessageBlockData<?> data = MessageBlockData.create(bytes,
        //         this.codec, context);

        return data;
    }


    @Override
    public void encode(EncodeContext context) throws CodecException {
        byte[] buffer = encode(this.codec,
                this.getBlockValue,
                context);
        context.getByteBuffer().writeBytes(buffer);
    }

    public <T> byte[] encode(T data, EncodeContext context) throws CodecException {
        return encode(this.codec,
                ctx -> data,
                context);
    }

    public static <T> byte[] encode(Codec<?> codec, T data, EncodeContext context) throws CodecException {
        return encode(codec,
                ctx -> data,
                context);
    }

    private static <T> byte[] encode(Codec<T> codec, Function<EncodeContext, ?> getValue, EncodeContext context) throws CodecException {
        T value = (T) getValue.apply(context);
        return codec.encode((T) value, context.getSettings());
    }

}
