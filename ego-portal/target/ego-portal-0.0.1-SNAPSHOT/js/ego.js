var EG = EGOU = {
	checkLogin : function(){
		var _ticket = $.cookie("EG_TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://192.168.2.174:8080/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					////127.0.0.1:8084/user/logout/"+_ticket+"\
					var html = username + "，欢迎来到易购！<a href=\"javascript:void(0);\" onclick=\"EG.logout()\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	},
	logout:function() {
		//alert();
		var _ticket = $.cookie("EG_TOKEN");
		$.ajax({
			url: "http://192.168.2.174:8080/user/logout/"+_ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					location.href="http://192.168.2.175:8082";
				}
			}
		});
	}
	
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	EG.checkLogin();
});