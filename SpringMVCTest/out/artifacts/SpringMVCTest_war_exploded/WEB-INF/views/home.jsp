<%--
  Created by IntelliJ IDEA.
  User: Siryu
  Date: 2019/5/10
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>
        Spittr
    </title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">
</head>
<body>
<h1> welcome to Spittr</h1>
<a href="<c:url value="/spittles"/> ">Spttles</a>
<a href="<c:url value="/spittles/register"/> ">Register</a>
</body>
</html>
