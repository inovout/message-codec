package org.inovout.message.codec.block;

import org.inovout.message.BlockType;
import org.inovout.message.CodecSettings;

public class NumberCodec {


    public static class ByteCodec implements Codec<Byte>, Decoder<Byte>, Encoder<Byte> {

        @Override
        public BlockType getBlockType() {
            return BlockType.BYTE;
        }

        @Override
        public byte[] encode(Byte value) {
            byte[] bytes = new byte[1];
            setByte(bytes, 0, value);
            return bytes;
        }

        @Override
        public Byte decode(byte[] bytes) {
            return getByte(bytes, 0);
        }

        @Override
        public Byte decode(byte[] bytes, CodecSettings settings) {
            return this.decode(bytes);
        }

        @Override
        public byte[] encode(Byte value, CodecSettings settings) {
            return this.encode(value);
        }


    }

    public static class ShortCodec implements Codec<Short>, Decoder<Short>, Encoder<Short> {

        @Override
        public BlockType getBlockType() {
            return BlockType.SHORT;
        }

        @Override
        public byte[] encode(Short value) {
            byte[] bytes = new byte[2];
            setShort(bytes, 0, value);

            return bytes;
        }

        @Override
        public Short decode(byte[] bytes) {
            return getShort(bytes, 0);
        }

        @Override
        public Short decode(byte[] bytes, CodecSettings settings) {
            return this.decode(bytes);
        }

        @Override
        public byte[] encode(Short value, CodecSettings settings
        ) {
            return this.encode(value);
        }
    }

    public static class IntegerCodec implements Codec<Integer>, Decoder<Integer>, Encoder<Integer> {

        @Override
        public BlockType getBlockType() {
            return BlockType.INTEGER;
        }

        @Override
        public byte[] encode(Integer value) {
            byte[] bytes = new byte[4];
            setInt(bytes, 0, value);

            return bytes;
        }

        @Override
        public Integer decode(byte[] bytes) {
            return getInt(bytes, 0);
        }

        @Override
        public Integer decode(byte[] bytes, CodecSettings settings) {
            return this.decode(bytes);
        }

        @Override
        public byte[] encode(Integer value, CodecSettings settings) {
            return this.encode(value);
        }
    }

    public static class IntegerLeCodec extends IntegerCodec {


        @Override
        public Integer decode(byte[] bytes, CodecSettings settings) {
            return this.decode(bytes);
        }

        @Override
        public Integer decode(byte[] bytes) {
            return getIntLE(bytes, 0);
        }

        @Override
        public byte[] encode(Integer value) {
            byte[] bytes = new byte[4];
            setIntLE(bytes, 0, value);
            return bytes;
        }

        @Override
        public byte[] encode(Integer value, CodecSettings settings) {
            return this.encode(value);
        }
    }

    public static class LongCodec implements Codec<Long>, Decoder<Long>, Encoder<Long> {

        @Override
        public BlockType getBlockType() {
            return BlockType.LONG;
        }

        @Override
        public byte[] encode(Long value) {
            byte[] bytes = new byte[8];
            setLong(bytes, 0, value);

            return bytes;
        }

        @Override
        public Long decode(byte[] bytes) {
            return getLong(bytes, 0);
        }

        @Override
        public Long decode(byte[] bytes, CodecSettings settings) {
            return this.decode(bytes);
        }

        @Override
        public byte[] encode(Long value, CodecSettings settings) {
            return this.encode(value);
        }
    }

    public static class LongLeCodec extends LongCodec {


        @Override
        public Long decode(byte[] bytes) {
            return getLongLE(bytes, 0);
        }

        @Override
        public Long decode(byte[] bytes, CodecSettings settings) {
            return this.decode(bytes);
        }

        @Override
        public byte[] encode(Long value) {
            byte[] bytes = new byte[8];
            setLongLE(bytes, 0, value);
            return bytes;
        }


        public byte[] encode(Long value, CodecSettings settings) {
            return this.encode(value);

        }
    }

    public static class FloatCodec implements Codec<Float>, Decoder<Float>, Encoder<Float> {

