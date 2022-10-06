package com.example.dao;

import com.example.domain.Contact;

import java.util.Collection;
import java.util.HashMap;

public enum ContactDao {
    INSTANCE;

    private final HashMap<String, Contact> contacts = new HashMap<>();

    public void add(Contact contact) { contacts.put(contact.getEmail(), contact); }

    public Contact getContactByEmail(String email) { return contacts.get(email); }

    public Collection<Contact> getAllContacts() { return contacts.values(); }
}
