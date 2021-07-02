package org.inovout.message;

import lombok.Getter;
import lombok.Setter;
import org.inovout.message.converter.impl.DateTimeFormatConverter;

import java.nio.charset.Charset;
import java.util.TimeZone;

@Setter
@Getter
public class CodecSettings {
    private Charset charset = Charset.defaultCharset();
    private boolean debug = false;
    private String datetimeFormat = DateTimeFormatConverter.DEFAULT_FORMAT;
    private TimeZone timeZone = TimeZone.getDefault();
    private int BufferSize = 4096;

}
