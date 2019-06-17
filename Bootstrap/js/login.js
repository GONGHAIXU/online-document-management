 function register() {
	//跳转到注册界面register.html进行注册
	window.open("register.html", "_blank");  //_self,_parent,_top,_blank
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
		newPage.location.href="home.html";
		alert("登录成功！")
		},
    error: function (error) {  
		alert("请求在连接过程中出现错误");
        }
     });
}
