$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});
function update() {
    $("#nameId").val(getCheckedName());
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
function getCheckedName(){
    var adIds = "";
    $("input:checkbox[name=name]:checked").each(function(i) {
        if (0 == i) {
            adIds = $(this).val();
        } else {
            adIds += ("," + $(this).val());
        }
    });
    return adIds;
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            name : {
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
            inTime : {
                required : icon + "请输入入校时间"
            },
            outTime : {
                required : icon + "请输入预计离校时间"
            }
        }
    })
}