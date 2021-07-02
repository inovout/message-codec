package org.inovout.message.converter.impl;


import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.inovout.message.converter.Converter;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@AllArgsConstructor(staticName = "of")
public class StringConverter
        implements Converter<String, CharSequence> {

    @Override
    public Class<?> getSourceType() {
        return String.class;
    }

    @Override
    public Class<?> getTargetType() {
        return CharSequence.class;
    }

    @Override
    public String toSource(CharSequence source) {
        return String.valueOf(source);
    }


    public CharSequence toTarget(String source) {
        return source;
    }
}
