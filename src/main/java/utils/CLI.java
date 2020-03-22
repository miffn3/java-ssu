package utils;

import bank.connection.DBConnection;
import tasks.model.PhoneBook;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {

    private  static boolean setUpIsDone = false;

    public void greeting() {
        System.out.println("Hello.");
        chooseVar();
    }

    private void chooseVar() {
        int k = getK("main");

        if (k == 1) {
            contactBookVars();
            chooseVar();
        }

        if (k == 2) {
            JDBCTaskVars();
            setUp();
            chooseVar();
        }

        if (k == 3) {
            return;
        }
    }

    private void contactBookVars() {
        int k = getK("contactsBook");

        if (k == 1) {
            addContact();
            contactBookVars();
        }

        if (k == 2) {
            deleteContact();
            contactBookVars();
        }

        if (k == 3) {
            updateContact();
            contactBookVars();
        }

        if (k == 4) {
            showAllContacts();
            contactBookVars();
        }

        if (k == 5) {
            return;
        }
    }

    private void JDBCTaskVars() {
        int k = getK("JDBCTaskVars");

        if (k == 1) {
            createUser();
            JDBCTaskVars();
        }

        if (k == 2) {
            authorization();
            JDBCTaskVars();
        }

        if (k == 3) {
            createAccount();
           JDBCTaskVars();
        }

        if (k == 4) {
            addMoney();
            JDBCTaskVars();
        }

        if (k == 5) {
            transferMoney();
            JDBCTaskVars();
        }

        if (k == 6) {
            return;
        }
    }

    private int getK(String usage) {
        System.out.println("\nChoose :");
        int to = 3;
        if (usage.equals("main")) {
            menu();
        } else if (usage.equals("contactsBook")) {
            to = 5;
            contactsMenu();
        } else if (usage.equals("JDBCTaskVars")) {
            to = 6;
            JDBCTaskMenu();
        } else if (usage.equals("updateContact")){
            updateContactMenu();
        }

        int k = 0;
        String tmp = readLine();

        if (StringUtils.isNumeric(tmp)) {
            k = Integer.parseInt(tmp);
        }


        while (k == 0 || k > to) {
            System.out.println("Incorrect, choose:");
            if (usage.equals("main")) {
                menu();
            } else if (usage.equals("contactsBook")) {
                contactsMenu();
            } else if (usage.equals("JDBCTaskVars")) {
                JDBCTaskMenu();
            } else if (usage.equals("updateContact")) {
                updateContactMenu();
            }


            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                tmp = reader.readLine();

                if (StringUtils.isNumeric(tmp)) {
                    k = Integer.parseInt(tmp);
                }
            }
            catch (IOException io) {
                io.printStackTrace();
            }
        }
        return k;
    }

    private void createUser() {

    }

    private void createAccount() {

    }

    private void authorization() {

    }

    private void addMoney() {}

    private void transferMoney() {}

    private void addContact() {
        System.out.println("Enter phone number: ");
        String phoneNumber = readLine();
        System.out.println("Enter first name: ");
        String firstName = readLine();
        System.out.println("Enter last name: ");
        String lastName = readLine();
        PhoneBook.getInstance().addContact(phoneNumber, firstName, lastName);
    }

    private void deleteContact() {
        System.out.println("Enter first name or last name or phone number of contact that you want to delete: ");
        String information = readLine();
        PhoneBook.getInstance().removeContact(information);
    }

    private void updateContact() {
        System.out.println("Enter phone number of contact that you want to update: ");
        String phoneNumber = readLine();
        int k = getK("updateContact");
        if (k == 1) {
            System.out.println("Enter new first name: ");
            String newFirstName = readLine();
            PhoneBook.getInstance().updateFirstName(phoneNumber, newFirstName);
        }
        if (k == 2) {
            System.out.println("Enter new last name: ");
            String newLastName = readLine();
            PhoneBook.getInstance().updateLastName(phoneNumber, newLastName);
        }
        if (k == 3) {
            System.out.println("Enter new phone number: ");
            String newPhoneNumber = readLine();
            PhoneBook.getInstance().updatePhoneNumber(phoneNumber, newPhoneNumber);
        }
    }

    private void showAllContacts() {
        PhoneBook.getInstance().showAllContacts();
    }

    private void menu() {
        System.out.println("1 : Contacts book");
        System.out.println("2 : JDBC task");
        System.out.println("3 : exit");
    }

    private void contactsMenu() {
        System.out.println("1 : Add contact");
        System.out.println("2 : Delete contact");
        System.out.println("3 : Update contact");
        System.out.println("4 : Show all contacts");
        System.out.println("5 : exit");
    }

    private void updateContactMenu() {
        System.out.println("1 : First name");
        System.out.println("2 : Last name");
        System.out.println("3 : Phone number");
    }

    private void JDBCTaskMenu() {
        System.out.println("1 : Create user");
        System.out.println("2 : Authorization");
        System.out.println("3 : Create account");
        System.out.println("4 : Add money");
        System.out.println("5 : Transfer money");
        System.out.println("6 : exit");
    }

    private String readLine() {
        String tmp = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            tmp = reader.readLine();
        }
        catch (IOException io) {
            io.printStackTrace();
        }
        return tmp;
    }

    private static void setUp() {
        if (!setUpIsDone) {
            boolean createConnection = DBConnection.createConnection();
            if(!createConnection) {
                throw new RuntimeException("Can't create connection, stop");
            }
            setUpIsDone = true;
            DBConnection.createTables();
        }
    }
}
