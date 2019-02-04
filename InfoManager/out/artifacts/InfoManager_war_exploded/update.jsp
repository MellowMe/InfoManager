<%@ page import="cn.yufan.infomanager.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>编辑用户信息</title>
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

        button,#submit {
            border: 0;
            box-sizing: content-box;
            height: 17px;
            font: bold 14px Verdana, Arial, Helvetica, sans-serif;
            color: #FFFFFF;
            background-color: #98bf21;
            text-align: center;
            padding: 4px;
        }
    </style>
    <script>
        function updateConfirm() {
            var value = document.getElementById("confirm").value;
            if (value === "null") {
            } else if (value === "true") {
                alert("修改成功，即将返回主页");
                window.location.href = "http://localhost:8080/";
            } else if (value === "occupied") {
                alert("修改失败，用户名已被占用");
                window.history.back();
            } else {
                alert("修改失败，请稍后重试");
                window.history.back();
            }
        }
        function quit() {
            var path = window.location.pathname;
            if(path.indexOf('login')!==-1){
                var date = new Date();
                date.setTime(date.getTime()-10000);
                document.cookie = 'account=0; expires='+date.toUTCString();
                location = 'http://'+location.host+'/login.jsp';
            }else if(path.indexOf('modify')!==-1){
                location = 'http://'+location.host+'/';
            }
        }
    </script>
</head>
<body onload="updateConfirm();">
<%
    User user = (User) request.getAttribute("user");
%>
<form action="${pageContext.request.contextPath}/update.udo" method="post">
    <table>
        <caption>修改用户信息</caption>
        <tr>
            <td style="text-align: right;">用户名：</td>
            <td style="text-align: left;"><input type="text" name="name" size="24" maxlength="20" value="<%=user.getName()%>" required></td>
        </tr>
        <tr>
            <td style="text-align: right;">密码：</td>
            <td style="text-align: left;"><input type="text" name="password" size="24" maxlength="24"
                                                 value="<%=user.getPassword()%>" required/></td>
        </tr>
        <tr>
            <td style="text-align: right;">出生日期：</td>
            <td style="text-align: left;"><input type="date" name="birthday" value="<%=user.getBirthday()%>" required/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right;">邮箱：</td>
            <td style="text-align: left;"><input type="email" name="email" size="24" maxlength="40"
                                                 value="<%=user.getEmail()%>" required/></td>
        </tr>
        <tr>
            <td style="text-align: right;">电话号码：</td>
            <td style="text-align: left;"><input type="text" name="phone_no" size="24" maxlength="11"
                                                 value="<%=user.getPhone_no()%>" required/></td>
        </tr>
        <tr>
            <td style="text-align: right;">住址：</td>
            <td style="text-align: left;"><input type="text" name="address" size="24" maxlength="50"
                                                 value="<%=user.getAddress()%>"/></td>
        </tr>
        <tr>
            <td><input id="confirm" type="hidden" name="message" value="<%=request.getAttribute("message")%>"/>
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="hidden" name="oldName" value="<%=user.getName()%>"/>
            </td>
            <td style="text-align: left;">
                <input type="submit" onmouseover="this.style.backgroundColor='#7a991a'"
                       onmouseout="this.style.backgroundColor='98BF21'" id="submit" value="确认提交"/>
                <button type="button" id="out" onclick="quit();" onmouseover="this.style.backgroundColor='#7a991a'"
                        onmouseout="this.style.backgroundColor='98BF21'" >退出</button>
        </tr>
    </table>
</form>
</body>
</html>
