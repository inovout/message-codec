package org.inovout.message.builder.action;

import org.inovout.message.ContractFieldValue;
import org.inovout.message.FieldType;
import org.inovout.message.codec.MessageField;

public interface FieldAction extends BuildAction {


    public interface FieldCreateAction extends BuildAction {
        // @NonNull End fields(org.inovout.message.MessageField[] fields);
        End field(org.inovout.message.MessageField field);

        End field(String name, ContractFieldValue<?>[] values);
    }

    public interface First extends FieldCreateAction, NameAction {
    }

    public interface Start extends StartAction<First> {
    }

    public interface NameAction extends BuildAction {
        default ValueAction name(String name) {
            return this.name(name, name);
        }

        ValueAction name(String name, String alias);
    }


    public interface ValueAction extends BuildAction {
        End value(ContractFieldValue<?>[] values);

        End value(FieldType type);
    }


    public interface End extends EndAction<MessageField> {
    }
}
