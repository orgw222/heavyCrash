//登录和注册的轮换显示
$(function (){
    $("#turnToRegist").click(function (){
        $("#loginForm").fadeOut("slow");
        $("#registForm").fadeIn("slow");
    })
    $("#backToLogin").click(function (){
        $("#registForm").fadeOut("slow");
        $("#loginForm").fadeIn("slow");
    })
})

//校验用户名
function checkLoginUsername(){
    var username=$("#loginUsername").val();
    var check=/^\w{8,16}$/;
    var flag=check.test(username);
    if(flag){
        $("#loginUsername").css("border-bottom","");
    }else{
        $("#loginUsername").css("border-bottom","2px solid red");
    }
    return flag;
}
function checkRegistUsername(){
    var username=$("#username").val();
    var check=/^\w{8,16}$/;
    var flag=check.test(username);
    if(flag){
        $("#username").css("border-bottom","");
    }else{
        $("#username").css("border-bottom","2px solid red");
    }
    return flag;
}

//校验密码
function checkLoginPassword(){
    var password=$("#loginPassword").val();
    var check=/^\w{8,16}$/;
    var flag=check.test(password);
    if(flag){
        $("#loginPassword").css("border-bottom","");
    }else{
        $("#loginPassword").css("border-bottom","2px solid red");
    }
    return flag;
}
function checkRegistPassword(){
    var password=$("#password").val();
    var check=/^\w{8,16}$/;
    var flag=check.test(password);
    if(flag){
        $("#password").css("border-bottom","");
    }else{
        $("#password").css("border-bottom","2px solid red");
    }
    return flag;
}

//校验昵称
function checkName(){
    var name=$("#name").val();
    var check=/^\w{1,20}$/;
    var flag=check.test(name);
    if(flag){
        $("#name").css("border-bottom","");
    }else{
        $("#name").css("border-bottom","2px solid red");
    }
    return flag;
}

//校验电话
function checkPhone(){
    var phone=$("#phone").val();
    var check=/^[0-9]{11}$/;
    var flag=check.test(phone);
    if(flag){
        $("#phone").css("border-bottom","");
    }else{
        $("#phone").css("border-bottom","2px solid red");
    }
    return flag;
}

//校验邮箱
function checkEmail(){
    var email=$("#email").val();
    var check=/^\w+@\w+.\w+$/;
    var flag=check.test(email);
    if(flag){
        $("#email").css("border-bottom","");
    }else{
        $("#email").css("border-bottom","2px solid red");
    }
    return flag;
}

$(function (){
    //绑定事件
    $("#loginUsername").blur(checkLoginUsername);
    $("#loginPassword").blur(checkLoginPassword);
    $("#username").blur(checkRegistUsername);
    $("#password").blur(checkRegistPassword);
    $("#name").blur(checkName);
    $("#phone").blur(checkPhone);
    $("#email").blur(checkEmail);

    //登录
    $("#loginForm").submit(function (){
        // 格式校验
        if(checkLoginUsername()&&checkLoginPassword()){
            // 传入servlet
            $.post("user/login",$(this).serialize(),function(data){
                if(data.flag){
                    // $('#modal-content').html("登录成功！");
                    // $('#modal').modal({
                    //     keyboard: false
                    // });
                    location.href="index.html";
                }else{
                    // $('#modal-content').html(data.errorMsg);
                    // $('#modal').modal({
                    //     keyboard: false
                    // });
                    alert(data.errorMsg);
                }
            });
        }
        return false;
    });

    //注册
    $("#registForm").submit(function (){
        // 格式校验
        if(checkRegistUsername()&&checkRegistPassword()&&checkName()&&checkPhone()&&checkEmail()){
            // 传入servlet
            $.post("user/regist",$(this).serialize(),function(data){
                if(data.flag){
                    // $('#modal-content').html("注册成功！");
                    // $('#modal').modal({
                    //     keyboard: false
                    // });
                    location.href="index.html";
                }else{
                    // $('#modal-content').html(data.errorMsg);
                    // $('#modal').modal({
                    //     keyboard: false
                    // });
                    alert(data.errorMsg);
                }
            });
        }
        return false;
    });
});