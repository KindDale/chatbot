package com.aichatbot.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class HomePage {
    Stage c2w_ai_primaryStage;
    String c2w_ai_userName;
    Scene c2w_ai_homePageScene, c2w_ai_searchPageScene, c2w_ai_notesPageScene;

    public void setScene(Scene c2w_ai_homePageScene) {
        this.c2w_ai_homePageScene = c2w_ai_homePageScene;
    }

    public void setStage(Stage c2w_ai_stage) {
        this.c2w_ai_primaryStage = c2w_ai_stage;
    }

    public void setUserName(String c2w_ai_userName) {
        this.c2w_ai_userName = c2w_ai_userName;
    }

    public Parent getView(Runnable c2w_ai_logout) {
        // 🔽 Background Image
        ImageView c2w_ai_background = new ImageView(new Image("file:assets/background.jpg")); // Change path as needed
        c2w_ai_background.setFitWidth(500);
        c2w_ai_background.setFitHeight(650);
        c2w_ai_background.setPreserveRatio(false);

        // 🔽 Top bar
        Label c2w_ai_title = new Label("Chat App");
        c2w_ai_title.setStyle("-fx-text-fill: white;");
        Button c2w_ai_backButton = new Button("Back");
        c2w_ai_backButton.setStyle(
            "-fx-background-color:rgb(25, 73, 109); -fx-text-fill: white; -fx-background-radius: 20; -fx-font-size: 15px;");
        c2w_ai_backButton.setOnAction(e -> c2w_ai_logout.run());

        HBox c2w_ai_titleBar = new HBox(50, c2w_ai_backButton, c2w_ai_title);
        c2w_ai_titleBar.setPadding(new Insets(10));
        c2w_ai_titleBar.setAlignment(Pos.CENTER_LEFT);
        c2w_ai_titleBar.setPrefHeight(50);
        c2w_ai_titleBar.setStyle("-fx-background-color:rgb(38, 38, 41);");

        // 🔽 Circular buttons
        Circle c2w_ai_search_circle = new Circle(70, Color.WHITE);
        Text c2w_ai_label_search = new Text("Search");
        c2w_ai_label_search.setFont(Font.font(20));
        StackPane c2w_ai_stack_search_circle = new StackPane(c2w_ai_search_circle, c2w_ai_label_search);
        c2w_ai_stack_search_circle.setOnMouseClicked(e -> openSearchPage());

        Circle c2w_ai_notes_circle = new Circle(70, Color.WHITE);
        Text c2w_ai_label_note = new Text("Notes");
        c2w_ai_label_note.setFont(Font.font(20));
        StackPane c2w_ai_stack_notes_circle = new StackPane(c2w_ai_notes_circle, c2w_ai_label_note);
        c2w_ai_stack_notes_circle.setOnMouseClicked(e -> openNotesPage());

        VBox c2w_ai_vb = new VBox(60, c2w_ai_stack_search_circle, c2w_ai_stack_notes_circle);
        c2w_ai_vb.setAlignment(Pos.CENTER);

        VBox c2w_ai_mainVBox = new VBox(120, c2w_ai_titleBar, c2w_ai_vb);
        c2w_ai_mainVBox.setPadding(new Insets(20));
        c2w_ai_mainVBox.setPrefSize(500, 650);

        // 🔽 ScrollPane (if needed)
        ScrollPane c2w_ai_scrollPane = new ScrollPane(c2w_ai_mainVBox);
        c2w_ai_scrollPane.setFitToWidth(true);
        c2w_ai_scrollPane.setStyle("-fx-background-color: transparent;");
        c2w_ai_scrollPane.setPrefSize(500, 650);
        c2w_ai_scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // 🔽 StackPane to layer background + content
        StackPane c2w_ai_root = new StackPane(c2w_ai_background, c2w_ai_scrollPane);

        // 🔽 Rounded corners (optional)
        Rectangle c2w_ai_clip = new Rectangle(300, 650);
        c2w_ai_clip.setArcWidth(40);
        c2w_ai_clip.setArcHeight(40);
        c2w_ai_root.setClip(c2w_ai_clip);

        return c2w_ai_root;
    }

    public void openSearchPage() {
        SearchPage c2w_ai_searchPage = new SearchPage();
        c2w_ai_searchPage.setC2w_ai_userName(c2w_ai_userName);
        c2w_ai_searchPage.setC2w_ai_stage(c2w_ai_primaryStage);
        c2w_ai_searchPageScene = new Scene(c2w_ai_searchPage.getView(this::backToHomePage), 300, 650);
        c2w_ai_searchPageScene.setFill(Color.TRANSPARENT);
        c2w_ai_searchPage.setScene(c2w_ai_searchPageScene);
        c2w_ai_primaryStage.setScene(c2w_ai_searchPageScene);
    }

    public void openNotesPage() {
        NotesPage c2w_ai_notesPage = new NotesPage();
        c2w_ai_notesPage.setC2w_ai_userName(c2w_ai_userName);
        c2w_ai_notesPageScene = new Scene(c2w_ai_notesPage.getView(this::backToHomePage), 300, 650);
        c2w_ai_notesPageScene.setFill(Color.TRANSPARENT);
        c2w_ai_notesPage.setScene(c2w_ai_notesPageScene);
        c2w_ai_primaryStage.setScene(c2w_ai_notesPageScene);
    }

    public void backToHomePage() {
        c2w_ai_primaryStage.setScene(c2w_ai_homePageScene);
    }
}
