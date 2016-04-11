package test;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/10.
 */
public class TestAlarming{

    public void tstGetAlarm() throws SQLException {
        final String sql = "select distinct alarmStatus " +
                "from webLogger.dbo.alarmlog " +
                "where sensorSerialNo=2 AND alarmAcknowledged = 0";
        Connection conn = null;
        Statement stat = null;

        String url = "jdbc:sqlserver://222.201.139.21:1435;databaseName=weblogger";
        String user = "sa";
        String pass = "Test123";

        conn = DriverManager.getConnection(url,user,pass);
        stat = conn.createStatement();

        ResultSet result = stat.executeQuery(sql);
        while(result.next()){
            System.out.println(result.getInt(1));
        }
        if(conn != null && !conn.isClosed())
            conn.close();


    }

    public void testUpdateAlarm() throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        String url = "jdbc:sqlserver://222.201.139.21:1435;databaseName=weblogger";
        String user = "sa";
        String pass = "Test123";

        String sql = "update webLogger.dbo.alarmlog " +
                "set alarmAcknowledged = 1 " +
                "where sensorSerialNo=1 and alarmAcknowledged=0";

        conn = DriverManager.getConnection(url,user,pass);
        stmt = conn.createStatement();

        int resultSet = stmt.executeUpdate(sql);
        if(conn != null && !conn.isClosed())
            conn.close();

        System.out.println(resultSet);
    }

    //测试是否执行sql成功
    public void testSqlIsOk(int sensorno) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        String url = "jdbc:sqlserver://222.201.139.21:1435;databaseName=weblogger";
        String user = "sa";
        String pass = "Test123";

        String sql = "select distinct alarmStatus " +
                "from webLogger.dbo.alarmlog " +
                "where sensorSerialNo=" + sensorno + " AND alarmAcknowledged = 0";

        conn = DriverManager.getConnection(url,user,pass);
        stmt = conn.createStatement();

        ResultSet result = stmt.executeQuery(sql);

        while(result.next()){
            System.out.println(result.getInt(1));
        }

        if(conn != null && !conn.isClosed())
            conn.close();

    }

    public void testInsertNoteIsOk() throws SQLException {
        String sql = "select * from webLogger.dbo.alarmnotes";
        String url = "jdbc:sqlserver://222.201.139.21:1435;databaseName=weblogger";
        String user = "sa";
        String pass = "Test123";

        Connection conn = null;
        Statement stmt = null;
        conn = DriverManager.getConnection(url,user,pass);
        stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
//        ResultSetMetaData resultSetMetaData = result.getMetaData();
//        for(int i = 1; i <= resultSetMetaData.getColumnCount(); ++i){
//            System.out.println(resultSetMetaData.getColumnClassName(i));
//        }
//
        while(result.next()){
            System.out.println(result.getString(2) + "     " + result.getString(3) + "     " + result.getString(4) + "    " + result.getString(5));
        }
        if(conn != null && !conn.isClosed())
            conn.close();
    }

    public void testAlarmLogId() throws SQLException {

        Connection conn = null;
        Statement stmt = null;

        String url = "jdbc:sqlserver://222.201.139.21:1435;databaseName=weblogger";
        String user = "sa";
        String pass = "Test123";

        final String sql = "select idAlarmLog " +
                "from webLogger.dbo.alarmlog " +
                "where sensorSerialNo=" + 8 + " and alarmAcknowledged = 0";

        conn = DriverManager.getConnection(url,user,pass);
        stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()){
            System.out.println(result.getInt(1));
        }

        if(conn != null && !conn.isClosed())
            conn.close();


    }


   public static void main(String[] args) throws SQLException {
       TestAlarming test = new TestAlarming();
//       test.tstGetAlarm();
//       System.out.println(11111111);
//       test.testUpdateAlarm();
//       System.out.println(11111111);
//       test.tstGetAlarm();


//       test.testSqlIsOk(2);


       test.testInsertNoteIsOk();

//       System.out.println(new Date().getTime());
//       System.out.println(new Timer().toString());
//
       System.out.println(new Timestamp(new Date().getTime()));

//       test.testAlarmLogId();

   }

}
