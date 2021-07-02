package org.inovout.message.builder.impl;


import lombok.NonNull;
import org.inovout.message.builder.MessagePartBuilder;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.codec.MessagePart;
import org.inovout.message.converter.CodecConverterProvider;
import org.inovout.message.converter.impl.BlockCodecConverter;

import java.util.ArrayList;
import java.util.List;

public abstract class MessagePartBuilderImple<Part extends MessagePart>
        implements MessagePartBuilder<Part> {

    private List<MessageBlock> blocks;

    protected MessageBlock[] getBlocks() {
        return (MessageBlock[]) this.blocks.toArray(new MessageBlock[this.blocks.size()]);
    }

    protected MessagePartBuilderImple() {
        this.blocks = new ArrayList<MessageBlock>();
    }

    @Override
    public void addBlock(@NonNull MessageBlock block) {
        this.blocks.add(block);
    }


    protected BlockCodecConverter<?> getCodecConverter(Class<?> type) {
        return (BlockCodecConverter<?>) CodecConverterProvider.INSTANCE.getCodecConverter(type);
    }
}
