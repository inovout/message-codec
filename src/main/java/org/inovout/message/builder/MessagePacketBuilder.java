package org.inovout.message.builder;

import lombok.NonNull;
import org.inovout.message.builder.action.BodyAction;
import org.inovout.message.builder.action.HeaderAction;
import org.inovout.message.builder.action.PacketAction;
import org.inovout.message.builder.impl.MessagePacketBuilderImpl;
import org.inovout.message.codec.MessageBody;
import org.inovout.message.codec.MessageHeader;
import org.inovout.message.codec.MessagePacket;
import org.inovout.message.codec.check.crc.Crc;
import org.inovout.message.codec.check.crc.CrcBlock;
import org.inovout.message.codec.impl.MessageTag;

import java.util.function.Function;

public interface MessagePacketBuilder
        extends MessagePartBuilder<MessagePacket>,
        PacketAction.Start,
        PacketAction.First,
        PacketAction.HeaderCreateAction,
        PacketAction.BodyCreateAction,
        PacketAction.End {


    MessagePacketBuilder header(MessageHeader header);

    MessagePacketBuilder body(MessageBody body);

    @Override
    default PacketAction.BodyCreateAction noHeader() {
        return this;
    }

    @Override
    default PacketAction.BodyCreateAction header(Function<HeaderAction.Start, MessageHeader> buildAction) {
        MessageHeader header = buildAction.apply(MessageHeaderBuilder.create());
        this.header(header);
        return this;
    }

    @Override
    default PacketAction.End noBody() {
        return this;
    }

    @Override
    default PacketAction.End body(Function<BodyAction.Start, MessageBody> buildAction) {
        MessageBody body = buildAction.apply(MessageBodyBuilder.create());
        this.body(body);
        return this;
    }

    default PacketAction.End crc16() {
        this.addBlock(new CrcBlock(Crc.CRC16));
        return this;
    }

    default PacketAction.First startTag(@NonNull byte[] tag) {
        if (0 < tag.length) {
            this.addBlock(MessageTag.of(tag));
        }
        return this;
    }

    default MessagePacket endTag(@NonNull byte[] tag) {
        if (0 < tag.length) {
            // this.addBlock(MessageTag.of(tag));
        }
        return this.build();
    }

    public static PacketAction.Start create() {
        return new MessagePacketBuilderImpl();
    }
}
