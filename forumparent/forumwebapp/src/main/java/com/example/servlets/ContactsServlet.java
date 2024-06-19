package com.example.servlets;

import com.example.dao.ContactDao;
import com.example.domain.Contact;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = "/contacts")
public class ContactsServlet extends HttpServlet {

    private ContactDao contactDao = ContactDao.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Contact> contacts = contactDao.getAllContacts();
        contacts.forEach(System.out::println);

        req.setAttribute("contacts", contacts);
        req.getRequestDispatcher("contacts.jsp").forward(req, resp);
    }
}
