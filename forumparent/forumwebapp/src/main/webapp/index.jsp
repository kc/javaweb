<%
    Object email = session.getAttribute("email");
    String emailString = email != null ? (String) email : null;
%>

<html>
<body>
<h2>Hello World!!!</h2>
<p><% if (emailString != null) { %><%= "Welkom " + emailString + "!" %> <%}%></p>
<a href="admin">Admin</a><BR>
<a href="my">MyServlet</a><BR>
<a href="registrationform.html">Register</a><BR>
<a href="registrationform_styled.html">Register in style</a><BR>
<a href="contacts">Get all contacts</a><BR>
</body>
</html>
