package org.inovout.message.builder;

import lombok.NonNull;
import org.inovout.message.ContractFieldValue;
import org.inovout.message.FieldType;
import org.inovout.message.builder.action.FieldAction;
import org.inovout.message.builder.impl.MessageFieldBuilderImpl;
import org.inovout.message.codec.MessageField;
import org.inovout.message.codec.impl.MessageTag;

public interface MessageFieldBuilder
        extends MessagePartBuilder<MessageField>,
        FieldAction.Start,
        FieldAction.First,
        FieldAction.NameAction,
        FieldAction.ValueAction,
        FieldAction.FieldCreateAction,
        FieldAction.End {


    MessageField build();

    MessageFieldBuilder setName(String name, String alias);

    MessageFieldBuilder setValue(ContractFieldValue<?>[] values);

    MessageFieldBuilder setValue(FieldType type);

    MessageFieldBuilder fromField(org.inovout.message.MessageField field);

    @NonNull
    default FieldAction.End field(org.inovout.message.MessageField field) {
        return this.fromField(field);
    }

    @NonNull
    default FieldAction.End field(String name, ContractFieldValue<?>[] values) {
        return this.setName(name, name).setValue(values);
    }

    default FieldAction.ValueAction name(String name, String alias) {
        return this.setName(name, alias);
    }

    default FieldAction.End value(ContractFieldValue<?>[] values) {
        return this.setValue(values);
    }

    default FieldAction.End value(FieldType type) {
        return this.setValue(type);
    }


    default FieldAction.First startTag(@NonNull byte[] tag) {
        if (0 < tag.length) {
            this.addBlock(MessageTag.of(tag));
        }
        return this;
    }

    default MessageField endTag(@NonNull byte[] tag) {
        if (0 < tag.length) {
            this.addBlock(MessageTag.of(tag));
        }
        return this.build();
    }

    public static FieldAction.Start create() {
        return new MessageFieldBuilderImpl();
    }

}
