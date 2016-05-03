package edu.scut.rjxy.service.impl;

import edu.scut.rjxy.dao.AlarmDao;
import edu.scut.rjxy.service.AlarmService;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yukai on 2016/4/9.
 */
@Transactional
public class AlarmServiceImpl implements AlarmService {

    private static final Logger logger = Logger.getLogger(AlarmServiceImpl.class);

    private AlarmDao alarmDao;

    public void setAlarmDao(AlarmDao alarmDao) {
        this.alarmDao = alarmDao;
    }

    public Map getAlarmStatus(int sensorno) {
        List<Object[]> alarmStatus = alarmDao.getAlarmStatus(sensorno);
        Map map = new HashMap<String,String>();

        logger.debug("getAlarm Status :   " + alarmStatus.size());

        if(alarmStatus == null || alarmStatus.size() == 0){
            map.put("alarmStatus","0");
            return map;
        }

        int length = alarmStatus.size();
        StringBuffer buffer = new StringBuffer();
        StringBuffer id_buffer = new StringBuffer();
        for(int i = 0; i < length; ++i){
            Object[] objects = alarmStatus.get(i);
            id_buffer.append(objects[0] + ",");
            buffer.append(objects[1] + ",");
        }
        id_buffer.setLength(id_buffer.length() - 1);
        buffer.setLength(buffer.length() - 1);
        map.put("LogId",id_buffer.toString());
        map.put("alarmStatus",buffer.toString());

        logger.debug("获取alarm的种类service 返回值为" + map.toString());

        return map;
    }

    public Map clearAlarmStatus(int sensorno, String description, String noteBy, String log_id_array) {
        boolean isok = false;
        Map map = new HashMap<String,String>();


        String[] id_str = log_id_array.split(",");
        if(id_str == null || id_str.length == 0){
            map.put("clearResult","false");
            return map;
        }

        List<Integer> id_list = new ArrayList<Integer>();

        for(String s : id_str){
            id_list.add(Integer.parseInt(s));
        }

        //需要修改为根据id来插入信息
        isok = alarmDao.insertAlarmNote(sensorno,description,noteBy, id_list);

        if(!isok){
            map.put("clearResult","false");
            return map;
        }

        //要根据id进行更新
        isok = alarmDao.updateSensorAlarm(id_list);

        logger.debug("isok               isok     " + isok);

        if(!isok){
            map.put("clearResult","false");
            return map;
        }

        map.put("clearResult","true");
        return map;
    }
}
