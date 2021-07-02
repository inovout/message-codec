package org.inovout.message;

public class NoMessageVersion implements MessageVersion {
    @Override
    public String alias() {
        return "NoVersion";
    }

    @Override
    public Number value() {
        return -1;
    }
}
