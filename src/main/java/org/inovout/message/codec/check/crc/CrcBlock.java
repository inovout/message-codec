package org.inovout.message.codec.check.crc;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import org.inovout.message.codec.DecodeContext;
import org.inovout.message.codec.EncodeContext;
import org.inovout.message.codec.block.Codecs;
import org.inovout.message.codec.impl.MessageBlockImpl;
import org.inovout.message.validator.BlockValidator;

import java.util.Arrays;

public class CrcBlock extends MessageBlockImpl
        implements BlockValidator {
    @NonNull
    private Crc crc;


    public CrcBlock(Crc crc) {
        super(Codecs.BYTE_ARRAY, context -> crc.getLength(),
                context -> calcCrc(crc, context), null);
        this.crc = crc;

    }

    private static byte[] calcCrc(Crc crc, EncodeContext context) {
        byte[] data = context.getEncodedBuffer(); //getBytes(context.getBuffer(), 0);
        return crc.cacl(data);
    }

    private static byte[] getBytes(ByteBuf buf, int offset) {
        int lenght = buf.readableBytes();
        int current = buf.readerIndex();
        if (lenght == 0) {
            lenght = current;
        }

        byte[] buffer = new byte[lenght - offset];
        buf.readerIndex(0);
        buf.readBytes(buffer);
        buf.readerIndex(current);
        return buffer;

    }

    @Override
    public boolean validate(DecodeContext context) {
        byte[] data = getBytes(context.getByteBuf(), crc.getLength());
        byte[] caclCrcValue = this.crc.cacl(data);
        byte[] readerCrcValue = context.getBlockData(this).getBufferData();

        boolean validateResult = Arrays.equals(caclCrcValue, readerCrcValue);
        if (!validateResult) {
            context.getErrors().add("crc check fail");
        }
        return validateResult;
    }
}