        @Override
        public BlockType getBlockType() {
            return BlockType.FLOAT;
        }

        @Override
        public byte[] encode(Float value) {
            byte[] bytes = new byte[4];
            setFloat(bytes, 0, value);

            return bytes;
        }

        @Override
        public Float decode(byte[] bytes) {
            return getFloat(bytes, 0);
        }

        @Override
        public Float decode(byte[] bytes, CodecSettings settings) {
            return this.decode(bytes);
        }

        @Override
        public byte[] encode(Float value, CodecSettings settings) {
            return this.encode(value);
        }
    }

    public static class FloatLeCodec extends FloatCodec {

        @Override
        public byte[] encode(Float value) {
            byte[] bytes = new byte[4];
            setIntLE(bytes, 0, Float.floatToIntBits(value));

            return bytes;
        }

        @Override
        public Float decode(byte[] bytes) {

            return Float.intBitsToFloat(getIntLE(bytes, 0));
        }

        @Override
        public Float decode(byte[] bytes, CodecSettings settings) {
            return this.decode(bytes);
        }

        @Override
        public byte[] encode(Float value, CodecSettings settings) {
            return this.encode(value);
        }
    }


    public static class DoubleCodec implements Codec<Double>, Decoder<Double>, Encoder<Double> {


        @Override
        public BlockType getBlockType() {
            return BlockType.DOUBLE;
        }

        @Override
        public byte[] encode(Double value) {
            byte[] bytes = new byte[8];
            setDouble(bytes, 0, value);

            return bytes;
        }

        @Override
        public Double decode(byte[] bytes) {
            return getDouble(bytes, 0);
        }

        @Override
        public Double decode(byte[] bytes, CodecSettings settings) {
            return this.decode(bytes);
        }

        @Override
        public byte[] encode(Double value, CodecSettings settings) {
            return this.encode(value);
        }

    }

    public static class DoubleLeCodec extends DoubleCodec {


        @Override
        public byte[] encode(Double value) {
            byte[] bytes = new byte[8];
            setLongLE(bytes, 0, Double.doubleToLongBits(value));

            return bytes;
        }

        @Override
        public Double decode(byte[] bytes) {
            return Double.longBitsToDouble(getLongLE(bytes, 0));
        }

        @Override
        public Double decode(byte[] bytes, CodecSettings settings) {
            return this.decode(bytes);
        }

        @Override
        public byte[] encode(Double value, CodecSettings settings) {
            return this.encode(value);
        }

    }


    static byte getByte(byte[] bytes, int index) {
        return bytes[index];
    }

    static short getShort(byte[] bytes, int index) {
        return (short) (bytes[index] << 8 | bytes[index + 1] & 255);
    }

    static short getShortLE(byte[] bytes, int index) {
        return (short) (bytes[index] & 255 | bytes[index + 1] << 8);
    }

    static int getUnsignedMedium(byte[] bytes, int index) {
        return (bytes[index] & 255) << 16 | (bytes[index + 1] & 255) << 8 | bytes[index + 2] & 255;
    }

    static int getUnsignedMediumLE(byte[] bytes, int index) {
        return bytes[index] & 255 | (bytes[index + 1] & 255) << 8 | (bytes[index + 2] & 255) << 16;
    }

    static int getInt(byte[] bytes, int index) {
        return (bytes[index] & 255) << 24 | (bytes[index + 1] & 255) << 16 | (bytes[index + 2] & 255) << 8 | bytes[index + 3] & 255;
    }

    static int getIntLE(byte[] bytes, int index) {
        return bytes[index] & 255 | (bytes[index + 1] & 255) << 8 | (bytes[index + 2] & 255) << 16 | (bytes[index + 3] & 255) << 24;
    }

    static long getLong(byte[] bytes, int index) {
        return ((long) bytes[index] & 255L) << 56 | ((long) bytes[index + 1] & 255L) << 48 | ((long) bytes[index + 2] & 255L) << 40 | ((long) bytes[index + 3] & 255L) << 32 | ((long) bytes[index + 4] & 255L) << 24 | ((long) bytes[index + 5] & 255L) << 16 | ((long) bytes[index + 6] & 255L) << 8 | (long) bytes[index + 7] & 255L;
    }

