<%@ page import="cn.yufan.infomanager.conctroller.VisitCount" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Root Login</title>
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
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/rootLogin.udo" method="post">
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
            <td><button type="submit"   onmouseover="this.style.backgroundColor='#7a991a'"
                        onmouseout="this.style.backgroundColor='98BF21'" value="">登&nbsp;&nbsp;录</button></td>
        </tr>
        <tr>
            <td></td>
            <td>访问次数：<%=VisitCount.count%></td>
        </tr>
    </table>
</form>
</body>
</html>
