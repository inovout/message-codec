package org.inovout.message.validator;

import org.inovout.message.codec.DecodeContext;

public interface BlockValidator {
    boolean validate(DecodeContext context);
}
