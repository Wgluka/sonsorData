/**
 * Created by yukai on 2016/3/31.
 */

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
});

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

        for(var j = 0; j < seconde_childs_length; ++j){
            var third_child = document.createElement("li");

            //设置警告
            if(sensorIsAlarm(second_childs_key[j],alarmData))
                third_child.innerHTML = "<a href=\"#\"><i class=\"fa fa-car\"></i><span class=\"text\" style=\"color: red\">" +
                  second_childs_name[j]
                  + "</span></a>";
            else
                third_child.innerHTML = "<a href=\"#\"><i class=\"fa fa-car\"></i><span class=\"text\">" +
                    second_childs_name[j]
                    + "</span></a>";

            third_child.setAttribute("id",second_childs_key[j]);
            third_child.setAttribute("key",second_childs_name[j]);

            third_child.onclick = function(){
                $("#menus").attr("key",this.getAttribute("id"));

                $("#tabs").load("sensordemo.html",{id: second_childs_key[j]}, function(){
                    $("#sensorno").val($("#menus").attr("key"));
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

