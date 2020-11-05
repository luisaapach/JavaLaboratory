<%--
  Created by IntelliJ IDEA.
  User: lapachitei
  Date: 10/25/2020
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>InputJSP</title>
</head>
<body>
<fmt:bundle basename="/messages">
<h1> <fmt:message key="title" /> </h1>
<%
    //Get the client's Locale
    Locale locale = request.getLocale();
    String language = locale.getLanguage();
    out.print("<fmt:setLocale value=\""+language+"\"/>");
%>
<form method = "POST" action = "Controller">

<label for="word"><h2> <fmt:message key="word" /> </h2></label>
    <input id="word" type="text" name="word">
<br>
    <label for="definition"><h2> <fmt:message key="definition" /> </h2></label>
    <input id="definition" type="text" name="definition">
<br>
    <label for="language"><h2> <fmt:message key="language" /> </h2></label>
    <select id="language" type="text" name="language">
    <%
        // din Get apar supported_languages
        String[] supported_languages = (String[])request.getAttribute("supported_languages");
        //String selected_language_option = supported_languages[0];
        String selected_language_option = "";
        // Iau Cookie-urile si verific daca Cookie-ul meu este setat
        Cookie[] cookies = request.getCookies();
        ArrayList<String> supported_languages_a = new ArrayList<String>( Arrays.asList( supported_languages ));
        supported_languages_a.add("");
        for (Cookie cookie : cookies){
            if(cookie.getName().equals("selected_language")){
                if(supported_languages_a.contains(cookie.getValue()))
                    selected_language_option = cookie.getValue();
                    break;
            }
        }
        // Fac Select pe elem gasit in Cookie / cu primul element din Supported Languages
        for (String el : supported_languages_a)
        {
            out.print("<option value=\""+el+"\" ");
            if(el.equals(selected_language_option)){
                out.print("selected");
            }
            out.print(">"+el+"</option>");
        }

    %>
    </select>
    <br><br>
    <img src="Captcha"><br>
    <h2><fmt:message key="captcha" /></h2>
    <h2><input type="text" name="result_captcha"></h2>
    <h2><input type="submit" value = "Add Dictionary Pair" style="font-weight: bold; font-size: 16px;"></h2>
</form>
</fmt:bundle>
</body>
</html>
