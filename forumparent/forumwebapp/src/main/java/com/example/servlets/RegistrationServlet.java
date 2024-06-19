package com.example.servlets;

import com.example.dao.ContactDao;
import com.example.domain.Contact;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private ContactDao contactDao = ContactDao.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");

        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(email);

        Contact contact = new Contact(firstname, lastname, email);
        contactDao.add(contact);

        req.getSession().setAttribute("email", contact.getEmail());

        req.setAttribute("contact", contact);
        req.getRequestDispatcher("/registrationresponse").forward(req, resp);

        // or
        // resp.sendRedirect("registrationresponse.html");

        // or
        // resp.sendRedirect("registrationresponse?email=" + contact.getEmail());

    }
}
