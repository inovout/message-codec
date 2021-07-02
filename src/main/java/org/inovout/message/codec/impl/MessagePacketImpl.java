package org.inovout.message.codec.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.java.Log;
import org.inovout.message.CodecSettings;
import org.inovout.message.codec.*;
import org.inovout.message.data.MessagePacketData;
import org.inovout.message.data.impl.MessagePacketDataImpl;
import org.inovout.message.exception.CodecException;

import java.util.Map;

@Log
public class MessagePacketImpl
        extends MessagePartImpl
        implements MessagePacket {
    @Getter
    private MessageHeader header;
    @Getter
    private MessageBody body;


    public MessagePacketImpl(@NonNull MessageBlock[] blocks) {
        super(blocks);
        for (MessageBlock block : blocks) {
            if (block instanceof MessageHeader) {
                this.header = (MessageHeader) block;

                header.setPacket(this);
            } else if (block instanceof MessageBody) {

                this.body = (MessageBody) block;
                body.setPacket(this);
            }
        }


    }

    public static MessagePacket of(MessageBlock[] blocks) {
        return new MessagePacketImpl(blocks);

    }


    @Override
    public byte[] encode(Map<String, ?> data) throws CodecException {
        return encode(data, new CodecSettings());
    }

    public byte[] encode(Map<@NonNull String, ?> data, CodecSettings settings) throws CodecException {
        EncodeContext context = new EncodeContext(settings, data);

        if (null != this.header) {
            this.header.setMessageVersionAndType(context);
        }
        ByteBuf buf = Unpooled.buffer(context.getSettings().getBufferSize());
        context.setByteBuffer(buf);
        super.encode(context);

        //  byte[] endTagBuffer = new byte[buf.readableBytes()];
        //  buf.readBytes(endTagBuffer);

        byte[] encodedBuffer = context.getEncodedBuffer();
        //  byte[] buffer = Arrays.copyOf(encodedBuffer,
        //         encodedBuffer.length + endTagBuffer.length);
        //  System.arraycopy(endTagBuffer, 0, buffer, encodedBuffer.length, encodedBuffer.length);

        return encodedBuffer;
    }

    @Override
    protected void encodePart(@NonNull EncodeContext context) throws CodecException {
        ByteBuf buf = context.getByteBuffer();
        byte[] startTag = new byte[buf.readableBytes()];
        buf.readBytes(startTag);
        context.setStartTagBuffer(startTag);
        if (null != this.body) {
            buf.readerIndex(0);
            buf.writerIndex(0);
            this.body.encode(context);
            byte[] bodyBuffer = new byte[buf.readableBytes()];
            buf.readBytes(bodyBuffer);
            context.setBodyBuffer(bodyBuffer);
        }
        if (null != this.header) {
            buf.readerIndex(0);
            buf.writerIndex(0);
            this.header.encode(context);
            byte[] headerBuffer = new byte[buf.readableBytes()];
            buf.readBytes(headerBuffer);
            context.setHeaderBuffer(headerBuffer);
        }
        buf.readerIndex(0);
        buf.writerIndex(0);
    }


    @Override
    public MessagePacketData decode(@NonNull byte[] buffer) throws CodecException {
        return this.decode(Unpooled.wrappedBuffer(buffer));
    }

    public MessagePacketData decode(@NonNull ByteBuf buffer) throws CodecException {
        return this.decode(buffer, new CodecSettings());
    }


    public MessagePacketData decode(ByteBuf buffer, CodecSettings settings) throws CodecException {
        MessagePacketData result = new MessagePacketDataImpl();
        DecodeContext context = DecodeContext.of(settings, buffer, result);
        super.decode(context);
        super.validate(context);
        return result;
    }
}
