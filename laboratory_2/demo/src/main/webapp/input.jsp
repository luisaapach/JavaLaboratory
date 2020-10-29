<%--
  Created by IntelliJ IDEA.
  User: lapachitei
  Date: 10/25/2020
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>InputJSP</title>
</head>
<body>
<h1> Give me a Pair for the Dictionary</h1>
<form method = "POST" action = "Controller">
    <label for="word"><h2> Word </h2></label>
    <input id="word" type="text" name="word">
<br>
    <label for="definition"><h2> Definition </h2></label>
    <input id="definition" type="text" name="definition">
<br>
    <label for="language"><h2> Language </h2></label>
    <select id="language" type="text" name="language">
    <%
        // din Get apar supported_languages
        String[] supported_languages = (String[])request.getAttribute("supported_languages");
        String selected_language_option = supported_languages[0];

        // Iau Cookie-urile si verific daca Cookie-ul meu este setat
        Cookie[] cookies = request.getCookies();
        List supported_languages_a = Arrays.asList(supported_languages);
        for (Cookie cookie : cookies){
            if(cookie.getName().equals("selected_language")){
                if(supported_languages_a.contains(cookie.getValue()))
                    selected_language_option = cookie.getValue();
                    break;
            }
        }
        // Fac Select pe elem gasit in Cookie / cu primul element din Supported Languages
        for (String el : supported_languages)
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
    <h2>Insert the result for the above expression:</h2>
    <h2><input type="text" name="result_captcha"></h2>
    <h2><input type="submit" value = "Add Dictionary Pair" style="font-weight: bold; font-size: 16px;"></h2>
</form>
</body>
</html>
