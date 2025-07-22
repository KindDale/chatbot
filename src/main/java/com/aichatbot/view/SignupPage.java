package com.aichatbot.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import com.aichatbot.controller.UserController;

public class SignupPage {

    private UserController c2w_ai_userController = new UserController();

    public Parent createSignupScene(Runnable c2w_ai_backHandler) {

        ImageView c2w_ai_logo = new ImageView("image/logo.png");
        c2w_ai_logo.setFitWidth(120);
        c2w_ai_logo.setPreserveRatio(true);

        Label c2w_ai_title = new Label("Sign up");
        c2w_ai_title.setStyle(
                "-fx-font-size: 25; -fx-font-weight: bold; -fx-text-fill: #FFFFFF;"
        );

        VBox c2w_ai_header = new VBox(20, c2w_ai_logo, c2w_ai_title);
        c2w_ai_header.setAlignment(Pos.CENTER);

        Label c2w_ai_userLabel = new Label("Username:");
        c2w_ai_userLabel.setStyle("-fx-text-fill: white;");

        TextField c2w_ai_userTextField = new TextField();
        c2w_ai_userTextField.setPromptText("Enter Username");
        c2w_ai_userTextField.setFocusTraversable(false);
        c2w_ai_userTextField.setStyle(
                "-fx-max-width: 270; -fx-min-height: 30; -fx-background-radius: 15;"
        );

        Label c2w_ai_passLabel = new Label("Password:");
        c2w_ai_passLabel.setStyle("-fx-text-fill: white;");

        PasswordField c2w_ai_passField = new PasswordField();
        c2w_ai_passField.setPromptText("Enter Password");
        c2w_ai_passField.setFocusTraversable(false);
        c2w_ai_passField.setStyle(
                "-fx-pref-width: 270; -fx-min-height: 30; -fx-background-radius: 15;"
        );

        Button c2w_ai_signupButton = new Button("Signup");
        c2w_ai_signupButton.setStyle(
                "-fx-pref-width: 70; -fx-min-height: 30; -fx-background-radius: 15; -fx-background-color: #2196F3; -fx-text-fill: #FFFFFF;"
        );

        Label c2w_ai_loginButton = new Label("Login");
        c2w_ai_loginButton.setStyle(
                "-fx-background-radius: 15; -fx-text-fill: white; -fx-underline: true;"
        );

        Label c2w_ai_output = new Label();
        c2w_ai_output.setStyle("-fx-text-fill: white;");

        VBox c2w_ai_fieldBox1 = new VBox(10, c2w_ai_userLabel, c2w_ai_userTextField);
        VBox c2w_ai_fieldBox2 = new VBox(10, c2w_ai_passLabel, c2w_ai_passField);

        c2w_ai_fieldBox1.setMaxSize(300, 30);
        c2w_ai_fieldBox2.setMaxSize(300, 30);

        c2w_ai_signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent c2w_ai_event) {
                if (!c2w_ai_userTextField.getText().isEmpty() && !c2w_ai_passField.getText().isEmpty()) {
                    boolean success = c2w_ai_userController.handleSignup(
                            c2w_ai_userTextField.getText(),
                            c2w_ai_passField.getText()
                    );
                    if (success) {
                        LoginPage c2w_ai_loginPage = new LoginPage();
                        c2w_ai_loginPage.getLoginScene();
                    } else {
                        c2w_ai_output.setText("User not Registered");
                    }
                } else {
                    c2w_ai_output.setText("Please Enter Username and Password");
                }
            }
        });

        c2w_ai_loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent c2w_ai_event) {
                c2w_ai_backHandler.run();
            }
        });

        VBox c2w_ai_loginBox = new VBox(20,
                c2w_ai_header,
                c2w_ai_fieldBox1,
                c2w_ai_fieldBox2,
                c2w_ai_signupButton,
                c2w_ai_loginButton,
                c2w_ai_output
        );

        c2w_ai_loginBox.setStyle(
                "-fx-alignment: CENTER; -fx-padding: 30; -fx-background-color: rgba(0,0,0,0.8);"
        );
        c2w_ai_loginBox.setAlignment(Pos.CENTER);
        c2w_ai_loginBox.setMaxSize(300, 650);

        Rectangle clip = new Rectangle(300, 650);
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        c2w_ai_loginBox.setClip(clip);

        return c2w_ai_loginBox;
    }
}
