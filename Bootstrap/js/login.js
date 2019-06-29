function register() {
	//跳转到注册界面register.html进行注册
	window.open("registerByMail.html", "_blank");  //_self,_parent,_top,_blank
	}
function login() {
    //登录逻辑
	var username = $('#username').val();
	var password = $('#password').val();
	var str={"username":username,
			 "password":password}
	//var newPage = window.open('about:blank');
	$.ajax({
    type: "POST",
	contentType: "application/json;charset=utf-8",
    url: "http://localhost:8080/loginByUsername",
    async:true,  
    data: JSON.stringify(str),
	dataType: "json",
    success: function (data) {
		if(data.result == "true"){
			window.location.href = "home.html";
			//newPage.location.href="home.html";
		}
		else{
			alert("账号或密码错误，请重试！");
		}
		},
    error: function (error) {
		alert("数据连接出错，请重试！");
        }
     });
}
