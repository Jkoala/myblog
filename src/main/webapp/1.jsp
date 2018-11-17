<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<title>title</title>
</head>
<body>


<shiro:hasPermission name="user:del">
	欢迎user:del权限的用户 <shiro:principal/>
</shiro:hasPermission>


<shiro:lacksPermission name="user:up">
	没有up 权限的用户 <shiro:principal/>
</shiro:lacksPermission>


</body>
</html>