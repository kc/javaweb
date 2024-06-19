<%@ page import="com.example.domain.Contact" %>

<%
    Contact contact = (Contact) request.getAttribute("contact");
%>

<html>
    <body>
        <p>Thank you <%= contact.getFullname() %> for contacting us from <%=contact.getEmail()%>!</p>
        <a href="index.jsp">Home</a>
    </body>
</html>
