package org.inovout.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RequiredArgsConstructor
public enum KeywordType {
    BYTE(byte.class, Byte.class, 1),
    BYTE_ARRAY(byte[].class, Byte[].class),
    BOOLEAN(boolean.class, Boolean.class, 1),
    BOOLEAN_ARRAY(boolean[].class, Boolean[].class),
    SHORT(short.class, Short.class, 2),
    SHORT_ARRAY(short[].class, Short[].class),
    INTEGER(int.class, Integer.class, 4),
    INTEGER_ARRAY(int[].class, Integer[].class),
    LONG(long.class, Long.class, 8),
    LONG_ARRAY(long[].class, Long[].class),
    FLOAT(float.class, Float.class, 4),
    FLOAT_ARRAY(float[].class, Float[].class),
    DOUBLE(double.class, Double.class, 8),
    DOUBLE_ARRAY(double[].class, Double[].class),
    CHAR(char.class, Character.class),
    CHAR_ARRAY(char[].class, CharSequence.class);

    @NonNull
    @Getter
    private Class<?> keyword;
    @NonNull
    @Getter
    private Class<?> type;
    @Getter
    private int length;

    private static Map<Class<?>, Class<?>> keywordMap = new HashMap<>();
    private static Map<Class<?>, Class<?>> typeMap = new HashMap<>();

    static {
        for (KeywordType type : KeywordType.values()) {
            typeMap.put(type.type, type.type);
            keywordMap.put(type.keyword, type.type);
        }
    }

    public static Class<?> getType(Class<?> clazz) {
        if (keywordMap.containsKey(clazz)) {
            return keywordMap.get(clazz);
        }
        return typeMap.get(clazz);
    }

}
