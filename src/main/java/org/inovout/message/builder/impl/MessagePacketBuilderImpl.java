package org.inovout.message.builder.impl;

import org.inovout.message.builder.MessagePacketBuilder;
import org.inovout.message.codec.MessageBody;
import org.inovout.message.codec.MessageHeader;
import org.inovout.message.codec.MessagePacket;
import org.inovout.message.codec.impl.MessagePacketImpl;

public class MessagePacketBuilderImpl
        extends MessagePartBuilderImple<MessagePacket>
        implements MessagePacketBuilder {


    private MessageHeader header;
    private MessageBody body;

    @Override
    public MessagePacketBuilder header(MessageHeader header) {
        this.header = header;
        super.addBlock(header);
        return this;
    }

    @Override
    public MessagePacketBuilder body(MessageBody body) {
        this.body = body;
        super.addBlock(body);
        return this;
    }

    @Override
    public MessagePacket build() {

        MessagePacket packet = MessagePacketImpl.of(super.getBlocks());

        return packet;
    }


}
