package org.inovout.message.codec.block;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.inovout.message.BlockType;
import org.inovout.message.CodecSettings;
import org.inovout.message.exception.CodecException;

public class HexCharArrayCodec implements Codec<CharSequence>, Decoder<CharSequence>, Encoder<CharSequence> {

    @Override
    public BlockType getBlockType() {
        return BlockType.CHAR_ARRAY;
    }

    @Override
    public CharSequence decode(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }


    @Override
    public CharSequence decode(byte[] bytes, CodecSettings settings) {
        return this.decode(bytes);
    }

    public byte[] encode(CharSequence value) throws CodecException {
        try {
            return Hex.decodeHex((String) value);
        } catch (DecoderException e) {
            throw new CodecException("Hex encode failure", e);
        }
    }

    @Override
    public byte[] encode(CharSequence value, CodecSettings settings) throws CodecException {
        return encode(value);

    }
}