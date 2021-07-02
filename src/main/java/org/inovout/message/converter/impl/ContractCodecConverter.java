package org.inovout.message.converter.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.inovout.message.ContractFieldValue;
import org.inovout.message.FieldType;
import org.inovout.message.codec.block.BlockCodecProvider;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.converter.CodecConverter;
@Getter
@RequiredArgsConstructor
public class ContractCodecConverter<V extends ContractFieldValue<N>, N extends Number>
        implements CodecConverter<V, N> {
    @NonNull
    private Codec<N> codec;
    @NonNull
    @Getter
    private V[] values;



    public static <FieldValue extends ContractFieldValue<FieldNumber>, FieldNumber extends Number>
    ContractCodecConverter<FieldValue, FieldNumber> of(FieldValue[] values) {
        Codec<FieldNumber> codec = (Codec<FieldNumber>) BlockCodecProvider.INSTANCE.getCodec(values[0].valueType());
        return new ContractCodecConverter<FieldValue, FieldNumber>(codec, values);
    }


    @Override
    public FieldType getFieldType() {
        return FieldType.CONTRACT_FIELD_VALUE;
    }


    @Override
    public V toField(N value) {
        for (V item : values) {
            if (value.equals(item.value())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public N toBlock(V v) {
        return v.value();
    }
}
