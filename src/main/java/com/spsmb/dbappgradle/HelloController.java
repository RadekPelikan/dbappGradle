package com.spsmb.dbappgradle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class HelloController implements Initializable {

    public TextField input;
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

    public void click(ActionEvent actionEvent) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("insert into mytable (input) values (?)");
            stmt.setString(1, input.getText());
            int i = stmt.executeUpdate();
            System.out.println(i + " records inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}