package org.inovout.message.builder.action;

import org.inovout.message.codec.MessagePart;

public interface EndAction<P extends MessagePart> {

    P endTag(byte[] tag);


    default P noEndTag() {
        return this.endTag(new byte[0]);
    }

    // default P endTagByHexCharArray(CharSequence tag) throws CodecException {
    //     return this.endTag(Codecs.HEX_CHAR_ARRAY.encode(tag));
    // }

    // default P endTagByCharArray(CharSequence tag) {
    //     return this.endTag((Codecs.CHAR_ARRAY).encode(tag));
    // }

}