package org.inovout.message.builder;

import lombok.NonNull;
import org.inovout.message.MessageField;
import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;
import org.inovout.message.builder.action.BodyAction;
import org.inovout.message.builder.impl.MessageBodyBuilderImpl;
import org.inovout.message.codec.MessageBody;
import org.inovout.message.codec.impl.MessageTag;

public interface MessageBodyBuilder
        extends MessagePartBuilder<MessageBody>,
        BodyAction.Start,
        BodyAction.First,
        BodyAction.FieldAndEndAction,
        BodyAction.FieldAction,
        BodyAction.End {

    MessageBody build();

    MessageBodyBuilder addMessageFields(MessageVersion<?> version,
                                        MessageType<?> type,
                                        MessageField[] fields);


    default BodyAction.FieldAndEndAction typeFields(MessageVersion<? extends Number> version,
                                                    MessageType<?> type,
                                                    MessageField[] fields) {
        this.addMessageFields(version, type, fields);
        return this;
    }

    default BodyAction.First startTag(@NonNull byte[] tag) {
        if (0 < tag.length) {
            this.addBlock(MessageTag.of(tag));
        }
        return this;
    }

    default MessageBody endTag(@NonNull byte[] tag) {
        if (0 < tag.length) {
            // this.addBlock(MessageTag.of(tag));
        }
        return this.build();
    }

    public static BodyAction.Start create() {
        return new MessageBodyBuilderImpl();
    }

}
