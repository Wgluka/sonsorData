package edu.scut.rjxy.service.impl;

import edu.scut.rjxy.dao.AlarmDao;
import edu.scut.rjxy.service.AlarmService;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yukai on 2016/4/9.
 */
public class AlarmServiceImpl implements AlarmService {

    private static final Logger logger = Logger.getLogger(AlarmServiceImpl.class);

    private AlarmDao alarmDao;

    public void setAlarmDao(AlarmDao alarmDao) {
        this.alarmDao = alarmDao;
    }

    public Map getAlarmStatus(int sensorno) {
        List alarmStatus = alarmDao.getAlarmStatus(sensorno);
        Map map = new HashMap<String,String>();

        if(alarmStatus == null || alarmStatus.size() == 0){
            map.put("alarmStatus","0");
            return map;
        }

        int length = alarmStatus.size();
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < length; ++i){
            buffer.append(alarmStatus.get(i) + ",");
        }
        buffer.setLength(buffer.length() - 1);
        map.put("alarmStatus",buffer.toString());

        logger.debug("获取alarm的种类service 返回值为" + map.toString());

        return map;
    }

    public Map clearAlarmStatus(int sensorno, String description, String noteBy) {
        boolean isok = false;
        Map map = new HashMap<String,String>();

        //测试用
        map.put("clearResult","false");
        return map;

//        isok = alarmDao.insertAlarmNote(sensorno,description,noteBy);
//
//        if(!isok){
//            map.put("clearResult","false");
//            return map;
//        }
//
//        isok = alarmDao.updateSensorAlarm(sensorno);
//
//        logger.debug("isok               isok     " + isok);
//
//        if(!isok){
//            map.put("clearResult","false");
//            return map;
//        }
//
//        map.put("clearResult","true");
//        return map;
    }
}
