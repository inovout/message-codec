package org.inovout.message.converter;

import org.inovout.message.ContractFieldValue;
import org.inovout.message.FieldType;
import org.inovout.message.codec.block.BlockCodecProvider;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.codec.block.Codecs;
import org.inovout.message.converter.impl.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CodecConverterProvider {

    public static CodecConverterProvider INSTANCE = new CodecConverterProvider();
    private Map<FieldType, CodecConverter<?, ?>> codecConverterMap = new HashMap<>();

    public final Numbers NUMBER;
    public final Contracts CONTRACT;
    public final Strings STRING;
    public final DateTimes DATETIME;
    public final Booleans BOOLEAN;


    private CodecConverterProvider() {
        NUMBER = new Numbers();
        CONTRACT = new Contracts();
        STRING = new Strings();
        DATETIME = new DateTimes();
        BOOLEAN = new Booleans();

        register(NUMBER.BYTE);
        register(NUMBER.SHORT);
        register(NUMBER.INTEGER);
        register(NUMBER.LONG);
        register(NUMBER.FLOAT);
        register(NUMBER.DOUBLE);
        register(NUMBER.BYTE_ARRAY);
        register(STRING.DEFAULT);
        register(STRING.CHAR_ARRAY);
        register(DATETIME.TIMESTAMP);
        register(BOOLEAN.BYTE);
    }

    private void register(CodecConverter<?, ?> codecConverter) {
        codecConverterMap.put(codecConverter.getFieldType(), codecConverter);
    }

    public CodecConverter<?, ?> getCodecConverter(FieldType type) {
        return codecConverterMap.get(type);
    }

    public CodecConverter<?, ?> getCodecConverter(Class<?> type) {
        return getCodecConverter(FieldType.getFieldType(type));
    }

    public class DateTimes {
        public final CodecConverter<Date, Long> TIMESTAMP = CodecConverterWrapper.wrap(new DateTimeConverter.TimestampConverter(), Codecs.LONG);

        public final CodecConverter<Date, CharSequence> format(String format) {
            return new DateTimeFormatConverter(format);
        }
    }

    public static class Strings {
        public final BlockCodecConverter<CharSequence> CHAR_ARRAY = BlockCodecConverter.of(Codecs.CHAR_ARRAY);
        public final CodecConverter<String, CharSequence> DEFAULT =
                CodecConverterWrapper.wrap(StringConverter.of(), Codecs.CHAR_ARRAY);



    }

    public static class Booleans {
        public final BooleanCodecConverter<Byte> BYTE =
                new BooleanCodecConverter<Byte>(Codecs.BYTE, (byte) 1, (byte) 0);
    }

    public static class Contracts {

        public <FieldValue extends ContractFieldValue<FieldNumber>, FieldNumber extends Number>
        ContractCodecConverter<FieldValue, FieldNumber> create(FieldValue[] values) {
            return ContractCodecConverter.of(values);
        }

        public <FieldValue extends Enum<FieldValue>, FieldNumber extends Number>
        EnumCodecConverter<FieldValue, FieldNumber> create(Class<FieldValue> type,
                                                           Function<FieldValue, FieldNumber> getValue) {
            EnumWrapper<FieldValue, FieldNumber>[] values = EnumWrapper.wrap(type, getValue);
            Codec<FieldNumber> codec = (Codec<FieldNumber>) BlockCodecProvider.INSTANCE.getCodec(values[0].valueType());
            return new EnumCodecConverter<FieldValue, FieldNumber>(codec, values);
        }

    }

    public static class Numbers {
        public final BlockCodecConverter<Byte> BYTE = BlockCodecConverter.of(Codecs.BYTE);
        public final BlockCodecConverter<Short> SHORT = BlockCodecConverter.of(Codecs.SHORT);
        public final BlockCodecConverter<Integer> INTEGER = BlockCodecConverter.of(Codecs.INTEGER);
        public final BlockCodecConverter<Integer> INTEGER_LE = BlockCodecConverter.of(Codecs.INTEGER_LE);
        public final BlockCodecConverter<Long> LONG = BlockCodecConverter.of(Codecs.LONG);
        public final BlockCodecConverter<Long> LONG_LE_ = BlockCodecConverter.of(Codecs.LONG_LE);
        public final BlockCodecConverter<Float> FLOAT = BlockCodecConverter.of(Codecs.FLOAT);
        public final BlockCodecConverter<Float> FLOAT_LE = BlockCodecConverter.of(Codecs.FLOAT_LE);
        public final BlockCodecConverter<Double> DOUBLE = BlockCodecConverter.of(Codecs.DOUBLE);
        public final BlockCodecConverter<Double> DOUBLE_LE = BlockCodecConverter.of(Codecs.DOUBLE_LE);
        public final BlockCodecConverter<byte[]> BYTE_ARRAY = BlockCodecConverter.of(Codecs.BYTE_ARRAY);


        public class Char {
            public final BlockCodecConverter<CharSequence> CHAR_ARRAY = BlockCodecConverter.of(Codecs.CHAR_ARRAY);

        }
    }
}