package org.inovout.message.builder.action;

public interface StartAction<A extends BuildAction> {

    A startTag(byte[] tag);


    default A noStartTag() {
        return this.startTag(new byte[0]);
    }

//    default A startTagByHexCharArray(CharSequence tag) throws CodecException {
    //      return this.startTag(Codecs.HEX_CHAR_ARRAY.encode(tag));
    //  }

    //  default A startTagByCharArray(CharSequence tag) {
    //     return this.startTag(((CharArrayCodec) Codecs.CHAR_ARRAY).encode(tag));
    // }

}
