package edu.scut.rjxy.dao.impl;

import edu.scut.rjxy.dao.AlarmDao;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.type.IntegerType;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by yukai on 2016/4/9.
 */
public class AlarmDaoImpl extends HibernateDaoSupport implements AlarmDao {

    private static final Logger log = Logger.getLogger(AlarmDaoImpl.class);

    //获得alarm类型
    public List getAlarmStatus(int sensorno) {

        final String sql = "select distinct alarmStatus " +
                "from webLogger.dbo.alarmlog " +
                "where sensorSerialNo=:sensorno AND alarmAcknowledged = 0";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.setInteger("sensorno",sensorno);
        query.addScalar("alarmStatus", new IntegerType());
//        SQLQuery query = this.getSession().createQuery(sql);

        return query.list();
    }

    //将警报恢复为0
    public boolean updateSensorAlarm(int sensorno) {
        final String sql = "update webLogger.dbo.alarmlog " +
                "set alarmAcknowledged = 1 " +
                "where sensorSerialNo= :sensorno and alarmAcknowledged = 0";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.setInteger("sensorno",sensorno);
        int result = query.executeUpdate();

        logger.debug("result               result number :     " + result);

        return result == 0 ? false : true ;
    }

    public List getAlarmLogId(int sensorno){
        final String sql = "select idAlarmLog " +
                "from webLogger.dbo.alarmlog " +
                "where sensorSerialNo= :sensorno and alarmAcknowledged = 0";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.setInteger("sensorno",sensorno);
        return query.list();
    }

    public boolean insertAlarmNote(int sensorno, String description, String noteBy) {

        logger.debug("insert   ");
        List<Integer> list = getAlarmLogId(sensorno);

        logger.debug("list           " + list.size());

        if( list == null || list.size() == 0)
            return false;

        final String sql = "insert into webLogger.dbo.alarmnotes(noteDescription, noteEntryDateTime,idAlarmLog,noteBy)" +
                " VALUES( :noteDescription1, :noteEntryDateTime, :idAlarmLog, :noteBy)";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.setString("noteDescription1", description);
        query.setDate("noteEntryDateTime",new Timestamp(new Date().getTime()));
        log.debug(new Timestamp(new Date().getTime()));
        query.setString("noteBy",noteBy);
        for(int i = 0; i < list.size(); ++i) {
            query.setInteger("idAlarmLog", list.get(i));
            if( 0 == query.executeUpdate())
                return false;
        }
        return true;
    }
}
