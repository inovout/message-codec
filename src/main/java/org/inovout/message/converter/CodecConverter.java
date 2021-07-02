package org.inovout.message.converter;

import org.inovout.message.BlockType;
import org.inovout.message.FieldType;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.exception.CodecException;

public interface CodecConverter<Field, Block> extends Converter<Field, Block> {
    FieldType getFieldType();

    default BlockType getBlockType() {
        return this.getCodec().getBlockType();
    }

    Codec<Block> getCodec();

    Field toField(Block block) throws CodecException;

    Block toBlock(Field field);

    default int getLength() {
        return this.getBlockType().getLength();
    }

    default Class<?> getSourceType() {
        return this.getFieldType().getType();
    }

    default Class<?> getTargetType() {
        return this.getBlockType().getType();
    }

    default Field toSource(Block block) throws CodecException {
        return this.toField(block);
    }

    default Block toTarget(Field field) {
        return this.toTarget(field);
    }


}
