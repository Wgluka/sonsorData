package edu.scut.rjxy.model;

/**
 * Created by yukai on 2016/4/9.
 */
public class AlarmParameter {

    private int sensorno;
//    private String alarmList;
    private String userAction;
    private String userName;

    public AlarmParameter() {
    }

    public int getSensorno() {
        return sensorno;
    }

    public void setSensorno(int sensorno) {
        this.sensorno = sensorno;
    }

//    public String getAlarmList() {
//        return alarmList;
//    }
//
//    public void setAlarmList(String alarmList) {
//        this.alarmList = alarmList;
//    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "AlarmParameter{" +
                "sensorno=" + sensorno +
//                ", alarmList='" + alarmList + '\'' +
                ", userAction='" + userAction + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
