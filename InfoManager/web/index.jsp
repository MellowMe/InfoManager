<%@ page import="java.util.List" %>
<%@ page import="cn.yufan.infomanager.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>InfoManager</title>
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

        a:link, a:visited, #submit {
            font-weight: bold;
            font-size: 14px;
            font-family: Verdana, Arial, Helvetica, sans-serif;
            color: #FFFFFF;
            background-color: #98bf21;
            text-align: center;
            padding: 4px;
            text-decoration: none;
        }

        a:hover, a:active {
            background-color: #7A991A;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/query.udo" method="post">
    <table>
        <tr>
            <td style="text-align: right;">用户名：</td>
            <td style="text-align: left;"><input type="text" name="name" size="24"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">邮箱：</td>
            <td style="text-align: left;"><input type="text" name="email" size="24"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">电话号码：</td>
            <td style="text-align: left;"><input type="text" name="phone_no" size="24"></td>
        </tr>
        <tr>
            <td></td>
            <td style="text-align: left;">
                <input style="border:0;box-sizing: content-box;height: 17px;"
                       onmouseover="this.style.backgroundColor='#7a991a'"
                       onmouseout="this.style.backgroundColor='98BF21'" id="submit" type="submit" value="查询用户"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td style="text-align: left;">
                <a href="${pageContext.request.contextPath}/add.jsp">注册用户</a>
            </td>
        </tr>
    </table>
</form>
<details style="margin-left: 120px;font:italic lighter 14px 宋体;color: cadetblue; ">
    <summary>说明</summary>
    输入相关内容后点击按钮进行模糊查询，不输入任何内容则显示所有用户
</details>
<br/>
<%List<User> list = (List<User>) request.getAttribute("userList");%>
<%
    if (list != null) {
        if (!list.isEmpty()) {
%>
<table style="width:auto;" border="1px" id="info">
    <style>
        #info td {
            padding: 3px 5px;
            text-align: center;
        }
    </style>
    <tr>
        <td>ID</td>
        <td>用户名</td>
        <td>密码</td>
        <td>生日</td>
        <td>邮箱</td>
        <td>电话号码</td>
        <td>地址</td>
        <td>编辑/删除</td>
    </tr>
    <%
        for (User user : list) {
    %>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getPassword()%>
        </td>
        <td><%=user.getBirthday()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getPhone_no()%>
        </td>
        <td><%=user.getAddress()%>
        </td>
        <td><a href="${pageContext.request.contextPath}/modify.udo?id=<%=user.getId()%>">编辑</a>
            <a href="${pageContext.request.contextPath}/delete.udo?id=<%=user.getId()%>" onclick="function deleteConfirm() {
                return confirm('确定要删除吗？');
            }
            return deleteConfirm();">删除</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<p style="margin-left: 100px;font:bold 20px 楷体;color: brown;"> 没有匹配的用户，重新输入关键字再进行查找试试吧</p>
<%
        }
    }
%>
</body>
</html>
