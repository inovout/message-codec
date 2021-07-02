package org.inovout.message.jetlinks.demo.general;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.inovout.message.FieldType;
import org.inovout.message.MessageField;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.converter.CodecConverters;
import org.inovout.message.jetlinks.demo.TcpStatus;


@Getter
@AllArgsConstructor
public enum  FireAlarm implements MessageField {
    DEVICE_ID("deviceId","设备标识",CodecConverters.NUMBER.LONG_LE_),

    INT("lnt","经度",CodecConverters.NUMBER.FLOAT_LE),

    LAT("lat","纬度",CodecConverters.NUMBER.FLOAT_LE),

    POINT("point","点位",CodecConverters.NUMBER.INTEGER_LE);


    @NonNull
    private String name;
    @NonNull
    private String alias;
    private FieldType type;
    private CodecConverter<?, ?> converter;

    FireAlarm(String name, String alias, FieldType type) {
        this(name, alias, type, null);
    }

    FireAlarm(String name, String alias, CodecConverter<?, ?> converter) {
        this(name, alias, null, converter);
    }


}

