package org.inovout.message.codec.check.crc;

import org.inovout.message.BlockType;

public interface Crc {
    int getLength();

    BlockType getType();

    public static Crc16 CRC16 = new Crc16();

    byte[] cacl(byte[] data);
}

