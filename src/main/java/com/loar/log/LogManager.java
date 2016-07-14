package com.loar.log;

/**
 * Created by Justsy on 2016/7/11.
 */
public class LogManager {

    private static final LogManager logManager = new LogManager();
    private static Config config;
    private static final Logger rootLogger;
    private static IRecordFile recordFile;

    static {
        getLogManager(config, null);
        rootLogger = new Logger(logManager.config);
    }

    public static LogManager getLogManager() {
        return getLogManager(null, null);
    }

    public static LogManager getLogManager(Config config, IRecordFile recordFile) {
        if (config == null) {
            LogManager.config = new DefaultConfig();
        } else {
            LogManager.config = config;
        }
        LogManager.recordFile = recordFile;
        return logManager;
    }

    public static void setRecordFile(IRecordFile recordFile) {
        LogManager.recordFile = recordFile;
        rootLogger.setRecordFile(LogManager.recordFile);
    }

    public static void setConfig(Config config) {
        LogManager.config = config;
        rootLogger.setConfig(LogManager.config);
    }

    public static Logger getRootLogger() {
        return rootLogger;
    }

    public Logger newLogger(Config config) {
        if (config == null) {
            config = new DefaultConfig();
        }
        Logger logger = new Logger(config);
        logger.setRecordFile(recordFile);
        return logger;
    }

    public static void info(String tag, String msg) {
        LogManager.getRootLogger().info(tag, msg);
    }

    public static void err(String tag, String msg) {
        LogManager.getRootLogger().err(tag, msg);
    }

    public static void debug(String tag, String msg) {
        LogManager.getRootLogger().debug(tag, msg);
    }

    public static void warn(String tag, String msg) {
        LogManager.getRootLogger().warn(tag, msg);
    }
}
