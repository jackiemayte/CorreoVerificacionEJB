<%-- 
    Document   : formInsert
    Created on : 22-dic-2015, 13:09:58
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
        <title>Insert_datos</title>
        <link rel="stylesheet" type="text/css" href="css/stylo.css"/>
    </head>
    <body>
        <div style="color:red "><html:errors/></div> 
        <c:url var="urlAction" value="/insert.do"/>
        <fieldset style="width:350px; background-color:Beige">
            <form method="POST" action="${urlAction}">
                <bean:message key="header.user"/> <input type="text" name="user" value="${old.user}"/>    
                <br/>
                <bean:message key="header.password"/> <input type="password" name="password" value="${old.password}"/> 
                <br/>
                <bean:message key="header.email"/> <input type="email" name="email" value="${old.email}" /> 
                <br/>
                <input type="submit" value="<bean:message key="label.insert"/>"/>            
            </form> 
        </fieldset>
    </body>
</html>
