package org.inovout.message.codec.field.impl;

import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.EncodeContext;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.codec.MessageField;
import org.inovout.message.codec.field.FieldLength;
import org.inovout.message.codec.field.FieldName;
import org.inovout.message.codec.field.FieldValue;
import org.inovout.message.codec.impl.MessagePartImpl;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.data.MessageBlockData;
import org.inovout.message.data.MessageFieldData;
import org.inovout.message.exception.CodecException;

@Getter
public class MessageFieldImpl
        extends MessagePartImpl
        implements MessageField, MessageBlock {

    @NonNull
    private FieldName name;
    @NonNull FieldLength length;
    @NonNull
    private FieldValue value;
    @NonNull
    protected CodecConverter<?, ?> converter;

    protected MessageFieldImpl(@NonNull FieldName name,
                               @NonNull FieldValue value,
                               @NonNull CodecConverter<?, ?> converter,
                               MessageBlock[] blocks) {
        super(blocks);
        this.name = name;
        this.value = value;
        this.length = value.getLength();
        this.converter = converter;
    }


    public static MessageFieldImpl of(@NonNull FieldName name,
                                      @NonNull FieldValue value,
                                      @NonNull CodecConverter<?, ?> converter,
                                      MessageBlock[] blocks) {

        return new MessageFieldImpl(name, value, converter, blocks);
    }

    @Override
    public void encode(EncodeContext context) throws CodecException {

        this.value.write(toBlock(this.converter, getFieldData(context)),
                context);
    }

    protected Object getFieldData(EncodeContext context) {
        String name = this.name.getName(context);
        return context.getData().get(name);
    }

    <Field, Block, Data>
    Block toBlock(CodecConverter<Field, Block> converter, Data data) {
        return converter.toBlock((Field) data);
    }

    @Override
    public void decode(DecodeContext context) throws CodecException {
        MessageFieldData<?> fieldData = this.decodeField(context);
        context.addFiledData(this, fieldData);

    }

    protected MessageFieldData<?> decodeField(DecodeContext context) throws CodecException {
        String name = this.name.getName(context);
        String alias = this.name.getAlias();
        MessageBlockData<?> valueBlockData = this.value.read(context);
        return MessageFieldData.create(name, this,
                this.converter, valueBlockData);

    }

    protected MessageFieldData<?> getFieldData(DecodeContext context) {
        return context.getFieldData(this);
    }


    @Override
    public int getBufferLength(DecodeContext context) {
        int length = 0;
        for (MessageBlock block : super.getBlocks()) {
            MessageBlockData<?> data = context.getBlockData(block);
            if (null != data) {
                length += data.getBufferLength();
            }
        }
        return length;
    }
}
