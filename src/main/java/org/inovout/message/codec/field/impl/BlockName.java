package org.inovout.message.codec.field.impl;

import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.EncodeContext;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.codec.field.FieldBlockName;
import org.inovout.message.codec.field.FieldName;
import org.inovout.message.codec.impl.MessageBlockImpl;

import java.util.function.Function;

@Getter
public class BlockName
        extends MessageBlockImpl
        implements FieldName, FieldBlockName {

    @NonNull
    private String alias;

    public BlockName(@NonNull Codec<?> codec,
                     @NonNull Function<DecodeContext, Integer> getBlockLength,
                     // @NonNull Function<EncodeContext, ?> getBlockValueh,
                     String alias) {
        super(codec);
        this.alias = alias;
    }


    @Override
    public String getName(DecodeContext context) {
        return null;
    }

    @Override
    public String getName(EncodeContext context) {
        return null;
    }

    @Override
    public void setName(EncodeContext context) {

    }
}
