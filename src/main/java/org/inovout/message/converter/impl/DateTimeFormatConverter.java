package org.inovout.message.converter.impl;

import lombok.NonNull;
import org.inovout.message.codec.block.Codec;
import org.inovout.message.codec.block.Codecs;
import org.inovout.message.exception.CodecException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeFormatConverter extends DateTimeConverter<CharSequence> {


    public static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    @NonNull
    private SimpleDateFormat format;

    public DateTimeFormatConverter() {
        this(DEFAULT_FORMAT);
    }

    public DateTimeFormatConverter(String datetimeformat) {
        this(datetimeformat, TimeZone.getDefault());
    }

    public DateTimeFormatConverter(String datetimeformat, TimeZone timeZone) {
        this.format = new SimpleDateFormat(datetimeformat);
        this.format.setTimeZone(timeZone);
    }

    @Override
    public int getLength() {
        return this.format.toPattern().length() / 2;
    }

    @Override
    public Codec<CharSequence> getCodec() {
        return Codecs.HEX_CHAR_ARRAY;
    }

    @Override
    public Class<?> getTargetType() {
        return CharSequence.class;
    }

    @Override
    public Date toSource(CharSequence source) throws CodecException {
        try {
            return this.format.parse(String.valueOf(source));
        } catch (ParseException e) {
            throw new CodecException("date format fail.", e);
        }
    }

    @Override
    public CharSequence toTarget(Date source) {
        return this.format.format(source);
    }
}

