package org.inovout.message.codec;

import org.inovout.message.codec.impl.MessageHeaderImpl;

public interface MessageType extends MessageField {
    void setType(EncodeContext context);

    void setHeader(MessageHeader messageHeader);
}
