package org.inovout.message.codec.block;

import org.inovout.message.BlockType;
import org.inovout.message.CodecSettings;

import java.util.HashMap;
import java.util.Map;

public class Codecs {


    private static Map<BlockType, Codec<?>> codecMap = new HashMap<>();


    public static Codec<Byte> BYTE = new NumberCodec.ByteCodec();
    public static Codec<Short> SHORT = new NumberCodec.ShortCodec();
    public static Codec<Integer> INTEGER = new NumberCodec.IntegerCodec();
    public static Codec<Integer> INTEGER_LE = new NumberCodec.IntegerLeCodec();
    public static Codec<Long> LONG = new NumberCodec.LongCodec();
    public static Codec<Long> LONG_LE = new NumberCodec.LongLeCodec();
    public static Codec<Float> FLOAT = new NumberCodec.FloatCodec();
    public static Codec<Float> FLOAT_LE = new NumberCodec.FloatLeCodec();
    public static Codec<Double> DOUBLE = new NumberCodec.DoubleCodec();
    public static Codec<Double> DOUBLE_LE = new NumberCodec.DoubleLeCodec();
    public static Codec<CharSequence> CHAR_ARRAY = new CharArrayCodec();
    public static Codec<CharSequence> HEX_CHAR_ARRAY = new HexCharArrayCodec();
    public static Codec<byte[]> BYTE_ARRAY = new Codec<byte[]>() {
        @Override
        public BlockType getBlockType() {
            return BlockType.BYTE_ARRAY;
        }

        @Override
        public byte[] encode(byte[] value) {
            return value;
        }

        @Override
        public byte[] encode(byte[] value, CodecSettings settings) {
            return this.encode(value);
        }

        @Override
        public byte[] decode(byte[] value) {
            return value;
        }

        @Override
        public byte[] decode(byte[] bytes, CodecSettings settings) {
            return this.decode(bytes);
        }
    };

    static {
        codecMap.put(BYTE.getBlockType(), BYTE);
        codecMap.put(SHORT.getBlockType(), SHORT);
        codecMap.put(INTEGER.getBlockType(), INTEGER);
        codecMap.put(LONG.getBlockType(), LONG);
        codecMap.put(FLOAT.getBlockType(), FLOAT);
        codecMap.put(DOUBLE.getBlockType(), DOUBLE);
        codecMap.put(CHAR_ARRAY.getBlockType(), CHAR_ARRAY);
        codecMap.put(BYTE_ARRAY.getBlockType(), BYTE_ARRAY);
    }

    public static Codec<?> getCodec(BlockType type) {
        return codecMap.get(type);
    }

}
