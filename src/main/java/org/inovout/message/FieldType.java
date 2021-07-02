package org.inovout.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.inovout.message.converter.impl.EnumWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum FieldType {
    BYTE(BlockType.BYTE),
    BYTE_ARRAY(BlockType.BYTE_ARRAY),
    SHORT(BlockType.SHORT),
    INTEGER(BlockType.INTEGER),
    LONG(BlockType.LONG),
    FLOAT(BlockType.FLOAT),
    DOUBLE(BlockType.DOUBLE),
    BOOLEAN(boolean.class, Boolean.class, 1),
    CHAR_ARRAY(char[].class, CharSequence.class),
    DATE(Date.class, Date.class),
    STRING(String.class, String.class),
    ENUM(Enum.class, EnumWrapper.class),
    CONTRACT_FIELD_VALUE(ContractFieldValue.class, ContractFieldValue.class);

    @NonNull
    @Getter
    private Class<?> keyword;
    @NonNull
    @Getter
    private Class<?> type;
    @Getter
    private int length;

    FieldType(BlockType blockType) {
        this(blockType.getKeywordType());
    }

    FieldType(KeywordType keywordType) {
        this(
                keywordType.getKeyword(),
                keywordType.getType(),
                keywordType.getLength()
        );
    }

    FieldType(Class<?> keyword, Class<?> type) {
        this(keyword, type, 0);
    }


    private static Map<Class<?>, Class<?>> keywordMap = new HashMap<>();
    private static Map<Class<?>, Class<?>> typeMap = new HashMap<>();
    private static Map<Class<?>, FieldType> FieldTypeMap = new HashMap<>();

    static {
        for (FieldType type : FieldType.values()) {
            typeMap.put(type.type, type.type);
            keywordMap.put(type.keyword, type.type);
            FieldTypeMap.put(type.getType(), type);
        }
    }

    public static FieldType getFieldType(Class<?> clazz) {
        return FieldTypeMap.get(getType(clazz));
    }

    public static Class<?> getType(Class<?> clazz) {
        if (keywordMap.containsKey(clazz)) {
            return keywordMap.get(clazz);
        }
        return typeMap.get(clazz);
    }
}
