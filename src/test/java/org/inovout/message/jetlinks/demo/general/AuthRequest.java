package org.inovout.message.jetlinks.demo.general;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.FieldType;
import org.inovout.message.MessageField;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.converter.CodecConverters;


@Getter
@AllArgsConstructor
public enum AuthRequest implements MessageField {
    DEVICE_ID("deviceId", "设备标识", CodecConverters.NUMBER.LONG_LE_),
    KEY("key", "认证码", FieldType.BYTE_ARRAY);

    @NonNull
    private String name;
    @NonNull
    private String alias;
    private FieldType type;
    private CodecConverter<?, ?> converter;

    AuthRequest(String name, String alias, FieldType type) {
        this(name, alias, type, null);
    }

    AuthRequest(String name, String alias, CodecConverter<?, ?> converter) {
        this(name, alias, null, converter);
    }


}
