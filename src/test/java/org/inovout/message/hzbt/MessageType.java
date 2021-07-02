package org.inovout.message.hzbt;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum MessageType implements org.inovout.message.MessageType<Short> {
    REPORT_DATA("上报数据", (short) 0X0040),
    ;

    private String alias;
    private Short value;

    @Override
    public Short value() {
        return value;
    }

    @Override
    public String alias() {
        return this.alias;
    }
}
