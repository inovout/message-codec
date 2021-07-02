package org.inovout.message.converter.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.inovout.message.FieldType;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.converter.CodecConverter;

@RequiredArgsConstructor
public class BooleanCodecConverter<Block extends Number>
        implements CodecConverter<Boolean, Block> {

    @Override
    public FieldType getFieldType() {
        return FieldType.BOOLEAN;
    }

    @NonNull
    @Getter
    private Codec<Block> codec;

    @NonNull
    private Block trueValue;

    @NonNull
    private Block falseValue;

    @Override
    public Boolean toField(Block v) {
        return this.trueValue.equals(v);
    }

    @Override
    public Block toBlock(Boolean v) {
        return v ? this.trueValue : falseValue;
    }

}
