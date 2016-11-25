package com.loar.log;

/**
 * Created by Justsy on 2016/11/25.
 */

public interface LogCallback {
    void upload(String tag, String msg, String sensitiveInfo);
}
