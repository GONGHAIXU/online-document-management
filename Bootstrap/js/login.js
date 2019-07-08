function register() {
	//跳转到注册界面register.html进行注册
	window.open("registerByMail.html", "_blank");  //_self,_parent,_top,_blank
}
var username;//登录用户的用户名
var identify;//登录用户的身份
//检查是否已登录和登录者的身份
function checkIdentify() {
	var username;//登录用户的用户名
	var identify;//登录用户的身份
    username = getCookie("username");
    if(username == null || username == "") {
        alert("用户尚未登录");
        location.assign("login.html");//如果用户还未登录，那么返回登录界面
    } //else {
      //  identify = getCookie("identify");
      //  if(identify == "1") {
      //      alert("1");
      //      location.assign("teacher.html");
      //  }
   // }
}
function setCookie(c_name, value) {
    document.cookie = c_name + "=" + escape(value); 
}
function login() {
    //登录逻辑
	var username = $('#username').val();
	var password = $('#password').val();
	var str={"username":username,
			 "password":password}
	var newPage = window.open('about:blank');
	$.ajax({
    type: "POST",
	contentType: "application/json;charset=utf-8",
    url: "http://localhost:8080/loginByUsername",
    async:true,  
    data: JSON.stringify(str),
	dataType: "json",
    success: function (data) {
		if(data.result == "true"){
			//location.assign("home.html");
			newPage.location.href="home.html";
			setCookie("username",username)
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
