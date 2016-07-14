package com.loar.log;

/**
 * 文件保存接口
 * Created by Justsy on 2016/7/12.
 */
public interface IRecordFile {
   void save(String dir, String fileName, String tag, String msg);
}
