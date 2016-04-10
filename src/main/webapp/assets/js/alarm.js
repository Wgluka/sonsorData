/**
 * Created by yukai on 2016/4/9.
 */
$(document).ready(function(){
   //$("#alarm_note").click(function(){
   //
   //    //判断当前的状态是否有效
   //    var chooce = $("#tab_data").text();
   //    //console.log(chooce);
   //    if(chooce == undefined || chooce == '')
   //         return ;
   //
   //    //判断是否需要加载警报界面
   //    var isAlarming = $("#" + $("#menus").attr("key")).attr("alarming");
   //    if(!isAlarming){
   //        return ;
   //    }
   //
   //    //加载警报界面
   //
   //    var tab_alarm = $("#tab_data").html();
   //    //console.log("alarming          sss" + tab_alarm);
   //    $("#tab_alarm").html(tab_alarm + "警报处理");
   //
   //    //console.log("jingbao     jjj");
   //
   //    $("#Sensor_alarm").load("alarm.html",function(){
   //        //console.log("加载完成");
   //
   //        var sensorno = $("#menus").attr("key");
   //        if(sensorno == 0)
   //             return ;
   //
   //        console.log("异步操作开始执行   jj");
   //
   //        //异步加载警报信息
   //        $.ajax({
   //            type: 'post',
   //            url: 'getAlarmDataAlarmAction.action',
   //            data: "sensorno=" + sensorno + "&userAction=" + "&userName=",
   //            success:function(res){
   //                 console.log("成功执行异步      jj");
   //            },
   //            error:function(e){
   //                console.log("执行异步失败      jj");
   //            }
   //        })
   //    });
   //    //console.log("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
   //
   //    //console.log("tab_data :  " + $("#tab_data").text());
   //    //var key = $("#menus").attr("key");
   //    // console.log(key);
   //    //var color = $("#" + $("#menus").attr("key")).attr("alarming");
   //    //console.log(" color : " + color);
   //    //if($("#")){
   //    //    console.log("1111");
   //    //}
   //});

    $("#Acknowledge").click(function(){

        //var checkedlist;
        var sensorno = $("#menus").attr("key");
        if(sensorno == 0)
            return ;

        var user_action = $("#actions").val();
        var user_name = $("#user").val();

        //去掉前后的空格
        $.trim(user_action);
        $.trim(user_name);

        console.info("qing li zhi hou de user action " + user_action);
        console.info("qing li zhi hou de user name " + user_name);


        if(user_action == '')
            alert("请输入操作信息！");
        if(user_name == '')
            alert("请输入操作人员的名称");

        console.log("异步操作开始更新数据");

        $.ajax({
            type: 'post',
            url: 'updateAlarmDataAlarmAction.action',
            data: "sensorno=" + sensorno + "&userAction=" + user_action + "&userName=" + user_name,
            success:function(res){
                console.log("成功执行异步      ");
                var re = eval("(" + res + ")");
                console.log(re);

                if(re == undefined)
                    return ;

                 var isok = re.clearResult;

                console.log(isok);
                if(!isok){
                    //失败清理之后的显示信息


                    return ;
                }

                //成功清理之后的显示信息

                return ;

            },
            error:function(e){
                console.log("执行异步失败      ");
            }
        });
    });
});

