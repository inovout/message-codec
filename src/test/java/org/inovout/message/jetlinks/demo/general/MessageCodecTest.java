package org.inovout.message.jetlinks.demo.general;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.inovout.message.AbstractMessageCodecTest;
import org.inovout.message.DefaultFieldNames;
import org.inovout.message.builder.MessagePacketBuilder;
import org.inovout.message.codec.MessagePacket;
import org.inovout.message.converter.CodecConverters;
import org.inovout.message.data.MessagePacketData;
import org.inovout.message.exception.CodecException;
import org.inovout.message.jetlinks.demo.TcpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MessageCodecTest extends AbstractMessageCodecTest {

    private MessagePacket packet;

    @BeforeEach
    void before() {
        packet = MessagePacketBuilder.create()
                .noStartTag()
                .header(header -> header
                        .noStartTag()
                        .messageType(MessageType.values())
                        .bodyLength(CodecConverters.NUMBER.INTEGER_LE)
                        .noEndTag()
                )
                .body(body -> body
                        .noStartTag()
                        .typeFields(MessageType.AUTH_REQ, AuthRequest.values())
                        .typeFields(MessageType.AUTH_RES, AuthResponse.values())
                        .typeFields(MessageType.FIRE_ALARM, FireAlarm.values())
                        .noEndTag()
                )
                .noEndTag();


    }

    @Test
    void testAuthRequest() throws DecoderException, CodecException {

        String hexBuffer = "000d000000e80300000000000061646d696e";
        Map<String, Object> data = new HashMap<>();
        data.put(DefaultFieldNames.MESSAGE_TYPE, MessageType.AUTH_REQ);
        data.put(AuthRequest.DEVICE_ID.getName(), 1000L);
        data.put(AuthRequest.KEY.getName(), "admin".getBytes());

        //  MessagePacket packet = this.createMessagePacket();
        MessagePacketData packetData = packet.decode(
                Hex.decodeHex(hexBuffer));
        super.asserDecodeResult(data, packetData);
        byte[] dataBuffer = packet.encode(data);
        String dataHex = Hex.encodeHexString(dataBuffer);
        asserEncodeResult(dataHex, hexBuffer);
        //   System.out.println(data);
    }

    @Test
    public void testAuthResponse() throws DecoderException, CodecException {

        String hexBuffer = "0109000000e80300000000000000";
        Map<String, Object> data = new HashMap<>();
        data.put(DefaultFieldNames.MESSAGE_TYPE, MessageType.AUTH_RES);
        data.put(AuthResponse.DEVICE_ID.getName(), 1000L);
        data.put(AuthResponse.STATUS.getName(), TcpStatus.SUCCESS);

        //  MessagePacket packet = this.createMessagePacket();
        MessagePacketData packetData = packet.decode(
                Hex.decodeHex(hexBuffer));
        super.asserDecodeResult(data, packetData);
        byte[] dataBuffer = packet.encode(data);
        String dataHex = Hex.encodeHexString(dataBuffer);
        asserEncodeResult(dataHex, hexBuffer);
        //   System.out.println(data);
    }

    @Test
    public void testFireAlarm() throws DecoderException, CodecException {

        String hexBuffer = "0614000000e80300000000000088a72b3e4ad78a3e020c9498";
        Map<String, Object> data = new HashMap<>();
        data.put(DefaultFieldNames.MESSAGE_TYPE, MessageType.FIRE_ALARM);
        data.put(AuthResponse.DEVICE_ID.getName(), 1000L);
        data.put(FireAlarm.INT.getName(), 0.16763127F);
        data.put(FireAlarm.LAT.getName(), 0.27117378F);
        data.put(FireAlarm.POINT.getName(), -1735128062);

        //   MessagePacket packet = this.createMessagePacket();
        MessagePacketData packetData = packet.decode(
                Hex.decodeHex(hexBuffer));
        super.asserDecodeResult(data, packetData);
        byte[] dataBuffer = packet.encode(data);
        String dataHex = Hex.encodeHexString(dataBuffer);
        asserEncodeResult(dataHex, hexBuffer);
        //  System.out.println(data);
    }

}
