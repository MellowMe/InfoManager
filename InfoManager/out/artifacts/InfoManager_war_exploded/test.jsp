<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.yufan.infomanager.dao.UserDao,cn.yufan.infomanager.dao.UserDaoImp,cn.yufan.infomanager.model.User" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>TestCases</title>
</head>
<body>
<%
    UserDao userDao = new UserDaoImp();
    List<User> list = userDao.fuzzyQueryByName("%daba%");
    out.println(list.get(0));
%>

</body>
</html>
