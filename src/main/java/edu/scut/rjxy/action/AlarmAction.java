package edu.scut.rjxy.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import edu.scut.rjxy.model.AlarmParameter;
import edu.scut.rjxy.service.AlarmService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yukai on 2016/4/9.
 */
public class AlarmAction extends ActionSupport
    implements ModelDriven{

    private static final Logger LOGGER = Logger.getLogger(AlarmAction.class);

    AlarmParameter alarmParameter = new AlarmParameter();

    private AlarmService alarmService;
    private String result;

    public void setAlarmService(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    public Object getModel() {
        return alarmParameter;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String updateAlarmData(){
        LOGGER.debug("接受到了                          updateAla");

        if(!isNullAlarmParamenter(alarmParameter) || alarmParameter.getLogId() == null){
            result = new JSONObject().put("clearResult","false").toString();
            return "fail";
        }

        LOGGER.debug(alarmParameter.getUserAction());
        LOGGER.debug(alarmParameter.getUserName());

        Map map = alarmService.clearAlarmStatus(alarmParameter.getSensorno(),
                alarmParameter.getUserAction(),
                alarmParameter.getUserName(),
                alarmParameter.getLogId());

        JSONObject jsonObject = JSONObject.fromObject(map);
        result = jsonObject.toString();

        LOGGER.debug("处理结果：   "  + result);

        return "success";
    }

    public String getAlarmData(){
        LOGGER.debug("接受到了                        getAlarmData");

        if(alarmParameter.getSensorno() == 0){
            result = new JSONObject().put("alarmStatus","0").toString();
            return "fail";
        }

        Map map = alarmService.getAlarmStatus(alarmParameter.getSensorno());

        JSONObject jsonObject = JSONObject.fromObject(map);
        result = jsonObject.toString();
        return "success";
    }

    private boolean isNullAlarmParamenter(AlarmParameter alarmParameter){
        if(alarmParameter == null)
            return false;
        if(alarmParameter.getSensorno() == 0)
            return false;
        if(alarmParameter.getUserAction() == null || alarmParameter.getUserAction() == "")
            return false;
        if(alarmParameter.getUserName() == null || alarmParameter.getUserName() == "")
            return false;
        return true;
    }
}
