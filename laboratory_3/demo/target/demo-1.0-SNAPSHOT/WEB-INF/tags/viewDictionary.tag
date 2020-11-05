<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="language" %>

<table>
    <tr> <th> Word </th>
        <th> Language </th>
        <th> Description </th>
        <th> Date </th>
    </tr>

    <c:forEach items="${sessionScope.Controller_Dictionary}" var="element">
        <c:if test="${language == null || element.language.equals(language)}">
        <tr>
            <td>${element.word}</td>
            <td>${element.language}</td>
            <td>${element.definition}</td>
            <td>${element.date}</td>
        </tr>
        </c:if>
    </c:forEach>
</table>