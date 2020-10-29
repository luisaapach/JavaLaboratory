<%--
  Created by IntelliJ IDEA.
  User: lapachitei
  Date: 10/25/2020
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="Utils.DictionaryPair"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<style><%@include file="style.css"%></style>
<body>
<h1> Dictionary now:</h1>
<table>
    <tr> <th> Word </th>
    <th> Language </th>
    <th> Description </th>
    </tr>
<%
 ArrayList<DictionaryPair> list_of_words = (ArrayList<DictionaryPair>)request.getSession().getAttribute("Controller_Dictionary");
 for (DictionaryPair el : list_of_words){
     out.println("<tr><td>"+el.getWord()+"</td><td>"+el.getLanguage()+"</td><td>"+el.getDefinition()+"</td></tr>");
 }
%>
</table>
</body>
</html>
