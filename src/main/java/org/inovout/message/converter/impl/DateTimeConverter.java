package org.inovout.message.converter.impl;

import org.inovout.message.FieldType;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.codec.block.Codecs;
import org.inovout.message.converter.CodecConverter;
import org.inovout.message.exception.CodecException;

import java.util.Date;

public abstract class DateTimeConverter<D>
        implements CodecConverter<Date, D> {

    @Override
    public FieldType getFieldType() {
        return FieldType.DATE;
    }

    @Override
    public Date toField(D d) throws CodecException {
        return this.toSource(d);
    }

    @Override
    public D toBlock(Date date) {
        return this.toTarget(date);
    }

    @Override
    public Class<?> getSourceType() {
        return Date.class;
    }

    public static class TimestampConverter extends DateTimeConverter<Long> {

        private final long maxSecondTimestamp = 9999999999L;

        @Override
        public Date toSource(Long source) {
            if (maxSecondTimestamp >= source) {
                source *= 1000;
            }
            return new Date(source);
        }

        @Override
        public Long toTarget(Date source) {
            return source.getTime();
        }


        @Override
        public Codec<Long> getCodec() {
            return Codecs.LONG;
        }

        @Override
        public Class<?> getTargetType() {
            return Long.class;
        }


    }

}
