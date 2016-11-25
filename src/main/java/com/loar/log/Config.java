package com.loar.log;

/**
 * Created by Justsy on 2016/7/11.
 */
public interface Config {
    String setFileDir();//日志目录

    String setFileName();//日志文件名

    boolean isDevelop();//是否是开发模式

    String setDivision();//分割符

    Level setLevel();//输出日志等级

    enum Level {
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
