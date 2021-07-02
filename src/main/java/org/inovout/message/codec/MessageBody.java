package org.inovout.message.codec;

import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;
import org.inovout.message.codec.impl.MessagePacketImpl;

public interface MessageBody extends MessagePart {

    int getBufferLength(DecodeContext context);

    void setPacket(MessagePacket messagePacket);

    boolean validate(DecodeContext context);
}
