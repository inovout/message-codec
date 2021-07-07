package org.inovout.message;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractMessageCodecTest {

    protected final void asserEncodeResult(String expected, String actual) {

        assertEquals(expected.toUpperCase(), actual.toUpperCase());
    }

    protected final void asserDecodeResult(Map<String, Object> data, Map<String, Object> packetData) {
        for (String name : data.keySet()) {
            Object expected = data.get(name);
            Object actual = packetData.get(name);
            if (byte[].class.equals(expected.getClass())) {
                assertArrayEquals((byte[]) expected, (byte[]) actual, name);

            } else if (Short[].class.equals(expected.getClass())) {
                assertArrayEquals((Short[]) expected, (Short[]) actual, name);
            } else if (Integer[].class.equals(expected.getClass())) {
                assertArrayEquals((Integer[]) expected, (Integer[]) actual, name);
            } else if (Long[].class.equals(expected.getClass())) {
                assertArrayEquals((Long[]) expected, (Long[]) actual, name);
            } else if (expected instanceof Map) {
                asserDecodeResult((Map<String, Object>) expected, (Map<String, Object>) actual);
            } else {
                assertEquals(expected, actual, name);
            }
        }
    }


}
