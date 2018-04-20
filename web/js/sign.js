function goSign() {
    mgzAjx(true,"SignAction","text","post","",function (result) {
    });
}
function sign(step) {
    switch (step) {
        case 1:
            var data = $("#signForm").serializeArray();
            var p1 = "",p2 = "",email = "";
            $.each(data,function (i, e) {
               if(e.name == "signPassword"){
                   p1 = e.value;
               } else if (e.name == "passwordConfirm"){
                   p2 = e.value;
               } else if (e.name == "userEmail"){
                   email = e.value;
               }
            });
            if (p1 == p2) {
                mgzAjx(true,"SignAction","text","post",data,function (result) {
                    if(result == "success"){
                        localStorage.mgzUserEmail = email;
                    }else if(result == "error"){
                        localStorage.mgzUserEmail = email;
                        if(window.confirm("该邮箱已注册，是否登录？")){
                            mgzAjx(false,"LoginAction","json","post",{userEmail:email});
                        }else {
                            goSign();
                        }
                    }
                });
            }else{
                alert("密码不相同，请重新输入！");
                $("#signForm").clear();
            }
            break;
        case 2:
            break;
        case 3:
            break;
    }
}