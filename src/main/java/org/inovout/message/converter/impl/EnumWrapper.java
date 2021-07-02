package org.inovout.message.converter.impl;

import lombok.*;
import org.inovout.message.ContractFieldValue;

import java.lang.reflect.Array;
import java.util.EnumSet;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(of = "value")
@ToString(of = "item")
public class EnumWrapper<EnumItem extends Enum, EnumValue extends Number>
        implements ContractFieldValue<EnumValue> {
    @NonNull
    private EnumItem item;
    @NonNull
    private EnumValue value;


    public static <FieldValue extends Enum<FieldValue>, FieldNumber extends Number>
    EnumWrapper<FieldValue, FieldNumber>[] wrap(Class<FieldValue> type, Function<FieldValue, FieldNumber> getValue) {

        EnumSet<FieldValue> enumSet = EnumSet.allOf(type);
        EnumWrapper<FieldValue, FieldNumber>[] items = (EnumWrapper<FieldValue, FieldNumber>[])
                Array.newInstance(EnumWrapper.class, enumSet.size());
        int index = 0;
        for (FieldValue item : enumSet) {

            items[index++] = new EnumWrapper<FieldValue, FieldNumber>(
                    item, getValue.apply(item)
            );
        }

        return items;
    }


    @Override
    public EnumValue value() {
        return this.value;
    }


}
