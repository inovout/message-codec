package org.inovout.message.codec;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import org.inovout.message.MessageType;
import org.inovout.message.MessageVersion;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MessageVersionType {
    @NonNull
    private MessageVersion<?> version;
    @NonNull
    private MessageType<?> type;

    public static MessageVersionType of(MessageVersion<?> versin, MessageType<?> type) {
        return new MessageVersionType(versin, type);
    }


}
