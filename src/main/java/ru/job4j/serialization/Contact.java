package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class Contact implements Serializable {
    private static final long  serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return zipCode == contact.zipCode && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 123-45-67");
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream outputStream = new FileOutputStream(tempFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(contact);
        }

        try (FileInputStream inputStream = new FileInputStream(tempFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            final Contact contactFromFile = (Contact) objectInputStream.readObject();
            if (contact.equals(contactFromFile)) {
                System.out.println("Объекты равны");
            }
        }
    }
}