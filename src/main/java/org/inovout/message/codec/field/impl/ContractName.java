package org.inovout.message.codec.field.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.EncodeContext;
import org.inovout.message.codec.field.FieldName;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class ContractName implements FieldName {
    @NonNull
    private String name;
    @NonNull
    private String alias;

    @Override
    public String getName(DecodeContext context) {
        return this.name;
    }

    @Override
    public String getName(EncodeContext context) {
        return this.name;
    }
}
