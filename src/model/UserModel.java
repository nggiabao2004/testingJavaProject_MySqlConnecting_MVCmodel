package model;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private List<User> users = new ArrayList<>();
    private List<UserObserver> observers = new ArrayList<>();

    public void addUser(String name) {
        try (var conn = Database.getConnection()) {
            var stmt = conn.prepareStatement("INSERT INTO users (name) VALUES (?)");
            stmt.setString(1, name);
            stmt.executeUpdate();
            notifyObservers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        List<User> usersList = new ArrayList<>();
        try (var conn = Database.getConnection()) {
            var stmt = conn.prepareStatement("SELECT * FROM users");
            var rs = stmt.executeQuery();
            while (rs.next()) {
                usersList.add(new User(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void addObserver(UserObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (UserObserver observer : observers) {
            observer.update();
        }
    }
}
