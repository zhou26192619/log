package com.loar.log;

/**
 * Created by Justsy on 2016/7/11.
 */
public class LogManager {

    private static final LogManager logManager = new LogManager();
    private static Config config;
    private static final Logger rootLogger = new Logger(new DefaultConfig());

    public static LogManager getLogManager() {
        return getLogManager(null, null);
    }

    public static LogManager getLogManager(Config config, IRecordFile recordFile) {
        if (config == null) {
            LogManager.config = new DefaultConfig();
        } else {
            LogManager.config = config;
        }
        setRecordFile(recordFile);
        return logManager;
    }

    public static void setRecordFile(IRecordFile recordFile) {
        LogManager.rootLogger.setRecordFile(recordFile);
    }

    public static void setCallback(LogCallback callback) {
        LogManager.rootLogger.setCallback(callback);
    }

    public static void setConfig(Config config) {
        LogManager.config = config;
        LogManager.rootLogger.setConfig(LogManager.config);
    }

    public static Logger getRootLogger() {
        return rootLogger;
    }

    public Logger newLogger(Config config) {
        if (config == null) {
            config = new DefaultConfig();
        }
        return new Logger(config);
    }

    public static void info(String tag, String msg) {
        LogManager.getRootLogger().info(tag, msg);
    }

    public static void debug(String tag, String msg) {
        LogManager.getRootLogger().debug(tag, msg);
    }

    public static void warn(String tag, String msg) {
        LogManager.getRootLogger().warn(tag, msg);
    }

    public static void info(String tag, String msg, String sensitiveMsg) {
        LogManager.getRootLogger().info(tag, msg, sensitiveMsg);
    }

    public static void debug(String tag, String msg, String sensitiveMsg) {
        LogManager.getRootLogger().debug(tag, msg, sensitiveMsg);
    }

    public static void warn(String tag, String msg, String sensitiveMsg) {
        LogManager.getRootLogger().warn(tag, msg, sensitiveMsg);
    }

    public static void err(String tag, String msg, String sensitiveMsg) {
        LogManager.getRootLogger().err(tag, msg, sensitiveMsg);
    }

    public static void err(String tag, String head, Exception e) {
        if (e == null) {
            return;
        }
        LogManager.getRootLogger().err(tag, head, LogManager.getRootLogger().exceptionToString(e));
    }

    public static void err(String tag, Exception e) {
        LogManager.getRootLogger().err(tag, e);
    }
}