    static long getLongLE(byte[] bytes, int index) {
        return (long) bytes[index] & 255L | ((long) bytes[index + 1] & 255L) << 8 | ((long) bytes[index + 2] & 255L) << 16 | ((long) bytes[index + 3] & 255L) << 24 | ((long) bytes[index + 4] & 255L) << 32 | ((long) bytes[index + 5] & 255L) << 40 | ((long) bytes[index + 6] & 255L) << 48 | ((long) bytes[index + 7] & 255L) << 56;
    }

    static float getFloat(byte[] bytes, int index) {
        return Float.intBitsToFloat(getInt(bytes, index));
    }

    static double getDouble(byte[] bytes, int index) {
        return Double.longBitsToDouble(getLong(bytes, index));
    }

    static void setByte(byte[] bytes, int index, int value) {
        bytes[index] = (byte) value;
    }

    public static void setShort(byte[] bytes, int index, int value) {
        bytes[index] = (byte) (value >>> 8);
        bytes[index + 1] = (byte) value;
    }

    static void setShortLE(byte[] bytes, int index, int value) {
        bytes[index] = (byte) value;
        bytes[index + 1] = (byte) (value >>> 8);
    }

    static void setMedium(byte[] bytes, int index, int value) {
        bytes[index] = (byte) (value >>> 16);
        bytes[index + 1] = (byte) (value >>> 8);
        bytes[index + 2] = (byte) value;
    }

    static void setMediumLE(byte[] bytes, int index, int value) {
        bytes[index] = (byte) value;
        bytes[index + 1] = (byte) (value >>> 8);
        bytes[index + 2] = (byte) (value >>> 16);
    }

    static void setInt(byte[] bytes, int index, int value) {
        bytes[index] = (byte) (value >>> 24);
        bytes[index + 1] = (byte) (value >>> 16);
        bytes[index + 2] = (byte) (value >>> 8);
        bytes[index + 3] = (byte) value;
    }

    static void setFloat(byte[] bytes, int index, Float value) {
        int intFloat = Float.floatToIntBits(value);
        setInt(bytes, index, intFloat);
    }

    static void setDouble(byte[] bytes, int index, Double value) {
        long longDouble = Double.doubleToLongBits(value);
        setLong(bytes, index, longDouble);
    }

    static void setIntLE(byte[] bytes, int index, int value) {
        bytes[index] = (byte) value;
        bytes[index + 1] = (byte) (value >>> 8);
        bytes[index + 2] = (byte) (value >>> 16);
        bytes[index + 3] = (byte) (value >>> 24);
    }

    static void setLong(byte[] bytes, int index, long value) {
        bytes[index] = (byte) ((int) (value >>> 56));
        bytes[index + 1] = (byte) ((int) (value >>> 48));
        bytes[index + 2] = (byte) ((int) (value >>> 40));
        bytes[index + 3] = (byte) ((int) (value >>> 32));
        bytes[index + 4] = (byte) ((int) (value >>> 24));
        bytes[index + 5] = (byte) ((int) (value >>> 16));
        bytes[index + 6] = (byte) ((int) (value >>> 8));
        bytes[index + 7] = (byte) ((int) value);
    }

    static void setLongLE(byte[] bytes, int index, long value) {
        bytes[index] = (byte) ((int) value);
        bytes[index + 1] = (byte) ((int) (value >>> 8));
        bytes[index + 2] = (byte) ((int) (value >>> 16));
        bytes[index + 3] = (byte) ((int) (value >>> 24));
        bytes[index + 4] = (byte) ((int) (value >>> 32));
        bytes[index + 5] = (byte) ((int) (value >>> 40));
        bytes[index + 6] = (byte) ((int) (value >>> 48));
        bytes[index + 7] = (byte) ((int) (value >>> 56));
    }

}

