<%-- 
    Document   : activate
    Created on : 24-dic-2015, 19:09:09
    Author     : mayteEcheverry
--%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"  %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>activation</title>
        <link rel="stylesheet" type="text/css" href="css/stylo.css"/>
    </head>
    <body>
        <h1><bean:message key="title.activate"/> </h1>
        <br/>
        <c:url var="urlUsuarios2" value="/listUsuarios2.do"/>
        <a href="${urlUsuarios2}"><h1><bean:message key="list.activate1"/></h1></a> 
        <br/>
        <c:url var="urlUsuarios" value="/listUsuarios.do"/>
        <a href="${urlUsuarios}"><h1><bean:message key="list.activate2"/></h1></a>
    </body>
</html>
