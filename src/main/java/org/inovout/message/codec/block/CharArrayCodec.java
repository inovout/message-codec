package org.inovout.message.codec.block;

import org.inovout.message.BlockType;
import org.inovout.message.CodecSettings;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class CharArrayCodec implements Codec<CharSequence>, Decoder<CharSequence>, Encoder<CharSequence> {

    @Override
    public BlockType getBlockType() {
        return BlockType.CHAR_ARRAY;
    }

    @Override
    public CharSequence  decode(byte[] bytes) {
        return this.decode(bytes, Charset.defaultCharset());
    }

    public CharSequence decode(byte[] bytes, Charset charset) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return charset.decode(buffer);
    }

    @Override
    public CharSequence decode(byte[] bytes, CodecSettings settings) {
        return this.decode(bytes, settings.getCharset());
    }

    public byte[] encode(CharSequence value) {
        return encode(value, Charset.defaultCharset());
    }

    public byte[] encode(CharSequence value, Charset charset) {
        CharBuffer buffer = CharBuffer.wrap(value);

        return charset.encode(buffer).array();
    }

    @Override
    public byte[] encode(CharSequence value, CodecSettings settings) {
        return encode(value, settings.getCharset());
    }
}
