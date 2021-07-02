package org.inovout.message.codec.field.impl;

import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.ContractFieldValue;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.codec.field.FieldLength;

@Getter
public class ContractBlockValue extends BlockValue {
    @NonNull
    private ContractFieldValue<?>[] values;

    public ContractBlockValue(@NonNull Codec<?> codec,
                              @NonNull FieldLength length,
                              @NonNull ContractFieldValue<?>[] values) {
        super(codec, length,
                null
        );
        this.values = values;
    }

    private static Codec<?> getCodec(ContractFieldValue<?> value) {
        return null;
    }

    public static ContractBlockValue of(@NonNull FieldLength length,
                                        @NonNull ContractFieldValue<?>[] values) {

        return new ContractBlockValue(getCodec(values[0]), length, values

        );
    }
}

