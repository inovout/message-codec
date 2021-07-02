package org.inovout.message.data;

import lombok.NonNull;
import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.block.Decoder;
import org.inovout.message.data.impl.MessageBlockDataImpl;

public interface MessageBlockData<B> {

    byte[] getBufferData();

    default int getBufferLength() {
        return this.getBufferData().length;
    }

    B getBlockData();

    @NonNull
    public static <T> MessageBlockData<T> create(byte bytes[], T data) {

        return new MessageBlockDataImpl<T>(bytes, data);
    }

    public static MessageBlockData<?> create(byte[] bytes, Decoder<?> decoder,
                                             DecodeContext context) {

        Class<?> type = decoder == null ? byte[].class : decoder.getType();
        if (Byte.class.equals(type)) {
            return new MessageBlockDataImpl<Byte>(bytes,
                    (Byte) decoder.decode(bytes, context.getSettings()));
        } else if (Short.class.equals(type)) {
            return new MessageBlockDataImpl<Short>(bytes,
                    (Short) decoder.decode(bytes, context.getSettings()));

        } else if (Integer.class.equals(type)) {
            return new MessageBlockDataImpl<Integer>(bytes,
                    (Integer) decoder.decode(bytes, context.getSettings()));

        } else if (Long.class.equals(type)) {
            return new MessageBlockDataImpl<Long>(bytes,
                    (Long) decoder.decode(bytes, context.getSettings()));

        } else if (Float.class.equals(type)) {
            return new MessageBlockDataImpl<Float>(bytes,
                    (Float) decoder.decode(bytes, context.getSettings()));

        } else if (Double.class.equals(type)) {
            return new MessageBlockDataImpl<Double>(bytes,
                    (Double) decoder.decode(bytes, context.getSettings()));

        } else if (byte[].class.equals(type)) {
            return new MessageBlockDataImpl<byte[]>(bytes, bytes);
        } else if (CharSequence.class.equals(type)) {
            return new MessageBlockDataImpl<CharSequence>(bytes,
                    (CharSequence) decoder.decode(bytes, context.getSettings()));

        } else {
            return null;
        }
    }


}
