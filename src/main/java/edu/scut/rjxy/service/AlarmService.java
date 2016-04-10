package edu.scut.rjxy.service;

import java.util.Map;

/**
 * Created by yukai on 2016/4/9.
 */
public interface AlarmService {

    //获得alarm的状态信息
    public Map getAlarmStatus(int sensorno);

    //执行alarm的清理工作
    public Map clearAlarmStatus(int sensorno, String description, String noteBy);
}
