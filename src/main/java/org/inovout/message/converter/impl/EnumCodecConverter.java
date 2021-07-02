package org.inovout.message.converter.impl;

import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.FieldType;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.converter.CodecConverter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class EnumCodecConverter<E extends Enum<E>, N extends Number>
        implements CodecConverter<E, N> {

    @NonNull
    private Codec<N> codec;
    @NonNull
    private EnumWrapper<E, N>[] items;

    private Map<E, N> enumMap = new HashMap<>();
    private Map<N, E> valueMap = new HashMap<>();

    public EnumCodecConverter(Codec<N> codec, EnumWrapper<E, N>[] values) {
        this.codec = codec;
        this.items = values;
        for (EnumWrapper<E, N> item : values) {
            this.enumMap.put(item.getItem(), item.getValue());
            this.valueMap.put(item.getValue(), item.getItem());
        }

    }

    @Override
    public FieldType getFieldType() {
        return FieldType.ENUM;
    }


    @Override
    public E toField(N value) {
        return this.valueMap.get(value);

    }

    @Override
    public N toBlock(E v) {
        return this.enumMap.get(v);
    }
}
