package org.inovout.message.converter;

import org.inovout.message.FieldType;

public class CodecConverters {

    public static CodecConverterProvider.Numbers NUMBER = CodecConverterProvider.INSTANCE.NUMBER;
    public static CodecConverterProvider.Contracts CONTRACT = CodecConverterProvider.INSTANCE.CONTRACT;
    public static CodecConverterProvider.DateTimes DATETIME = CodecConverterProvider.INSTANCE.DATETIME;
    public static CodecConverterProvider.Booleans BOOLEAN = CodecConverterProvider.INSTANCE.BOOLEAN;
    public static CodecConverterProvider.Strings STRING = CodecConverterProvider.INSTANCE.STRING;

    public static CodecConverter<?, ?> getCodecConverter(FieldType type) {
        return CodecConverterProvider.INSTANCE.getCodecConverter(type);
    }
}
