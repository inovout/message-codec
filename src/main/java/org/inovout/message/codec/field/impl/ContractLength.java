package org.inovout.message.codec.field.impl;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.field.FieldLength;

@AllArgsConstructor(staticName = "of")
@Getter
@NonNull
public class ContractLength implements FieldLength {
    private Integer length;


    @Override
    public Integer getLength(DecodeContext context) {

        return this.length;
    }
}
