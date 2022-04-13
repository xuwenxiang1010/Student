$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function getCheckedCourses(){
	var adIds = "";
	$("input:checkbox[name=course]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}

function save() {
	$("#courseIds").val(getCheckedCourses());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/stu/save",
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
			stuNo : {
				required : true
			},
			name : {
				required : true
			},
			sex : {
				required : true
			},
			age : {
				required : true,
				range:[1,100] 
			},
			grade : {
				required : true
			},
			mobile : {
				required : true,
				minlength : 11,
				maxlength : 11
			},
			email : {
				required : true,
				email : true
			}
		},
		messages : {
			stuNo : {
				required : icon + "请输入学号"
			},
			name : {
				required : icon + "请输入姓名"
			},
			sex : {
				required : icon + "请选择性别"
			},
			age : {
				required : icon + "请输入年龄",
				range : icon + "年龄必须在1-100之间"
			},
			grade : {
				required : icon + "请选择年级"
			},
			mobile : {
				required : icon + "请输入电话号码",
				minlength : icon + "请输入11位电话号码",
				maxlength : icon + "请输入11位电话号码"
			},
			email : {
				required : icon + "请输入个人邮箱",
				email : icon + "请输入正确格式的邮箱"
			}
		}
	})
}


var openSch = function(){
	layer.open({
		type:2,
		title:"选择学院",
		area : [ '300px', '450px' ],
		content:"/system/sch/treeView"
	})
}
function loadSch( schId,schName){
	$("#schId").val(schId);
	$("#schName").val(schName);
}
