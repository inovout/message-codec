package org.inovout.message.builder.action;

import org.inovout.message.codec.MessageBody;
import org.inovout.message.codec.MessageHeader;
import org.inovout.message.codec.MessagePacket;

import java.util.function.Function;

public interface PacketAction extends BuildAction {

    public interface First extends HeaderCreateAction {
    }

    public interface Start extends StartAction<First> {
    }

    public interface HeaderCreateAction extends BuildAction {
        BodyCreateAction noHeader();

        BodyCreateAction header(Function<HeaderAction.Start, MessageHeader> buildAction);

    }

    public interface BodyCreateAction extends BuildAction {

        End noBody();

        End body(Function<BodyAction.Start, MessageBody> buildAction);
    }


    public interface End extends EndAction<MessagePacket> {
        End crc16();
    }
}
