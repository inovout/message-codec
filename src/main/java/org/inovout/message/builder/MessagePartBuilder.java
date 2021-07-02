package org.inovout.message.builder;

import lombok.NonNull;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.codec.MessagePart;
import org.inovout.message.codec.impl.MessageTag;

public interface MessagePartBuilder<Part extends MessagePart> {

    void addBlock(@NonNull MessageBlock block);

    Part build();


}
