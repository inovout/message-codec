package org.inovout.message.hzbt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.FieldType;
import org.inovout.message.MessageField;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.converter.CodecConverters;


@Getter
@AllArgsConstructor
public enum ReportData implements MessageField {
    SOURCE_ADDRESS("sourceAddress", "源地址", FieldType.SHORT),
    CHANNEL("channel", "通道", FieldType.BYTE),
    DEVICE_ID("deviceId", "设备Id", FieldType.BYTE),
    FREQUENCY("frequency", "频率", CodecConverters.NUMBER.FLOAT_LE),
    TEMPERATURE("temperature", "温度", CodecConverters.NUMBER.FLOAT_LE),
    //TIME("time", "时间", FieldType.STRING, 6),
    TIME("time", "时间", CodecConverters.DATETIME.format("yyMMddHHmmss")),
    ;


    @NonNull
    private String name;
    @NonNull
    private String alias;
    private FieldType type;
    private CodecConverter<?, ?> converter;

    private int length;

    ReportData(String name, String alias, FieldType type) {
        this(name, alias, type, type.getLength());
    }

    ReportData(String name, String alias, FieldType type, int length) {
        this(name, alias, type, null, length);
    }

    ReportData(String name, String alias, CodecConverter<?, ?> converter) {
        this(name, alias, null, converter, converter.getLength());
    }


    @Override
    public int getLength() {
        return this.length;
    }
}

