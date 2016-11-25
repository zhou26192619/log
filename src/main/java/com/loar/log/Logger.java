package com.loar.log;

import android.text.TextUtils;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Justsy on 2016/7/11.
 */
public class Logger {

    private Config config;

    private LogCallback callback;

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

    public void setCallback(LogCallback callback) {
        this.callback = callback;
    }

    /**
     * 开发模式什么都会输出，非开发模式，只有在级别大于等于设定值时才输出
     *
     * @param level
     * @param tag
     * @param msg
     */
    public void log(Config.Level level, String tag, String msg, String sensitiveMsg) {
        if (!config.isDevelop() && level.ordinal() < config.setLevel().ordinal()) {
            //没有达到输出条件
            return;
        }

        StringBuilder sb = new StringBuilder();
        if (tag == null) {
            tag = "logger";
        }
        if (msg == null) {
            msg = "打印的信息为空";
        }
        if (sensitiveMsg == null) {
            sensitiveMsg = "";
        }

        sb.append(msg).append(TextUtils.isEmpty(config.setDivision()) ? " >> " : config.setDivision());
        if (config.isDevelop()) {
            sb.append(sensitiveMsg);
        }
        //打印日志
        switch (level) {
            case ALL:
                break;
            case DEBUG:
                Log.d(tag, sb.toString());
                break;
            case INFO:
                Log.i(tag, sb.toString());
                break;
            case WARN:
                Log.w(tag, sb.toString());
                break;
            case ERROR:
                Log.e(tag, sb.toString());
                break;
            case CRASH:
                break;
        }
        //输出日志文件
        if (config.setFileName() != null && config.setFileDir() != null && recordFile != null) {
            recordFile.save(config.setFileDir(), config.setFileName(), tag, sb.toString());
        }

        if (callback != null) {
            callback.upload(tag, msg, sensitiveMsg);
        }
    }


    public void info(String tag, String msg, String sensitiveMsg) {
        log(Config.Level.INFO, tag, msg, sensitiveMsg);
    }

    public void info(String tag, String msg) {
        info(tag, msg, null);
    }

    public void err(String tag, String msg, String sensitiveMsg) {
        log(Config.Level.ERROR, tag, msg, sensitiveMsg);
    }

    public void err(String tag, Exception e) {
        err(tag, exceptionToString(e), null);
    }

    public void debug(String tag, String msg, String sensitiveMsg) {
        log(Config.Level.DEBUG, tag, msg, sensitiveMsg);
    }

    public void debug(String tag, String msg) {
        debug(tag, msg, null);
    }

    public void warn(String tag, String msg, String sensitiveMsg) {
        log(Config.Level.WARN, tag, msg, sensitiveMsg);
    }

    public void warn(String tag, String msg) {
        warn(tag, msg, null);
    }

    public String exceptionToString(Exception e) {
        if (e == null) {
            return "";
        }
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        e.printStackTrace(printWriter);
        return result.toString();
    }

}
