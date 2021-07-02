package org.inovout.message.codec;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.inovout.message.CodecSettings;
import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class EncodeContext {
    @NonNull
    private CodecSettings settings;
    private List<String> errors = new ArrayList<>();
    @NonNull
    private Map<String, ?> data;
    @Setter
    ByteBuf byteBuffer;
    @Setter
    private byte[] bodyBuffer = new byte[0];
    @Setter
    private byte[] headerBuffer = new byte[0];
    @Setter
    private byte[] startTagBuffer = new byte[0];

    @Getter
    @Setter
    private org.inovout.message.MessageVersion<?> messageVersion = MessageVersion.NO_MESSAGE_VERSION;
    @Getter
    @Setter
    private MessageType<?> messageType;


    public byte[] getEncodedBuffer() {

        ByteBuf buf = this.getByteBuffer();

        byte[] endTagBuffer = new byte[buf.readableBytes()];
        buf.readBytes(endTagBuffer);

        byte[] buffer = Arrays.copyOf(startTagBuffer,
                startTagBuffer.length + headerBuffer.length +
                        bodyBuffer.length + endTagBuffer.length);
        System.arraycopy(headerBuffer, 0, buffer, startTagBuffer.length, headerBuffer.length);
        System.arraycopy(bodyBuffer, 0, buffer, startTagBuffer.length + headerBuffer.length, bodyBuffer.length);
        System.arraycopy(endTagBuffer, 0, buffer, startTagBuffer.length + headerBuffer.length + bodyBuffer.length, endTagBuffer.length);
        return buffer;
    }
}
