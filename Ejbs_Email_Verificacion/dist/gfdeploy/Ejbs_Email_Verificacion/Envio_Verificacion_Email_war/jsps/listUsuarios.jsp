<%-- 
    Document   : listUsuarios
    Created on : 24-dic-2015, 20:05:57
    Author     : mayteEcheverry
--%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"  %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>lista_usuarios</title>
        <link rel="stylesheet" type="text/css" href="css/stylo.css"/>
        </head>
    <body>
        <h1> <bean:message key="title.list"/> </h1>
        <p><a href='javascript:history.go(-1)'>Volver</a></p>
        <table >
            <thead>
                <tr>
                    <th> <bean:message key="header.user"/> </th>
                    <th> <bean:message key="header.password"/> </th>
                    <th> <bean:message key="header.email"/> </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${usuarios}">
                    <tr>
                        <td> ${user.username}</td>
                        <td> ${user.password} </td>
                        <td> ${user.email} </td>                        
                    </tr> 
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
