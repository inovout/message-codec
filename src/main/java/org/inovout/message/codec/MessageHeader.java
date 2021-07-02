package org.inovout.message.codec;

public interface MessageHeader extends MessagePart {
    // MessageLength getMessageLength();

    void setMessageVersionAndType(EncodeContext context);

    MessagePacket getPacket();
    void setPacket(MessagePacket messagePacket);

    boolean validate(DecodeContext context);
}
