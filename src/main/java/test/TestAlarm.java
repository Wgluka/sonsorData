package test;

import java.sql.*;

/**
 * Created by yukai on 2016/3/23.
 */
public class TestAlarm {

    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;

        String url = "jdbc:sqlserver://222.201.139.22:49156;databaseName=weblogger";
        String user = "sensorData";
        String pass = "Sensor123";

        try {
            conn = DriverManager.getConnection(url,user,pass);
            stmt = conn.createStatement();

            String sql = "select sensorSerialNo from webLogger.dbo.alarmlog " +
                    "where alarmAcknowledged = 0";

            ResultSet result = stmt.executeQuery(sql);

            while(result.next()){
                System.out.println(result.getLong(1));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}
