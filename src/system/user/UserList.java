package system.user;

import system.exception.UserException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserList {
    private List<User> userList;

    public UserList() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void loadUserList(String csvFile) throws UserException {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/customer.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length != 4) {
                    throw new UserException("Invalid CSV format");
                }
                String email = values[0];
                String password = values[1];
                UserPlan.PlanType planType = UserPlan.PlanType.valueOf(values[2].toUpperCase());
                boolean isActive = Boolean.parseBoolean(values[3]);

                UserPlan plan = new UserPlan(planType, isActive);
                User user = new User(email, password, plan);
                userList.add(user);
            }
        } catch (IOException e) {
            throw new UserException("Error reading user list: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new UserException("Invalid plan type in CSV file.");
        }
    }

    public void saveUserList(String csvFile) throws UserException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./src/customer.csv"))) {
            for (User user : userList) {
                bw.write(user.getEmail() + "," + user.getPassword() + "," +
                        user.getPlan().getPlanType() + "," + user.getPlan().isActive());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new UserException("Error writing user list: " + e.getMessage());
        }
    }

    public void updateUser(User user) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(user.getEmail())) {
                userList.set(i, user);
                break;
            }
        }
    }

    @Override
    public String toString() {
        if (userList.isEmpty()) {
            throw new RuntimeException("User list is empty");
        }

        StringBuilder sb = new StringBuilder();
        for (User user : userList) {
            sb.append(user).append("\n");
        }
        return sb.toString();
    }

    public List<User> getUserList() {
        return userList;
    }
}
