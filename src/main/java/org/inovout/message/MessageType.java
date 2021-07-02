package org.inovout.message;

public interface MessageType<V extends Number> extends ContractFieldValue<V> {
    V value();

    String alias();


}


