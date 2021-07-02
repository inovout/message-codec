package org.inovout.message.codec.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.inovout.message.codec.*;
import org.inovout.message.validator.BlockValidator;

import java.util.ArrayList;
import java.util.List;

public class MessageHeaderImpl
        extends MessagePartImpl
        implements MessageHeader {
    @NonNull
    private MessageVersion messageVersion;
    @NonNull
    private MessageType messageType;
    @Getter
    private MessageLength messageLength;
    //private Map<MessageVersionType, MessageField[]> versionTypeFields;
    private MessageField[] fields;

    public MessageHeaderImpl(MessageBlock[] blocks) {
        super(blocks);
        List<MessageField> fieldList = new ArrayList<>();
        for (MessageBlock field : blocks) {
            if (field instanceof MessageField) {
                fieldList.add((MessageField) field);
            }
            if (field instanceof MessageType) {
                this.messageType = (MessageType) field;
                this.messageType.setHeader(this);
            } else if (field instanceof MessageLength) {
                this.messageLength = (MessageLength) field;
                messageLength.setHeader(this);
            } else if (field instanceof MessageVersion) {
                this.messageVersion = (MessageVersion) field;
            }
        }
        this.fields = new MessageField[fieldList.size()];
        fieldList.toArray(this.fields);


        this.fields = fields;
        //this.versionTypeFields = versionTypeFields;

    }

    public static MessageHeaderImpl of(MessageBlock[] blocks) {

        return new MessageHeaderImpl(blocks);
    }


    @Override
    public void setMessageVersionAndType(EncodeContext context) {
        if (null != this.messageVersion) {
            this.messageVersion.setVersion(context);
        }
        if (null != this.messageType) {
            this.messageType.setType(context);
        }
    }

    @Getter
    @Setter
    public MessagePacket packet;


}
