package org.inovout.message.data;

import org.inovout.message.codec.MessageField;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.data.impl.MessageFieldDataImpl;
import org.inovout.message.exception.CodecException;

public interface MessageFieldData<E> {

    String getName();

    int getBufferLength();

    E getData();

    public static <Field, Block> MessageFieldData<Field> create(String name, MessageField field,
                                                                CodecConverter<Field, Block> converter,
                                                                MessageBlockData<?> blockData) throws CodecException {

        Block block = (Block) blockData.getBlockData();
        Field fieldData = converter.toField(block);
        return new MessageFieldDataImpl<Field>(name, field,
                fieldData,
                blockData);
    }


}
