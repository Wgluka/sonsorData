/**
 * Created by yukai on 2016/3/31.
 */

var alarm_id;

$(document).ready(function(){
    $.ajax({
        async: false,
        type: 'post',
        url: 'getMenuSensorAction.action',
        data: "datefield=" +  "&dategap="  + "&sensorno=" ,
        success:function(res){
            var ores = eval("(" + res + ")");

            optionMenu(ores);
            //alert(res);
            //alert(ores);

        },
        error: function(e){
            alert('Error : ' + e);
        }
    });

    $("#alarm_note").click(function(){

        console.log("click ");

        //判断当前的状态是否有效
        var chooce = $("#tab_data").text();
        //console.log(chooce);
        if(chooce == undefined || chooce == '')
            return ;
console.log(chooce);
        //判断是否需要加载警报界面
        var isAlarming = $("#" + $("#menus").attr("key")).attr("alarming");
        if(isAlarming == undefined || isAlarming == "false"){
            return ;
        }

        console.info("over");

        //加载警报界面

        var tab_alarm = $("#tab_data").html();
        //console.log("alarming          sss" + tab_alarm);
        $("#tab_alarm").html(tab_alarm + "警报处理");

        console.log("jingbao     jjj");

        $("#Sensor_alarm").load("alarm.html",function(){
            console.log("加载完成");

            //清理textarea
            $("#actions").val('');

            var sensorno = $("#menus").attr("key");
            if(sensorno == 0)
                return ;

            console.log("异步操作开始执行   jj");

            $("#tab_alarm").trigger("click");

            //异步加载警报信息
            $.ajax({
                type: 'post',
                url: 'getAlarmDataAlarmAction.action',
                data: "sensorno=" + sensorno + "&userAction=" + "&userName=" + "&logId=",
                success:function(res){
                    console.log("成功执行异步      jj");
                    console.log(res);
                    var re = eval("(" + res + ")");
                    console.log(re);

                    displayStatus(re);

                    //存放log的id
                    alarm_id = re.LogId;

                    console.info(alarm_id);

                    //$("#tab a:last").tab('show');

                    //下面一行代码会导致警报界面一直存在
                    //$("#Sensor_alarm").attr("class","active");

                    //<script>

                        //$('#tab a:last').tab('show');//初始化显示哪个tab

                        //$('#tab a').click(function (e) {
                        //    e.preventDefault();//阻止a链接的跳转行为
                        //    $(this).tab('show');//显示当前选中的链接及关联的content
                        //});
                    //})
                    //</script>
                },
                error:function(e){
                    console.log("执行异步失败      jj");
                }
            })
        });
        //console.log("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");

        //console.log("tab_data :  " + $("#tab_data").text());
        //var key = $("#menus").attr("key");
        // console.log(key);
        //var color = $("#" + $("#menus").attr("key")).attr("alarming");
        //console.log(" color : " + color);
        //if($("#")){
        //    console.log("1111");
        //}
    });
});

function displayStatus(data){
    if(data == undefined)
        return ;
     var status = data.alarmStatus;
    if(status == undefined || status == 0)
        return ;

    var dataArray = status.split(",");

    console.info(dataArray);

    //将状态信息显示到界面中
    var length = dataArray.length;
    for(var i = 0; i < length ; ++i){
        $("#sensor" + dataArray[i]).attr("checked" ,true);
    }
}

function optionMenu(menus){

    if(menus.firstMenu == undefined){
        return;
    }

    var alarmData = menus.alarm;
    if(alarmData != undefined)
        alarmData = alarmData.split(',');
    else
        alarmData = null;

    var firstMenus = menus.firstMenu.split(",");
    var menuNumber = firstMenus.length;

    for(var i = 0; i < menuNumber; ++i){

        //创建第一层子节点
        var second_menu_name = firstMenus[i];
        var second_menu = document.createElement("li");
        second_menu.innerHTML = "<a href=\"#\"><i class=\"fa fa-file-text\"></i><span class=\"text\">" +
            second_menu_name +
            "</span>" +
            " <span class=\"fa fa-angle-down pull-right\"></span></a>";


        $("#menus").append(second_menu);

        //创建子节点的容器
        var second_childs_container = document.createElement("ul");
        second_childs_container.setAttribute("class","nav sub");

        second_menu.appendChild(second_childs_container);


        //创建子节点
        var second_child = "second_" + i + "name";
        var second_child_key = "second_" + i + "key";

        var second_childs_name = menus[second_child].split(",");
        var second_childs_key = menus[second_child_key].split(",");


        var seconde_childs_length = second_childs_name.length;

        for(var j = 0; j < seconde_childs_length; ++j) {
            var third_child = document.createElement("li");

            //设置警告
            if (sensorIsAlarm(second_childs_key[j], alarmData)) {
                third_child.innerHTML = "<a href=\"#\"><i class=\"fa fa-car\"></i><span class=\"text\" style=\"color: red\">" +
                    second_childs_name[j]
                    + "</span></a>";

                third_child.setAttribute("alarming", "true");
            } else {

            third_child.innerHTML = "<a href=\"#\"><i class=\"fa fa-car\"></i><span class=\"text\">" +
                second_childs_name[j]
                + "</span></a>";

                third_child.setAttribute("alarming", "false");
             }

            third_child.setAttribute("id",second_childs_key[j]);
            third_child.setAttribute("key",second_childs_name[j]);

            third_child.onclick = function(){
                $("#menus").attr("key",this.getAttribute("id"));
                console.log(this.getAttribute("key"));

                //先除去掉警报部分
                $("#tab_alarm").html('');
                $("#Sensor_alarm").html('');

                //添加标签头名称
                $("#tab_data").html(this.getAttribute("key"));

                $("#tab_data").trigger("click");
                //,{id: second_childs_key[j]}
                //加载页面
                $("#Sensor_tab").load("sensordemo.html", function(){
                    //$("#tab_alarm").remove();
                    //$("#Sensor_alarm").remove();

                    //$("#tab_alarm").html('');
                    //$("#Sensor_alarm").html('');

                    $("#sensorno").val($("#menus").attr("key"));

                    //设置警钟的颜色
                    if( $("#" + $("#menus").attr("key")).attr("alarming") == "true"){
                        $("#alarm_note_color").attr("style","color: red");
                        console.info("red");
                    }else {
                        $("#alarm_note_color").attr("style","color: rgb(132,135,136)");
                    }

                });
            };
            second_childs_container.appendChild(third_child);
        }
    }
}

//判断sensor中的是否警告
function sensorIsAlarm(sensorNo,array){

    if(array == null)
        return false;

    var len = array.length;
    if( len == 0){
        return false;
    }

    for(var i = 0; i < len; ++i){
        if(sensorNo == array[i])
            return true;
    }
    return false;
}

