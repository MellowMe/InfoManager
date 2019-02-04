<%@page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p></p>
<script>
    document.getElementsByTagName("p")[0].innerHTML = document.cookie;
    var date = new Date();
    date.setDate(date.getDate()+30);
    var time1 = date.toUTCString();
    document.cookie="psw=131455;expires=" + time1;
    document.cookie="city=南京;expires="+time1+";path=/"
</script>
</body>

</html>
