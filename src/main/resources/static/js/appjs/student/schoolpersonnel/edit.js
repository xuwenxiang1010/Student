$().ready(function() {
    laydate.render({
        elem: '#inTime', //指定元素
        type: 'datetime',
        value: '',
        ready: function(date){
            $(".layui-laydate-footer [lay-type='datetime'].laydate-btns-time").click();
            $(".laydate-main-list-0 .layui-laydate-content li ol li:last-child").click();
            $(".layui-laydate-footer [lay-type='date'].laydate-btns-time").click();
        }
    });
    laydate.render({
        elem: '#outTime', //指定元素
        type: 'datetime',
        value: '',
        ready: function(date){
            $(".layui-laydate-footer [lay-type='datetime'].laydate-btns-time").click();
            $(".laydate-main-list-0 .layui-laydate-content li ol li:last-child").click();
            $(".layui-laydate-footer [lay-type='date'].laydate-btns-time").click();
        }
    });
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});
function update() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/student/schoolpersonnel/update",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            name : {
                required : true
            },
            nature : {
                required : true
            },
            healthy : {
                required : true
            },
            address : {
                required : true
            },
            inTime : {
                required : true
            },
            outTime : {
                required : true
            }
        },
        messages : {
            name : {
                required : icon + "请输入名称"
            },
            nature : {
                required : icon + "请选择身份性质"
            },
            healthy : {
                required : icon + "请输入健康状况"
            },
            address  : {
                required : icon + "请输入目前居住地址"
            },
            inTime : {
                required : icon + "请输入入校时间"
            },
            outTime : {
                required : icon + "请输入预计离校时间"
            },
        }
    })
}