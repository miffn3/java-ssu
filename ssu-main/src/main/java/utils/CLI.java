package utils;

import bank.connection.DBConnection;
import bank.entity.Account;
import bank.repository.impl.AccountRepositoryImpl;
import bank.repository.impl.OperationRepositoryImpl;
import bank.repository.impl.UserRepositoryImpl;
import bank.service.iface.AccountService;
import bank.service.iface.OperationService;
import bank.service.iface.UserService;
import bank.service.impl.AccountServiceImpl;
import bank.service.impl.OperationServiceImpl;
import bank.service.impl.UserServiceImpl;
import tasks.model.PhoneBook;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

public class CLI {

    private  static boolean setUpIsDone = false;
    private OperationService operationService;
    private AccountService accountService;
    private UserService userService;
    private String loggedInUserLogin;

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
            setUp();
            JDBCTaskVars();
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
            showHistory();
            JDBCTaskVars();
        }

        if (k == 7) {
            loggedInUserLogin = null;
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
            to = 7;
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
        System.out.println("Enter login:");
        String login = readLine();
        if(userService.existUserByLogin(login)) {
            System.out.println("User " + login + " is already exist." );
            return;
        }
        System.out.println("Enter password:");
        String password = readLine();
        System.out.println("Enter phone:");
        String phone = readLine();
        System.out.println("Enter address:");
        String address = readLine();
        loggedInUserLogin = userService.createUser(login, password, phone, address);
        System.out.println("User " + loggedInUserLogin + " successfully created and logged in.");
    }

    private void createAccount() {
        if(loggedInUserLogin != null) {
            System.out.println("Enter currency (RUB,EUR,USD): ");
            String accCode = readLine();
            if (!accCode.equals("RUB")  && !accCode.equals("EUR") && !accCode.equals("USD")){
                System.out.println("Wrong currency. Try again.");
                return;
            }
            String id = accountService.createAccount(loggedInUserLogin, accCode);
            if (id == null)
            {
                System.out.println("Account haven't been created, please try again.");
            }
            else
            {
                System.out.println("Bank account with ID " + id + " with " + accCode + " currency successfully created.");
            }
        } else {
            System.out.println("Log in first!");
        }
    }

    private void authorization() {
        if (loggedInUserLogin != null) {
            System.out.println("User " + loggedInUserLogin + " is already authorized, please exit from account first " +
                    "to change user profile");
            return;
        }

        System.out.println("Enter login:");
        String login = readLine();
        System.out.println("Enter password:");
        String password = readLine();
        if(!userService.existUserByLogin(login)) {
            System.out.println("User " + login + " doesn't exist." );
            return;
        }
        loggedInUserLogin = userService.authorization(login, password);
        System.out.println("User " + loggedInUserLogin + " successfully logged in.");
    }

    private void addMoney() {
        if(loggedInUserLogin != null) {
            System.out.println("Which account do you want to replenish? Enter number of the account.");
            Account account = chooseAccountFromList();
            if (account == null)
                return;

            System.out.println("Enter currency you want to add (RUB,EUR,USD):");
            String chosenCurrency = readLine();
            if (!chosenCurrency.equals("RUB")  && !chosenCurrency.equals("EUR") && !chosenCurrency.equals("USD")){
                System.out.println("Wrong currency. Try again.");
                return;
            }
            System.out.println("How much do you want to add?");
            String amountString = readLine();

            if (!StringUtils.isNumeric(amountString)) {
                System.out.println("Wrong amount, enter number, try again.");
                return;
            }

            double amount = Double.parseDouble(amountString);
            if (amount <= 0.0) {
                System.out.println("Wrong amount, enter positive number, try again.");
                return;
            }
            BigDecimal val = BigDecimal.valueOf(amount);
            BigDecimal resultAmount = accountService.increaseAmount(account,
               val, chosenCurrency);
            if (resultAmount != null) {
                System.out.println("Amount after increase is " + resultAmount + " " + account.getAccCode());
            } else {
                System.out.println("Couldn't add money to account");
            }
        } else {
            System.out.println("Log in first!");
        }
    }

    private void transferMoney() {
        if(loggedInUserLogin != null) {
            System.out.println("From which account do you want to transfer? Enter number of the account.");
            Account accountFrom = chooseAccountFromList();
            if (accountFrom == null)
                return;

            System.out.println("How much do you want to transfer?");
            String amountString = readLine();
            if (!StringUtils.isNumeric(amountString)) {
                System.out.println("Wrong amount, enter number, try again.");
                return;
            }
            double amount = Double.parseDouble(amountString);
            if(amount <= 0.0) {
                System.out.println("Wrong amount, enter positive number, try again.");
                return;
            }

            System.out.println("How do you want to transfer, choose one : \n1.By phone to another user. \n2.Between " +
                    "your accounts.");
            int var;
            String tmp = readLine();

            if (!StringUtils.isNumeric(tmp)) {
                System.out.println("Wrong variant. Try again.");
                return;
            }
            var = Integer.parseInt(tmp);

            if (var == 0 || var > 2){
                System.out.println("Wrong variant. Try again.");
                return;
            }

            if (var == 1) {
                System.out.println("Enter phone of another user: ");
                String phone = readLine();
                String res = accountService.moneyTransfer(accountFrom, phone, BigDecimal.valueOf(amount));
                if (res == null){
                    System.out.println("Couldn't transfer money to another user.");
                } else {
                    System.out.println("Successfully transferred " + res + " to user with phone: " + phone);
                }
            } else {
                System.out.println("Choose from your accounts, where you want to transfer: ");
                Account accountTo = chooseAccountFromList();
                if (accountTo == null)
                    return;
                BigDecimal resultAmount = accountService.moneyTransfer(accountFrom, accountTo, BigDecimal.valueOf(amount));
                if (resultAmount != null) {
                    System.out.println("Amount after transfer is " + resultAmount + " " + accountFrom.getAccCode());
                } else {
                    System.out.println("Couldn't transfer money to account.");
                }
            }
        } else {
            System.out.println("Log in first!");
        }
    }

    private void showHistory() {
        if(loggedInUserLogin != null) {
            System.out.println("History of which account do you want to see? Enter number of the account.");
            Account accountFrom = chooseAccountFromList();
            if (accountFrom == null)
                return;

            List<String> operations = operationService.getHistory(accountFrom.getId().toString());
            if (operations == null) {
                System.out.println("Couldn't find any operation.");
                return;
            }
            for (String operation : operations) {
                System.out.println(operation);
            }
        } else {
            System.out.println("Log in first!");
        }
    }

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
        System.out.println("6 : Show history");
        System.out.println("7 : exit");
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

    private void setUp() {
        if (!setUpIsDone) {
            boolean createConnection = DBConnection.createConnection();
            if(!createConnection) {
                throw new RuntimeException("Can't create connection, stop");
            }

            if(userService == null)
                userService = new UserServiceImpl(new UserRepositoryImpl());

            if(operationService == null)
                operationService = new OperationServiceImpl(new OperationRepositoryImpl());

            if(accountService == null)
                accountService = new AccountServiceImpl(new AccountRepositoryImpl(), userService, operationService);

            DBConnection.createTables();
            setUpIsDone = true;
        }
    }

    private Account chooseAccountFromList() {
        List<Account> accounts = accountService.listOfUserAccounts(loggedInUserLogin);
        if (accounts == null || accounts.isEmpty()) {
            System.out.println("Accounts haven't been found");
            return null;
        }
        for (int i = 0; i < accounts.size(); i++) {
            Account current = accounts.get(i);
            int index = i + 1;
            System.out.println(index + ". Amount: " + current.getAmount() + ", currency " + current.getAccCode());
        }
        int var;
        String tmp = readLine();

        if (!StringUtils.isNumeric(tmp)) {
            System.out.println("Wrong amount, enter number, try again.");
            return null;
        }
        var = Integer.parseInt(tmp);

        if (var == 0 || var > accounts.size()){
            System.out.println("Wrong account number. Try again.");
            return null;
        }
        return accounts.get(var - 1);
    }

}