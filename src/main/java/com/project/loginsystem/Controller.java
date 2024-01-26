package com.project.loginsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Controller {
    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    public void loginButtonOnAction(ActionEvent e){

        if (!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()){
            //loginMessageLabel.setText("You tried to login!");
            validateLogin();

        } else {
            loginMessageLabel.setText("Please enter username and password!");
        }
    }

    public void validateLogin(){
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectionDB = connection.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_accounts WHERE Username = '"+ usernameTextField.getText() +"' AND Password = '"+ passwordField.getText() +"';";

        try{
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Welcome!");
                } else {
                    loginMessageLabel.setText("Invalid Login. Please try again.");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }





}