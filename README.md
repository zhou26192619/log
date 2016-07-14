# log
Android 日志简单打印小框架
默认配置已经配置好了
使用logManager.info(tag,msg)即可。

默认为发布模式，info级别的以上的才输出。
也可 //初始化日志打印配置
        LogManager.setConfig(new Config() {
            @Override
            public String setFileDir() {
                return "这里为文件路径";
            }

            @Override
            public String setFileName() {
                return "文件名";
            }
            //是否未开发模式
            @Override
            public boolean isDevelop() {
                return true;
            }

            //输出级别，非开发模式时生效
            @Override
            public Level setLevel() {
                return Level.INFO;
            }
        });
        
默认不输出到文件，需要输出到文件的话，请自己实现IRecordFile去输出，同事config的文件路径和文件名不能为空
设置日志的输出到文件
        LogManager.setRecordFile(new IRecordFile() {
            @Override
            public void save(String dir, String fileName, String tag, String msg) {
                try {
                    FileOperator.appendToFile(DateTimeUtil.datetimeToString(new Date(), null) + "  " + tag + " >> " + msg + "\n", dir, fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
