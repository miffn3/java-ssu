package Model;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<Contact> contacts = new ArrayList<>();
    private static PhoneBook phoneBook;

    public static PhoneBook getInstance() {
        if (phoneBook == null) {
            phoneBook = new PhoneBook();
        }
        return phoneBook;
    }

    public String checkPhone(String phone) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phone)) {
                return contacts.get(i).toString();
            }
        }
        return null;
    }

    public void addContact(String phoneNumber, String firstName, String lastName) {
        Contact newContact = new Contact(firstName, lastName, phoneNumber);
        this.contacts.add(newContact);
    }

    public void removeContact(String information) {
        for (Contact current : contacts) {
            if (current.getFirstName().equals(information)) {
                contacts.remove(current);
                break;
            }
            if (current.getLastName().equals(information)) {
                contacts.remove(current);
                break;
            }
            if (current.getPhoneNumber().equals(information)) {
                contacts.remove(current);
                break;
            }
        }
    }

    public void updateFirstName(String phoneNumber, String newInformation) {
        for (Contact current:contacts) {
            if (current.getPhoneNumber().equals(phoneNumber)) {
                current.setFirstName(newInformation);
                break;
            }
        }
    }

    public void updateLastName(String phoneNumber, String newInformation) {
        for (Contact current:contacts) {
            if (current.getPhoneNumber().equals(phoneNumber)) {
               current.setLastName(newInformation);
               break;
            }
        }
    }

    public void updatePhoneNumber(String phoneNumber, String newInformation) {
        for (Contact current:contacts) {
            if (current.getPhoneNumber().equals(phoneNumber)) {
                current.setPhoneNumber(newInformation);
            }
        }
    }

    public void showAllContacts() {
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println(contacts.get(i).toString());
        }
    }
}
