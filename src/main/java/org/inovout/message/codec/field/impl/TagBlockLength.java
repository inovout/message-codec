package org.inovout.message.codec.field.impl;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.EncodeContext;
import org.inovout.message.codec.block.Codecs;
import org.inovout.message.codec.field.FieldBlockLength;
import org.inovout.message.codec.field.FieldLength;
import org.inovout.message.codec.impl.MessageTag;

import java.util.Arrays;

public class TagBlockLength
        extends MessageTag
        implements FieldLength, FieldBlockLength {

    @NonNull
    private byte[] tag;

    public TagBlockLength(@NonNull byte[] tag) {
        super(tag);
        this.tag = tag;
    }

    public static TagBlockLength of(byte[] tag) {
        return new TagBlockLength(tag);
    }


    @Override
    public Integer getLength(DecodeContext context) {
        return this.decodeLength(context);
    }

    @Override
    public void setLength(EncodeContext context) {

    }

    private int decodeLength(DecodeContext context) {
        byte[] bytes = new byte[this.tag.length];
        ByteBuf buf = context.getByteBuf();
        int positon = buf.readerIndex();
        int length = buf.readableBytes();

        while (0 < this.tag.length && 0 < buf.readableBytes()) {
            buf.readBytes(bytes);
            if (Arrays.equals(this.tag, bytes)) {
                length = buf.readerIndex() - positon;
                break;
            }
        }
        buf.readerIndex(positon);
        return length;
    }
}