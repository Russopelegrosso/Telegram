package org.telegram.ui;

public class GetRequestAsync implements Runnable {
    public volatile String value;

    @Override
    public void run() {
        value = "dawdawd";
    }

    public String getValue() {
        return value;
    }
}
