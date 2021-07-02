package org.inovout.message.codec.impl;


import lombok.NonNull;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.codec.block.Codecs;

public class MessageTag
        extends MessageBlockImpl
        implements MessageBlock {
    @NonNull
    private byte[] tag;

    public MessageTag(byte[] tag) {
        super(Codecs.BYTE_ARRAY,
                context -> tag.length,
                context -> tag,
                tag);
        this.tag = tag;
    }

    public static MessageTag of(byte[] tag) {
        return new MessageTag(tag);
    }
}
