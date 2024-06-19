package com.example.servlets;

import com.example.dao.ContactDao;
import com.example.domain.Contact;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registrationresponse")
public class RegistrationResponseServlet extends HttpServlet {

    private ContactDao dao = ContactDao.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("req.getSession().getAttribute(\"email\")=" + req.getSession().getAttribute("email"));
        req.getRequestDispatcher("thankyou.jsp").forward(req, resp);

        /*
        Contact contact = (Contact) req.getAttribute("contact");
        req.setAttribute("contact", contact);
        String fn = contact.getFullname();

        PrintWriter pw = resp.getWriter();

        String html = """
                        <html>
                            <body>
                                <p>Thank you $fn for contacting us!</p>
                                <a href="index.jsp">Home</a>
                             </body>
                          </html>
                      """
                .replace("$fn", fn);

        pw.println(html);*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        Contact contact = dao.getContactByEmail(email);

        // String fn = contact.getFullname();

        /*PrintWriter pw = resp.getWriter();

        String html = """
                        <html>
                            <body>
                                <p>Thank you $fn for contacting us from $email!</p>
                                <a href="index.jsp">Home</a>
                             </body>
                          </html>
                      """
                .replace("$fn", fn)
                .replace("$email", email);

        pw.println(html);*/

        req.setAttribute("contact", contact);
        req.getRequestDispatcher("thankyou.jsp").forward(req, resp);
    }
}
