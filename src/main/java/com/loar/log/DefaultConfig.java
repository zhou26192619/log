package com.loar.log;

/**
 * Created by Justsy on 2016/7/11.
 */
public class DefaultConfig implements Config {
    @Override
    public String setFileDir() {
        return null;
    }

    @Override
    public String setFileName() {
        return null;
    }

    @Override
    public boolean isDevelop() {
        return false;
    }

    @Override
    public Level setLevel() {
        return Level.INFO;
    }
}
