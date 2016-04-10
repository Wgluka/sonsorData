package edu.scut.rjxy.dao;

import java.util.List;

/**
 * Created by yukai on 2016/4/9.
 */
public interface AlarmDao {

    /**
     *
     */
    public List getAlarmStatus(int sensorno);

    /**
     *
     */
    public boolean updateSensorAlarm(int sensorno);

    public List getAlarmLogId(int sensorno);

    /**
     *
     */
    public boolean insertAlarmNote(int sensorno, String description, String noteBy);

}
