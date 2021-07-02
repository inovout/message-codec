package org.inovout.message.jetlinks.demo.general;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum MessageType implements org.inovout.message.MessageType<Byte> {
    AUTH_REQ("认证请求"),
    AUTH_RES("认证应答"),
    ERROR("错误"),
    PING("Ping"),
    REPORT_TEMPERATURE("上报温度"),
    READ_TEMPERATURE_REPLY("读取温度回复"),
    FIRE_ALARM("火警"),
    READ_PROPERTY("读取设备属性"),
    WRITE_PROPERTY("修改设备属性");

    private String alias;

    @Override
    public Byte value() {
        return (byte) (this.ordinal() & 0xFF);
    }

    @Override
    public String alias() {
        return this.alias;
    }
}
