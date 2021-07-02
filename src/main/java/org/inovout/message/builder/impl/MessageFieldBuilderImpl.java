package org.inovout.message.builder.impl;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.inovout.message.ContractFieldValue;
import org.inovout.message.FieldType;
import org.inovout.message.builder.MessageFieldBuilder;
import org.inovout.message.codec.MessageBlock;
import org.inovout.message.codec.MessageField;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.codec.field.FieldLength;
import org.inovout.message.codec.field.FieldName;
import org.inovout.message.codec.field.FieldValue;
import org.inovout.message.codec.field.impl.*;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.converter.CodecConverters;

@Log
public class MessageFieldBuilderImpl
        extends MessagePartBuilderImple<MessageField>
        implements MessageFieldBuilder {


    private FieldName name;

    @Override
    public MessageFieldBuilder setName(@NonNull String name, String alias) {
        this.name = ContractName.of(name, alias);
        return this;
    }


    public MessageFieldBuilder setName(String name) {
        return setName(name, name);
    }

    private FieldLength length;
    private FieldValue value;

    private MessageFieldBuilder setLength(int len) {
        this.length = ContractLength.of(len);
        return this;
    }

    @Override
    public MessageFieldBuilder setValue(@NonNull ContractFieldValue<?>[] values) {
        this.setLength(FieldType.getFieldType(values[0].valueType()).getLength());
        this.value = ContractBlockValue.of(this.length, values);
        super.addBlock(this.value);
        return this;
    }

    @Override
    public MessageFieldBuilder setValue(FieldType type) {
        this.converter = CodecConverters.getCodecConverter(type);
        return this.setValue(this.converter.getCodec(), type.getLength());
    }

    private CodecConverter<?, ?> converter;

    @Override
    public MessageField build() {
        return MessageFieldImpl.of(
                this.name,
                this.value,
                this.converter,
                super.getBlocks()
        );
    }


    public MessageFieldBuilder setValue(Codec<?> codec, int length) {
        this.length = ContractLength.of(length);
        this.value = BlockValue.of(codec, this.length);
        super.addBlock((MessageBlock) this.value);
        return this;
    }

    private MessageFieldBuilder setLength(byte[] tag) {
        TagBlockLength blockLength = TagBlockLength.of(tag);
        this.length = blockLength;
        super.addBlock(blockLength);
        return this;
    }

    private MessageFieldBuilder setValue(Codec<?> codec, byte[] tag) {
        this.setLength(tag);
        this.value = BlockValue.of(codec, this.length);

        super.addBlock(this.value);
        return this;

    }


    @Override
    public MessageFieldBuilder fromField(org.inovout.message.MessageField field) {
        this.setName(field.getName(), field.getAlias());
        this.converter = field.getConverter();
        if (null == this.converter) {
            this.converter = CodecConverters.getCodecConverter(field.getType());
        }
        int length = field.getLength();
        if (0 >= length) {
            length = this.converter.getLength();
        }
        if (0 < length) {
            return this.setValue(this.converter.getCodec(), length);
        } else {
            return this.setValue(this.converter.getCodec(), field.getEndTag());

        }
    }


}




