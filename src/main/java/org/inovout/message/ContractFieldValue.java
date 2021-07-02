package org.inovout.message;

public interface ContractFieldValue<V extends Number> {
    V value();

    default Class<? extends Number> valueType() {
        return (Class<? extends Number>) this.value().getClass();
    }
}
