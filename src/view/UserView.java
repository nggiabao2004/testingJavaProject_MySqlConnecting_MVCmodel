package view;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.User;
import model.UserObserver;

public class UserView extends JFrame implements UserObserver {
    private DefaultListModel<String> listModel;
    private JList<String> userList;
    private JTextField nameField;
    private JButton addButton;
    private Runnable onAddUser;

    public UserView() {
        setTitle("MVC + Observer + MySQL");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        add(new JScrollPane(userList), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        nameField = new JTextField(20);
        addButton = new JButton("Add User");
        panel.add(nameField);
        panel.add(addButton);
        add(panel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            if (onAddUser != null) {
                onAddUser.run();
            }
        });
    }

    public String getUserName() {
        return nameField.getText();
    }

    public void setOnAddUser(Runnable onAddUser) {
        this.onAddUser = onAddUser;
    }

    public void updateUserList(List<User> users) {
        listModel.clear();
        for (User user : users) {
            listModel.addElement(user.getName());
        }
    }

    @Override
    public void update() {
        JOptionPane.showMessageDialog(this, "User added successfully!");
    }
}
