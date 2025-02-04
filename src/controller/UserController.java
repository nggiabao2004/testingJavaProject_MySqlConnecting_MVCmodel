package controller;
import model.UserModel;
import view.UserView;

public class UserController {
    private UserModel model;
    private UserView view;

    public UserController(UserModel model, UserView view) {
        this.model = model;
        this.view = view;
        this.view.setOnAddUser(() -> addUser());
        model.addObserver(view);
        updateUserList();
    }

    private void addUser() {
        String name = view.getUserName();
        if (!name.isEmpty()) {
            model.addUser(name);
            updateUserList();
        }
    }

    private void updateUserList() {
        view.updateUserList(model.getUsers());
    }
}
