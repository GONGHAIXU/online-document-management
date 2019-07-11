function registerByMail(){
	window.open("registerByMail.html", "_blank");
}
function registerByPhone(){
	window.open("registerByPhone.html", "_blank");
}
function verifyByMail(){
	var username = $('#username').val();
	var mail = $('#mail').val();
	var password = $('#password').val();
	var str={"username":username,
			 "mail":mail,
			 "password":password
			 }
	$.ajax({
    type: "POST",
	contentType: "application/json;charset=utf-8",
    url: "http://localhost:8080/registerByMail",
    async:true,  
    data: JSON.stringify(str),
	dataType: "json",
    success: function (data) {
		if(data.result == "true"){
			alert("验证码已发送，请耐心等待");
		}
		else{
			alert(data.result);
		}
		},
    error: function (error) {
		alert("数据连接出错，请重试！");
        }
     });
}
function verifyByPhone(){
	var username = $('#username').val();
	var phone = $('#phone').val();
	var password = $('#password').val();
	var str={"username":username,
			 "phone":phone,
			 "password":password
			 }
	$.ajax({
    type: "POST",
	contentType: "application/json;charset=utf-8",
    url: "http://localhost:8080/registerByPhone",
    async:true,  
    data: JSON.stringify(str),
	dataType: "json",
    success: function (data) {
		if(data.result == "true"){
			alert("验证码已发送，请耐心等待");
		}
		else{
			alert(data.result);
		}
		},
    error: function (error) {
		alert("数据连接出错，请重试！");
        }
     });
}
function register(){
	var activationCode = $('#activationCode').val();
	var str={"activationCode":activationCode
			 }
	$.ajax({
    type: "POST",
	contentType: "application/json;charset=utf-8",
    url: "http://localhost:8080/verify",
    async:true,  
    data: JSON.stringify(str),
	dataType: "json",
    success: function (data) {
		if(data.result == "true"){
			window.location.href = "home.html";;
		}
		else{
			alert("验证码错误，请重试！");
		}
		},
    error: function (error) {
		alert("数据连接出错，请重试！");
        }
     });
}
}