package org.inovout.message;


public interface MessageVersion<V extends Number> extends ContractFieldValue<V> {
    String alias();

    V value();


    public static MessageVersion NO_MESSAGE_VERSION = new NoMessageVersion();

}
