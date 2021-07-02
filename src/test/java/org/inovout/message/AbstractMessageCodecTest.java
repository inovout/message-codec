package org.inovout.message;

import org.inovout.message.data.MessagePacketData;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractMessageCodecTest {

    protected final void asserEncodeResult(String expected, String actual) {

        assertEquals(expected.toUpperCase(), actual.toUpperCase());
    }

    protected final void asserDecodeResult(Map<String, Object> data, MessagePacketData packetData) {
        for (String name : data.keySet()) {
            Object expected = data.get(name);
            Object actual = packetData.get(name);
            if (expected instanceof byte[]) {
                assertArrayEquals((byte[]) expected, (byte[]) actual, name);

            } else {
                assertEquals(expected, actual, name);
            }
        }
    }


}
