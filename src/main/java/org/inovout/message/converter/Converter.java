package org.inovout.message.converter;


import org.inovout.message.exception.CodecException;

public interface Converter<Source, Target> {
    Class<?> getSourceType();

    Class<?> getTargetType();

    Source toSource(Target target) throws CodecException;

    Target toTarget(Source source);


}
