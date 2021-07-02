package org.inovout.message.hzbt;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.inovout.message.AbstractMessageCodecTest;
import org.inovout.message.DefaultFieldNames;
import org.inovout.message.FieldType;
import org.inovout.message.builder.MessagePacketBuilder;
import org.inovout.message.codec.MessagePacket;
import org.inovout.message.data.MessagePacketData;
import org.inovout.message.exception.CodecException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


public class MessageCodecTest extends AbstractMessageCodecTest {

    private MessagePacket packet;

    @BeforeEach
    void before() {

        packet = MessagePacketBuilder.create()
                .startTag(new byte[]{(byte) 0XAA, (byte) 0XBB})
                .header(header -> header
                        .noStartTag()
                        .field("targetAddRESS", FieldType.SHORT)
                        .messageType(MessageType.values())
                        //  .field("count", FieldType.SHORT)
                        .bodyLength(FieldType.SHORT)
                        .noEndTag()
                )
                .body(body -> body
                        .noStartTag()
                        .typeFields(MessageType.REPORT_DATA, ReportData.values())
                        .noEndTag()
                )
                .crc16()
                .noEndTag();


    }

    @Test
    void testAuthRequest() throws CodecException, DecoderException, ParseException {

        String hexBuffer = "AABB000100400012B0040141009214453333D3412007021520303E60";
        Map<String, Object> data = new HashMap<>();
        data.put(DefaultFieldNames.MESSAGE_TYPE, MessageType.REPORT_DATA);
        data.put("targetAddRESS", Short.valueOf((short) 1));
        data.put(ReportData.DEVICE_ID.getName(), Byte.valueOf((byte) 65));
        data.put(ReportData.FREQUENCY.getName(), Float.valueOf((float) 2377.125));
        data.put(ReportData.TEMPERATURE.getName(), Float.valueOf((float) 26.4));
        data.put(ReportData.CHANNEL.getName(), Byte.valueOf((byte) 1));
        data.put(ReportData.SOURCE_ADDRESS.getName(), Short.valueOf((short) -20476));
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        data.put(ReportData.TIME.getName(), sdf.parse("200702152030"));

        MessagePacketData packetData = packet.decode(Hex.decodeHex(hexBuffer));
        super.asserDecodeResult(data, packetData);
        byte[] dataBuffer = packet.encode(data);
        String dataHex = Hex.encodeHexString(dataBuffer);
        super.asserEncodeResult(dataHex, hexBuffer);
    }


}
