package org.inovout.message;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum BlockType {
    BYTE(KeywordType.BYTE),
    SHORT(KeywordType.SHORT),
    INTEGER(KeywordType.INTEGER),
    LONG(KeywordType.LONG),
    FLOAT(KeywordType.FLOAT),
    DOUBLE(KeywordType.DOUBLE),
    BYTE_ARRAY(KeywordType.BYTE_ARRAY),
    CHAR_ARRAY(KeywordType.CHAR_ARRAY);

    @NonNull
    @Getter
    private KeywordType keywordType;

    public Class<?> getType() {
        return this.keywordType.getType();
    }

    public int getLength() {
        return this.keywordType.getLength();
    }

    private static Map<Class<?>, Class<?>> keywordMap = new HashMap<>();
    private static Map<Class<?>, Class<?>> typeMap = new HashMap<>();

    static {
        for (BlockType type : BlockType.values()) {
            if (byte[].class.equals(type.getKeywordType())) {
                typeMap.put(type.keywordType.getType(), type.keywordType.getKeyword());
                keywordMap.put(type.keywordType.getKeyword(), type.keywordType.getKeyword());
            } else {
                typeMap.put(type.keywordType.getType(), type.keywordType.getType());
                keywordMap.put(type.keywordType.getKeyword(), type.keywordType.getType());
            }
        }
    }

    public static Class<?> getType(Class<?> clazz) {
        if (keywordMap.containsKey(clazz)) {
            return keywordMap.get(clazz);
        }
        return typeMap.get(clazz);
    }
}
