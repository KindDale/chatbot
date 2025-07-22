package com.aichatbot.view;

import com.aichatbot.controller.UserController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginPage extends Application {

    private Stage c2w_ai_primaryStage;
    private Scene c2w_ai_loginScene, c2w_ai_homeScene;
    private UserController c2w_ai_userController = new UserController();
    public static String c2w_ai_key;

    public void getLoginPage(Stage c2w_ai_primaryStage) {
        this.c2w_ai_primaryStage = c2w_ai_primaryStage;
        initLoginScene();
    }

    private void initLoginScene() {
        // Background image
        Image bgImage = new Image("image/bg.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(300, 650, false, false, false, false)
        );
        Background background = new Background(backgroundImage);

        ImageView c2w_ai_logo = new ImageView("image/logo.png");
        c2w_ai_logo.setFitWidth(120);
        c2w_ai_logo.setPreserveRatio(true);

        Label c2w_ai_title = new Label("Login");
        c2w_ai_title.setStyle("-fx-font-size:25; -fx-font-weight: bold; -fx-text-fill:#FFFFFF");

        VBox c2w_ai_header = new VBox(20, c2w_ai_logo, c2w_ai_title);
        c2w_ai_header.setAlignment(Pos.CENTER);

        Label c2w_ai_userLabel = new Label("Username:");
        c2w_ai_userLabel.setStyle("-fx-text-fill: white;");
        TextField c2w_ai_userTextField = new TextField();
        c2w_ai_userTextField.setPromptText("Enter Username");
        c2w_ai_userTextField.setStyle("-fx-max-width: 270; -fx-min-height: 30; -fx-background-radius: 15;");
        c2w_ai_userTextField.setFocusTraversable(false);

        Label c2w_ai_passLabel = new Label("Password:");
        c2w_ai_passLabel.setStyle("-fx-text-fill: white;");
        PasswordField c2w_ai_passField = new PasswordField();
        c2w_ai_passField.setPromptText("Enter Password");
        c2w_ai_passField.setStyle("-fx-pref-width: 270; -fx-min-height: 30; -fx-background-radius: 15;");
        c2w_ai_passField.setFocusTraversable(false);

        Label c2w_ai_output = new Label();
        c2w_ai_output.setStyle("-fx-text-fill: white;");

        Button c2w_ai_loginButton = new Button("Login");
        c2w_ai_loginButton.setStyle("-fx-pref-width: 70; -fx-min-height: 30; -fx-background-radius: 15; -fx-background-color: #2196F3; -fx-text-fill: #FFFFFF");

        Label c2w_ai_signupButton = new Label("Signup");
        c2w_ai_signupButton.setStyle("-fx-text-fill: white;");

        c2w_ai_loginButton.setOnAction(e -> {
            if (!c2w_ai_userTextField.getText().isEmpty() && !c2w_ai_passField.getText().isEmpty()) {
                if (c2w_ai_userController.authenticateUser(c2w_ai_userTextField.getText(), c2w_ai_passField.getText())) {
                    initUserScene(c2w_ai_userTextField.getText());
                    c2w_ai_primaryStage.setScene(c2w_ai_homeScene);
                    c2w_ai_userTextField.clear();
                    c2w_ai_passField.clear();
                } else {
                    c2w_ai_output.setText("Invalid Username or Password");
                }
            } else {
                c2w_ai_output.setText("Please Enter Username and Password");
            }
        });

        c2w_ai_signupButton.setOnMouseClicked(event -> {
            showSignupScene();
            c2w_ai_userTextField.clear();
            c2w_ai_passField.clear();
        });

        VBox c2w_ai_fieldBox1 = new VBox(10, c2w_ai_userLabel, c2w_ai_userTextField);
        VBox c2w_ai_fieldBox2 = new VBox(10, c2w_ai_passLabel, c2w_ai_passField);

        VBox c2w_ai_loginBox = new VBox(20, c2w_ai_header, c2w_ai_fieldBox1, c2w_ai_fieldBox2,
                c2w_ai_loginButton, c2w_ai_signupButton, c2w_ai_output);
        c2w_ai_loginBox.setAlignment(Pos.CENTER);
        c2w_ai_loginBox.setPadding(new Insets(30));
        c2w_ai_loginBox.setMaxWidth(300);
        c2w_ai_loginBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 20;");

        Rectangle clip = new Rectangle(300, 650);
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        c2w_ai_loginBox.setClip(clip);

        StackPane root = new StackPane();
        root.setBackground(background);
        root.getChildren().add(c2w_ai_loginBox);

        c2w_ai_loginScene = new Scene(root, 300, 650);
        c2w_ai_loginScene.setFill(Color.TRANSPARENT);
    }

    private void initUserScene(String userName) {
        HomePage c2w_ai_homePage = new HomePage();
        c2w_ai_homePage.setStage(c2w_ai_primaryStage);
        c2w_ai_homePage.setUserName(userName);
        c2w_ai_homeScene = new Scene(c2w_ai_homePage.getView(this::handleLogout), 300, 650);
        c2w_ai_homeScene.setFill(Color.TRANSPARENT);
        c2w_ai_homePage.setScene(c2w_ai_homeScene);
    }

    public Scene getLoginScene() {
        return c2w_ai_loginScene;
    }

    public void showLoginScene() {
        c2w_ai_primaryStage.setScene(c2w_ai_loginScene);
        c2w_ai_primaryStage.show();
    }

    private void showSignupScene() {
        SignupPage signupPage = new SignupPage();
        Scene signupScene = new Scene(signupPage.createSignupScene(this::handleBack), 300, 650);
        signupScene.setFill(Color.TRANSPARENT);
        c2w_ai_primaryStage.setScene(signupScene);
    }

    private void handleLogout() {
        c2w_ai_primaryStage.setScene(c2w_ai_loginScene);
    }

    private void handleBack() {
        c2w_ai_loginScene.setFill(Color.TRANSPARENT);
        c2w_ai_primaryStage.setScene(c2w_ai_loginScene);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Class.forName("com.core2web.configuration.FirebaseInitialization");
        getLoginPage(primaryStage);
        primaryStage.setScene(getLoginScene());
        primaryStage.setTitle("ChatApp");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}

