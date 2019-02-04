<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>注册新用户</title>
    <script>function formCheck() {
        var psw = document.getElementById("psw").value;
        var pswConfirm = document.getElementById("pswConfirm").value;
        if(psw !== pswConfirm){
            alert("两次密码不一致，提交失败");
            return false;
        }
    }

    function addConfirm() {
        var value = document.getElementById("confirm").value;
        if (value === "null") {
        } else if (value === "true") {
            var back = confirm("注册新用户成功，现在要回到主页吗？");
            if (back === true) {
                window.location.href = "http://localhost:8080/index.jsp";
            }
        } else if(value ==="occupied"){
            alert("注册失败，用户名已被占用");
            window.history.back();
        }else{
            alert("注册失败，请稍后再试");
            window.history.back();
        }
    }
    </script>
    <style>
        body {
            background-color: aliceblue;
        }

        table {
            margin-left: 100px;
            padding: 30px 50px;
            border: 1px silver solid;
            border-radius: 20px;
            background-color: antiquewhite;
            width: 400px;
        }

        #submit {
            border: 0;
            box-sizing: content-box;
            height: 17px;
            font: bold 14px Verdana, Arial, Helvetica, sans-serif;
            color: #FFFFFF;
            background-color: #98bf21;
            text-align: center;
            padding: 4px;
            text-decoration: none;
        }
    </style>
</head>
<body onload="addConfirm();">
<form action="${pageContext.request.contextPath}/add.udo" onsubmit="return formCheck();" method="post">
    <table>
        <caption>注册新用户</caption>
        <tr>
            <td style="text-align: right;">用户名：</td>
            <td style="text-align: left;"><input type="text" name="name" size="24" maxlength="20" required/></td>
        </tr>
        <tr>
            <td style="text-align: right;">密码：</td>
            <td style="text-align: left;"><input type="password" id="psw" name="password" size="24" maxlength="24"
                                                 required/></td>
        </tr>
        <tr>
            <td style="text-align: right;">密码确认：</td>
            <td style="text-align: left;"><input type="password" id="pswConfirm" name="pswConfirm" size="24"
                                                 maxlength="24" required/></td>
        </tr>
        <tr>
            <td style="text-align: right;">出生日期：</td>
            <td style="text-align: left;"><input type="date" name="birthday" required/></td>
        </tr>
        <tr>
            <td style="text-align: right;">邮箱：</td>
            <td style="text-align: left;"><input type="email" name="email" size="24" maxlength="40" required/></td>
        </tr>
        <tr>
            <td style="text-align: right;">电话号码：</td>
            <td style="text-align: left;"><input type="text" name="phone_no" size="24" maxlength="11" required/></td>
        </tr>
        <tr>
            <td style="text-align: right;">住址：</td>
            <td style="text-align: left;"><input type="text" name="address" size="24" maxlength="50" /></td>
        </tr>
        <tr>
            <td><input id="confirm" type="hidden" name="message" value="<%=request.getAttribute("message")%>"/></td>
            <td style="text-align: left;">
                <input type="submit" onmouseover="this.style.backgroundColor='#7a991a'"
                       onmouseout="this.style.backgroundColor='98BF21'" id="submit" value="确认提交"/>
        </tr>
    </table>
</form>
</body>
</html>
