<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>
<header>
    <meta charset="UTF-8">
</header>
<body>
<h2>Hello World!</h2>
<a href="user.jsp">用户界面</a><br><br>
<a href="admin.jsp">管理员界面</a><br><br>
<a href="logoutSecurity">登出</a><br>
<security:authentication property="principal.username"></security:authentication>
<%-- ifAnyGranted="ROLE_USER" 这种表示法已经被弃用了！！！ --%>
<security:authorize access="hasRole('ROLE_USER')">
    <a href="#">用户界面的秘密通道</a><br>
</security:authorize>
<security:authorize access="hasRole('ROLE_ADMIN')">
    <a href="#">管理员界面的秘密通道</a><br>
</security:authorize>
</body>
</html>
