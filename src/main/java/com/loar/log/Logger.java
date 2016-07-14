package com.loar.log;

import android.util.Log;

/**
 * Created by Justsy on 2016/7/11.
 */
public class Logger {

    private Config config;

    private IRecordFile recordFile;

    public Logger(Config config) {
        this.config = config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public void setRecordFile(IRecordFile recordFile) {
        this.recordFile = recordFile;
    }

    /**
     * 开发模式什么都会输出，非开发模式，只有在级别大于等于设定值时才输出
     *
     * @param level
     * @param tag
     * @param msg
     */
    public void log(Config.Level level, String tag, String msg) {
        if (config.isDevelop() || level.ordinal() >= config.setLevel().ordinal()) {
            //打印日志
            switch (level) {
                case ALL:
                    break;
                case DEBUG:
                    Log.d(tag, msg);
                    break;
                case INFO:
                    Log.i(tag, msg);
                    break;
                case WARN:
                    Log.w(tag, msg);
                    break;
                case ERROR:
                    Log.e(tag, msg);
                    break;
                case CRASH:
                    break;
            }

            if (config.setFileName() != null && config.setFileDir() != null) {
                //输出日志文件
                if (recordFile != null) {
                    recordFile.save(config.setFileDir(), config.setFileName(), tag, msg);
                }
            }
        }
    }

    public void info(String tag, String msg) {
        log(Config.Level.INFO, tag, msg);
    }

    public void err(String tag, String msg) {
        log(Config.Level.ERROR, tag, msg);
    }

    public void debug(String tag, String msg) {
        log(Config.Level.DEBUG, tag, msg);
    }

    public void warn(String tag, String msg) {
        log(Config.Level.WARN, tag, msg);
    }

}
