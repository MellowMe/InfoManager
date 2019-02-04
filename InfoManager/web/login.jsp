<!--<%@ page contentType="text/html;charset=UTF-8" %>-->
<html>
<head>
    <title>登录页</title>
    <style>
        body {
            background-color: aliceblue;
        }

        form {
            margin-left: 100px;
            padding: 30px 50px;
            border: 1px silver solid;
            border-radius: 20px;
            background-color: antiquewhite;
            width: 400px;
        }

        button {
            font-weight: bold;
            font-size: 14px;
            font-family: Verdana, Arial, Helvetica, sans-serif;
            color: #FFFFFF;
            background-color: #98bf21;
            text-align: center;
            padding: 4px;
        }
    </style>
    <script>
        function auto_login() {
            let cookies = document.cookie.split("; ");
            let already = false;
            let cookie;
            for (cookie of cookies) {
                let entry = cookie.split("=");
                if (entry[0] === "account") {
                    document.getElementById("uid").value = entry[1];
                    already = true;
                    break;
                }
            }
            if (already) {
                document.getElementById("forward").click();
            }
        }
        function renewCaptcha() {
            document.getElementById('img').src = "${pageContext.request.contextPath}/captcha.udo" + '?'+Math.random();
        }
    </script>
</head>
<body onload="auto_login();">
<form action="${pageContext.request.contextPath}/login.udo" method="post">
    <table>
        <tr>
            <td><label for="name">用户名：</label></td>
            <td><input type="text" id="name" name="name"/></td>
        </tr>
        <tr>
            <td> <label for="password">密 码：</label></td>
            <td><input type="password" id="password" name="password"/></td>
        </tr>
        <tr>
            <td></td>
            <td><img id="img" src="${pageContext.request.contextPath}/captcha.udo" alt="验证码" onclick="renewCaptcha();"/></td>
        </tr>
       <tr>
           <td><label for="captcha">验证码：</label></td>
           <td><input type="text" id="captcha" name="captcha"/>
               <input type="hidden" id="uid" name="uid"/>
           </td>
       </tr>
        <tr>
            <td colspan="2"><label for="expires">记住我一周</label> <input type="radio" name="expires" id="expires" value="7"/>
                &nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" id="forward"
                     onmouseover="this.style.backgroundColor='#7a991a'"
                   onmouseout="this.style.backgroundColor='98BF21'" >登&nbsp;&nbsp;录</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
