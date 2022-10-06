package com.example.servlets;

import com.example.dao.ContactDao;
import com.example.domain.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = "/api/contacts")
public class ContactsResource extends HttpServlet {

    private final ContactDao contactDao = ContactDao.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Contact> contacts = contactDao.getAllContacts();

        resp.getWriter().write(contacts.toString());
    }
}
