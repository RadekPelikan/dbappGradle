package com.spsmb.dbappgradle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class HelloController implements Initializable {

    public TextField insertField;
    public Button insertButton;
    public Label selectLabel;
    public Button selectButton;
    public ListView selectList;
    public TextField idField;
    public TextField nameField;
    public Button deleteButton;
    public Button updateButton;
    Connection connection;
    String URL = "jdbc:mysql://localhost:3306/dbaappgradle";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         try {
             connection = DriverManager.getConnection(URL, "root", "");
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }

    public void clickSelect(ActionEvent actionEvent) {
        PreparedStatement stmt = null;
        try {
            String id = idField.getText();
            if (id.equals("")) {
                stmt = connection.prepareStatement("SELECT (name) FROM names ORDER BY id DESC LIMIT 1;");
            } else {
                stmt = connection.prepareStatement("SELECT (name) FROM names WHERE id = (?);");
                stmt.setString(1, id);
            }
            ResultSet result = stmt.executeQuery();
            result.next();
            String s = result.getString("name");
            selectLabel.setText(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clickSelectAll(ActionEvent actionEvent) {
        selectList.getItems().clear();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT (name) FROM names");
            ResultSet result = stmt.executeQuery();
            String s;
            while (result.next()) {

                s = result.getString("name");
                selectList.getItems().add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clickInsert(ActionEvent actionEvent) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("INSERT INTO names (name) VALUES (?);");
            stmt.setString(1, insertField.getText());
            int i = stmt.executeUpdate();
            System.out.println(i + " records inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clickDelete(ActionEvent actionEvent) {
        PreparedStatement stmt = null;
        try {
            String id = idField.getText();
            if (id.equals("")) {
                stmt = connection.prepareStatement("DELETE FROM names ORDER BY id DESC LIMIT 1;");
            } else {
                stmt = connection.prepareStatement("DELETE FROM names WHERE id = (?);");
                stmt.setString(1, id);
            }
            int i = stmt.executeUpdate();
            System.out.println(i + " records deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clickUpdate(ActionEvent actionEvent) {
        PreparedStatement stmt = null;
        try {
            String id = idField.getText();
            if (id.equals("")) {
                stmt = connection.prepareStatement("UPDATE names SET name = (?) ORDER BY id DESC LIMIT 1;");
                stmt.setString(1, nameField.getText());
            } else {
                stmt = connection.prepareStatement("UPDATE names SET name = (?) WHERE id = (?)");
                stmt.setString(1, nameField.getText());
                stmt.setString(2, id);
            }
            int i = stmt.executeUpdate();
            System.out.println(i + " records updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void enter(KeyEvent keyEvent) {

    }
}