package com.loar.log;

/**
 * Created by Justsy on 2016/7/11.
 */
public interface Config {
    String setFileDir();

    String setFileName();

    boolean isDevelop();

    Level setLevel();

    public static enum Level {
        ALL(0),
        DEBUG(1),
        INFO(2),
        WARN(3),
        ERROR(4),
        CRASH(5);

        private int value;

        Level(int i) {
            value = i;
        }

        public int getValue() {
            return value;
        }
    }
}
