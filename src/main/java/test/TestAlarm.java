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
//            String sql = "SELECT sensorSerialNo FROM webLogger.dbo.sensor";

//            String sql =  "select grid.idGrid,grid.gridName,sensor.sensorSerialNo,sensor.sensorName " +
//                    " from webLogger.dbo.grid,webLogger.dbo.gridsensor,webLogger.dbo.sensor " +
//                    " where gridsensor.deleted=0 and grid.idGrid = gridsensor.idGrid " +
//                    " and gridsensor.sensorSerialNo = sensor.sensorSerialNo and sensor.deleted=0 order by grid.idGrid ";

            String sql = "select sensorSerialNo from webLogger.dbo.alarmlog " +
                    "where alarmAcknowledged = 0";

            ResultSet result = stmt.executeQuery(sql);

            int index = 0;
            while(result.next()){
                System.out.println(result.getLong(1));
//                System.out.println(result.getLong(1) + "  " + result.getString(2) + "  " + result.getLong(3) + "  " + result.getString(4));

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
