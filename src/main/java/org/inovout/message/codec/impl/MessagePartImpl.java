package org.inovout.message.codec.impl;

import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.EncodeContext;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.codec.MessagePart;
import org.inovout.message.exception.CodecException;
import org.inovout.message.validator.BlockValidator;

import java.util.ArrayList;
import java.util.List;

public abstract class MessagePartImpl
        implements MessagePart {

    @NonNull
    @Getter
    private MessageBlock[] blocks;

    private MessageBlock[] start;
    @NonNull
    private MessageBlock[] part;
    private MessageBlock[] end;

    public MessagePartImpl(MessageBlock[] blocks) {
        this.blocks = blocks;
        List<MessageBlock> startList = new ArrayList<>();
        List<MessageBlock> partList = new ArrayList<>();
        List<MessageBlock> endList = new ArrayList<>();

        for (MessageBlock block : blocks) {
            if (block instanceof MessagePart) {
                partList.add(block);
            } else {
                if (0 == partList.size()) {
                    startList.add(block);
                } else {
                    endList.add(block);
                }
            }
        }
        this.start = new MessageBlock[startList.size()];
        startList.toArray(this.start);
        this.part = new MessageBlock[partList.size()];
        partList.toArray(this.part);
        this.end = new MessageBlock[endList.size()];
        endList.toArray(this.end);

    }

    @Override
    public void decode(DecodeContext context) throws CodecException {
        for (MessageBlock block : this.start) {

            block.decode(context);
        }

        this.decodePart(context);
        for (
                MessageBlock block : this.end) {

            block.decode(context);
        }

    }


    protected void decodePart(DecodeContext context) throws CodecException {
        for (MessageBlock block : part) {
            block.decode(context);
        }
    }

    protected void encodePart(EncodeContext context) throws CodecException {
        for (MessageBlock block : part) {
            block.encode(context);
        }
    }

    public boolean validate(DecodeContext context) {
        boolean result = true;
        for (MessageBlock block : blocks) {
            if (block instanceof BlockValidator) {
                result &= ((BlockValidator) block).validate(context);
            }
        }
        return result;
    }


    @Override
    public void encode(EncodeContext context) throws CodecException {

        for (MessageBlock block : this.start) {
            block.encode(context);
        }
        this.encodePart(context);
        for (MessageBlock block : this.end) {
            block.encode(context);
        }
    }
}
