package org.inovout.message.codec.block;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.inovout.message.BlockType;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockCodecProvider {
    public static BlockCodecProvider INSTANCE = new BlockCodecProvider();
    private static Map<Class<?>, Codec<?>> typeCodecMap = new HashMap<>();

    static {
        registerCodec(Codecs.BYTE);
        registerCodec(Codecs.SHORT);
        registerCodec(Codecs.INTEGER);
        registerCodec(Codecs.LONG);
        registerCodec(Codecs.FLOAT);
        registerCodec(Codecs.DOUBLE);
        registerCodec(Codecs.CHAR_ARRAY);
    }

    static void registerCodec(Codec<?> codec) {
        typeCodecMap.put(BlockType.getType(codec.getType()), codec);
    }


    public BlockCodecProvider register(Codec<?> codec) {
        registerCodec(codec);
        return this;
    }

    public Decoder<?> getDecoder(Class<?> decodeType) {
        return typeCodecMap.get(decodeType);
    }

    public Codec<?> getCodec(Class<?> type) {
        return typeCodecMap.get(type);
    }
}
