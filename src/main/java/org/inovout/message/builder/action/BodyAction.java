package org.inovout.message.builder.action;

import org.inovout.message.MessageField;
import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;
import org.inovout.message.codec.MessageBody;

public interface BodyAction extends EndAction<MessageBody> {


    public interface FieldAction extends BuildAction {
        FieldAndEndAction typeFields(MessageVersion<? extends Number> version,
                                     MessageType<?> type,
                                     MessageField[] fields);


        default FieldAndEndAction typeFields(MessageType<? extends Number> type,
                                             MessageField[] fields) {
            return typeFields(MessageVersion.NO_MESSAGE_VERSION, type, fields);
        }
    }

    public interface FieldAndEndAction
            extends FieldAction, End {
    }

    public interface Start extends StartAction<First> {
    }

    public interface First extends FieldAndEndAction {
    }

    public interface End extends EndAction<MessageBody> {
      //  End crc16();
    }
}
