<%@ page import="com.example.domain.Contact" %>
<%@ page import="java.util.Collection" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
    <head>
        <title>Contacts</title>
        <style>
            th {
                background-color: darkgray
            }

            section {
                border: 1px solid;
                width: 600px;
            }

            header, .row {
                display: flex; /* aligns all child elements (flex items) in a row */
                padding: 2px;
            }

            header {
                font-weight: bold;
                background-color: darkgray
            }

            .col {
                flex: 1; /* distributes space on the line equally among items */
            }
        </style>
    </head>

    <body>
        <p>All registered contacts:</p>
        <h3>JSP style</h3>
        <section>
            <header>
                <div class="col">First&nbsp;name</div>
                <div class="col">Last&nbsp;name</div>
                <div class="col">Email</div>
            </header>
            <%
                Collection<Contact> contacts = (Collection<Contact>) request.getAttribute("contacts");
                for (Contact c : contacts) {
            %>
            <div class="row">
                <div class="col"><%=c.getFirstname()%>
                </div>
                <div class="col"><%=c.getLastname()%>
                </div>
                <div class="col"><%=c.getEmail()%>
                </div>
            </div>
            <%}%>
        </section>

        <h3>JSTL style</h3>
        <section>
            <header>
                <div class="col">First&nbsp;name</div>
                <div class="col">Last&nbsp;name</div>
                <div class="col">Email</div>
            </header>
            <c:forEach var="c" items="${contacts}">
                <div class="row">
                    <div class="col"><c:out value="${c.firstname}" escapeXml="true"/></div>
                    <div class="col"><c:out value="${c.lastname}" escapeXml="true"/></div>
                    <div class="col"><c:out value="${c.email}" escapeXml="true"/></div>
                </div>
            </c:forEach>
        </section>

        <p><a href="index.jsp">Home</a></p>
    </body>
</html>



