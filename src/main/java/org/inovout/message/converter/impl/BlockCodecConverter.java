package org.inovout.message.converter.impl;

import org.inovout.message.FieldType;
import org.inovout.message.codec.block.Codec;

public class BlockCodecConverter<Block> extends CodecConverterWrapper<Block, Block> {
    public BlockCodecConverter(Codec<Block> codec) {
        super(codec);
    }

    public static <B> BlockCodecConverter<B> of(Codec<B> codec) {
        return new BlockCodecConverter<B>(codec);
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.getFieldType(this.getCodec().getType());
    }

    @Override
    public Block toBlock(Block block) {
        return block;
    }

    @Override
    public Block toField(Block block) {
        return block;
    }
}
