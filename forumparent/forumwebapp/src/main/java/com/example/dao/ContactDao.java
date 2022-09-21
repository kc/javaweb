package com.example.dao;

import com.example.domain.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum ContactDao {
    INSTANCE;

    private final HashMap<String, Contact> contacts = new HashMap<>();

    public void add(Contact contact) { contacts.put(contact.getEmail(), contact); }

    public Contact getContactByEmail(String email) { return contacts.get(email); }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }
}
