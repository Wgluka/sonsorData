/**
 * Created by yukai on 2016/4/9.
 */
$(document).ready(function(){

    $("#Acknowledge").click(function() {

        //var checkedlist;
        var sensorno = $("#menus").attr("key");
        if (sensorno == 0)
            return;

        var user_action = $("#actions").val();
        var user_name = $("#user").val();

        //去掉前后的空格
        $.trim(user_action);
        $.trim(user_name);

        //console.info("qing li zhi hou de user action " + user_action);
        //console.info("qing li zhi hou de user name " + user_name);


        if (user_action == '') {
            alert("请输入操作信息！");
            return ;
        }
        if(user_name == ''){
            alert("请输入操作人员的名称");
            return ;
        }

        //console.log("异步操作开始更新数据");

        //console.log("acknowledged 之前的 log id" + alarm_id);

        if (alarm_id == undefined){
            //console.log("alarm_id 的值为空");
            return ;
        }

        $.ajax({
            type: 'post',
            url: 'updateAlarmDataAlarmAction.action',
            data: "sensorno=" + sensorno + "&userAction=" + user_action + "&userName=" + user_name +
                        "&logId=" + alarm_id,
            success:function(res){
                //console.log("成功执行异步      ");
                var re = eval("(" + res + ")");
                //console.log(re);

                if(re == undefined)
                    return ;

                 var isok = re.clearResult;

                //console.log(isok);
                if(!isok || isok == "false"){
                    //失败清理之后的显示信息
                    alert("操作失败");

                    return ;
                }

                //成功清理之后应该将页面清除
                $("#tab_alarm").html('');
                $("#Sensor_alarm").html('');
                //tianjia
                $("#tab_alarm").attr("href","#");

                $("#" + $("#menus").attr("key")).attr("alarming" , false);
                $("#alarm_note_color").attr("style","color: rgb(132,135,136)");
                $("#" + $("#menus").attr("key") + " " + "span").attr("style","color: rgb(132,135,136)");

                //console.info("qing li wan cheng le color black");

                //模拟点击事件
                $("#tab_data").trigger("click");

                //console.log("tab");

                alarm_id = undefined;
                return ;
            },
            error:function(e){
                console.log("网络异常，操作失败，请稍后再试！");
            }
        });
    });
});

