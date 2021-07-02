package org.inovout.message.jetlinks.demo.general;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.FieldType;
import org.inovout.message.MessageField;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.converter.CodecConverters;
import org.inovout.message.jetlinks.demo.TcpStatus;


@Getter
@AllArgsConstructor
public enum AuthResponse implements MessageField {
    DEVICE_ID("deviceId", "设备标识", CodecConverters.NUMBER.LONG_LE_),
    STATUS("status", "状态", CodecConverters.CONTRACT.create(TcpStatus.class, TcpStatus::getStatus));

    @NonNull
    private String name;
    @NonNull
    private String alias;
    private FieldType type;
    private CodecConverter<?, ?> converter;

    AuthResponse(String name, String alias, FieldType type) {
        this(name, alias, type, null);
    }

    AuthResponse(String name, String alias, CodecConverter<?, ?> converter) {
        this(name, alias, null, converter);
    }


}
