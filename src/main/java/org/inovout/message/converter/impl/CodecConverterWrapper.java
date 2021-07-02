package org.inovout.message.converter.impl;

import lombok.*;
import org.inovout.message.FieldType;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.converter.Converter;
import org.inovout.message.exception.CodecException;

@AllArgsConstructor(staticName = "wrap")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CodecConverterWrapper<Field, Block> implements CodecConverter<Field, Block> {
    private Converter<Field, Block> converter;
    @NonNull
    private Codec<Block> codec;

    @Override
    public FieldType getFieldType() {
        return FieldType.getFieldType(this.converter.getSourceType());
    }


    @Override
    public Field toField(Block block) throws CodecException {
        return this.converter.toSource(block);
    }

    @Override
    public Block toBlock(Field field) {
        return this.converter.toTarget(field);
    }

}
