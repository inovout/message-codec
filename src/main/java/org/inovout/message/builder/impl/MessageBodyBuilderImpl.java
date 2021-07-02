package org.inovout.message.builder.impl;

import lombok.NonNull;
import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;
import org.inovout.message.builder.MessageBodyBuilder;
import org.inovout.message.builder.MessageFieldBuilder;
import org.inovout.message.codec.MessageBody;
import org.inovout.message.codec.MessageField;
import org.inovout.message.codec.MessageVersionType;
import org.inovout.message.codec.impl.MessageBodyImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageBodyBuilderImpl
        extends MessagePartBuilderImple<MessageBody>
        implements MessageBodyBuilder {

    public MessageBodyBuilderImpl() {
        this.versionTypeFields = new HashMap<>();
    }

    @Override
    public MessageBody build() {
        return MessageBodyImpl.of(
                this.versionTypeFields,
                super.getBlocks());
    }

    private Map<MessageVersionType, MessageField[]> versionTypeFields;

    @Override
    @NonNull
    public MessageBodyBuilder addMessageFields(MessageVersion<?> version,
                                               MessageType<?> type,
                                               org.inovout.message.MessageField[] fields) {

        MessageVersionType versionType = MessageVersionType.of(version, type);

        MessageField[] typeFields = new MessageField[fields.length];
        MessageField typeField = null;
        for (int i = 0; i < fields.length; i++) {
            typeField = MessageFieldBuilder.create()
                    .noStartTag()
                    .field(fields[i])
                    .noEndTag();
            this.addBlock(typeField);
            typeFields[i] = typeField;
        }
        this.versionTypeFields.put(versionType, typeFields);
        return this;
    }


}
