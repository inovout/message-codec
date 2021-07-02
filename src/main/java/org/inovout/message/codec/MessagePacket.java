package org.inovout.message.codec;

import io.netty.buffer.ByteBuf;
import org.inovout.message.data.MessagePacketData;
import org.inovout.message.exception.CodecException;

import java.util.Map;

public interface MessagePacket extends MessagePart {

    MessageBody getBody();

    MessageHeader getHeader();

    MessagePacketData decode(byte[] buffer) throws CodecException;

    byte[] encode(Map<String, ?> data) throws CodecException;


}
