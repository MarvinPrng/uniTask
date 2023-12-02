package account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AccountService {
    ArrayList<User> users = new ArrayList<User>();

    public AccountService() {
        loadAllUsers();
    }

    public class User {
        /* private */ String userId;
        /* private */ String userName;
        /* private */ String password;

        protected User(String userName, String password) {
            this.userName = userName;
            this.password = password;
            generateId();
        }

        private void setUserId(String userId) {
            this.userId = userId;
        }

        private void generateId() {
            Random random = new Random();
            String newUserId = "" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
                    + random.nextInt(10);
            while (newUserId == "00000") {
                newUserId = "" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
                        + random.nextInt(10);
            }
            this.userId = newUserId;
        }

        public String getId() {
            return userId;
        }

    }

    public String verifyLogin(String username, String password) {
        for (User user : users) {
            if (user.userName.equals(username) && user.password.equals(password)) {
                return user.userId;
            }
        }

        return "00000";
    }

    public void createNewUser(String userName, String userPassword) {
        addUser(userName, userPassword);
        loadAllUsers();
        for (User user : users) {
            System.out.println(user.userName);
            System.out.println(user.password);
            System.out.println(user.userId);
        }
    }

    private void loadAllUsers() {
        users.clear();
        ArrayList<String[]> allUsers = loadUsersData();
        for (String[] userDataLine : allUsers) {

            System.out.println(Arrays.toString(userDataLine));
            for (String userLine : userDataLine) {
                try {
                String[] userData = userLine.split("¡");
                User user = new User(userData[0], userData[1]);
                user.setUserId(userData[2]);
                users.add(user);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Users could not be loaded correctly");
                }
            }
        }
    }

    private ArrayList<String[]> loadUsersData() {
        ArrayList<String[]> userData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("swt-group-2B/klipsias/src/account/database/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split("\\|");
                userData.add(part);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return userData;
    }

    private void addUser (String userName, String password) {
        User newUser = new User(userName, password);
        String userToDatabank =  "|" + userName + "¡" + password + "¡" + newUser.getId();
        try {
            FileWriter fileWriter = new FileWriter("swt-group-2B/klipsias/src/account/database/users.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userToDatabank);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
