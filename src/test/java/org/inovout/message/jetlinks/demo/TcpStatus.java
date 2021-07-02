package org.inovout.message.jetlinks.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TcpStatus {
    SUCCESS((byte) 0),
    ILLEGAL_ARGUMENTS((byte) 40),
    UN_AUTHORIZED((byte) 41),
    INTERNAL_SERVER_ERROR((byte) 50),
    UNKNOWN((byte) -1);

    private byte status;
}

