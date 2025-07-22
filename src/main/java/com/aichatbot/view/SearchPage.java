package com.aichatbot.view;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.fxmisc.richtext.InlineCssTextArea;
import com.aichatbot.controller.AiApiController;
import com.aichatbot.controller.FormatController;
import com.aichatbot.controller.NotesController;
import com.aichatbot.model.Note;
import com.aichatbot.utilities.Snackbar;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SearchPage {
    Scene c2w_ai_searchScene;
    Stage c2w_ai_stage;
    private VBox c2w_ai_chatBox = new VBox(15);
    ScrollPane c2w_ai_scrollPane = null;
    String c2w_ai_userName;

    private Parser c2w_ai_markdownParser = Parser.builder().build();
    private TextContentRenderer c2w_ai_renderer = TextContentRenderer.builder().build();

    AiApiController c2w_ai_controller = new AiApiController();
    FormatController c2w_ai_formatController = new FormatController();
    NotesController c2w_ai_notesController = new NotesController();

    public void setScene(Scene c2w_ai_searchScene) {
        this.c2w_ai_searchScene = c2w_ai_searchScene;
    }

    public void setC2w_ai_stage(Stage c2w_ai_stage) {
        this.c2w_ai_stage = c2w_ai_stage;
    }

    public void setC2w_ai_userName(String c2w_ai_userName) {
        this.c2w_ai_userName = c2w_ai_userName;
    }

    public Parent getView(Runnable c2w_ai_back) {
        TextField c2w_ai_inputField = new TextField();
        c2w_ai_inputField.setPromptText("Ask something...");
        c2w_ai_inputField.setStyle("-fx-background-radius: 20;");
        c2w_ai_inputField.setFont(Font.font("Segoe UI", 14));
        c2w_ai_inputField.setPrefHeight(35);
        c2w_ai_inputField.setPrefWidth(200);

        Button c2w_ai_sendBtn = new Button("Send");
        c2w_ai_sendBtn.setFont(Font.font("Segoe UI Semibold", 14));
        c2w_ai_sendBtn.setStyle("-fx-background-color: #0078D4; -fx-text-fill: white; -fx-background-radius: 20;");
        c2w_ai_sendBtn.setPrefHeight(35);
        c2w_ai_sendBtn.setDefaultButton(true);

        c2w_ai_sendBtn.setOnAction(e -> {
            String c2w_ai_question = c2w_ai_inputField.getText();
            if (!c2w_ai_question.isBlank()) {
                addUserBubble(c2w_ai_question);
                String c2w_ai_response = c2w_ai_controller.callGeminiAPI(c2w_ai_question);
                System.out.println(c2w_ai_response);
                addBotBubble(c2w_ai_question, c2w_ai_response);
                c2w_ai_inputField.clear();
            }
        });

        c2w_ai_scrollPane = new ScrollPane(c2w_ai_chatBox);
        c2w_ai_scrollPane.setFitToWidth(true);
        c2w_ai_scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        c2w_ai_scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        c2w_ai_scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        c2w_ai_scrollPane.setPannable(false);
        c2w_ai_scrollPane.setOnScroll(event -> event.consume());
        Platform.runLater(() -> c2w_ai_scrollPane.setVvalue(1.0));

        c2w_ai_chatBox.setPadding(new Insets(10));
        c2w_ai_chatBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        Label c2w_ai_title = new Label("Search");
        c2w_ai_title.setStyle(" -fx-text-fill: white;");

        Button c2w_ai_backButton = new Button("Back");
        c2w_ai_backButton.setStyle("-fx-background-color:rgb(25, 73, 109); -fx-text-fill: white; -fx-background-radius: 20; -fx-font-size: 15px;");
        c2w_ai_backButton.setOnAction(arg0 -> c2w_ai_back.run());

        HBox c2w_ai_titleBar = new HBox(50, c2w_ai_backButton, c2w_ai_title);
        c2w_ai_titleBar.setPadding(new Insets(10));
        c2w_ai_titleBar.setAlignment(Pos.CENTER_LEFT);
        c2w_ai_titleBar.setPrefHeight(50);
        c2w_ai_titleBar.setStyle("-fx-background-color:rgb(38, 38, 41); -fx-text-fill: white;");

        HBox c2w_ai_inputBar = new HBox(10, c2w_ai_inputField, c2w_ai_sendBtn);
        c2w_ai_inputBar.setPadding(new Insets(10, 10, 20, 10));
        c2w_ai_inputBar.setAlignment(Pos.CENTER);
        c2w_ai_inputBar.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox c2w_ai_root = new VBox(10, c2w_ai_titleBar, c2w_ai_scrollPane, c2w_ai_inputBar);
        c2w_ai_root.setStyle("-fx-background-color: black; -fx-font-size: 20px; -fx-font-family: 'Segoe UI';");
        VBox.setVgrow(c2w_ai_scrollPane, Priority.ALWAYS);

        Rectangle c2w_ai_clip = new Rectangle(300, 650);
        c2w_ai_clip.setArcWidth(40);
        c2w_ai_clip.setArcHeight(40);
        c2w_ai_root.setClip(c2w_ai_clip);

        return c2w_ai_root;
    }

    private void addUserBubble(String text) {
        Label c2w_ai_label = new Label(text);
        c2w_ai_label.setWrapText(true);
        c2w_ai_label.setStyle("-fx-background-color:rgba(237, 66, 94, 0.42); -fx-padding: 10; -fx-background-radius: 10;-fx-font-size: 14px; -fx-text-fill : white");
        HBox c2w_ai_container = new HBox(c2w_ai_label);
        c2w_ai_container.setAlignment(Pos.CENTER_RIGHT);
        c2w_ai_container.setPadding(new Insets(2));
        c2w_ai_chatBox.getChildren().add(c2w_ai_container);
    }

    private void addBotBubble(String c2w_ai_question, String c2w_ai_markdown) {
        String c2w_ai_plainText = c2w_ai_renderer.render(c2w_ai_markdownParser.parse(c2w_ai_markdown));
        InlineCssTextArea c2w_ai_area = new InlineCssTextArea();
        c2w_ai_area.replaceText(c2w_ai_plainText);
        c2w_ai_area.setWrapText(true);
        c2w_ai_area.setEditable(false);
        c2w_ai_area.setPrefWidth(600);
        c2w_ai_area.setStyle("-fx-background-color:rgba(34, 17, 129, 0.42); -fx-font-family: 'Segoe UI'; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 12;");

        c2w_ai_formatController.formatAndDisplayAIResponse(c2w_ai_area, c2w_ai_plainText);

        Platform.runLater(() -> c2w_ai_area.scrollYToPixel(0));
        c2w_ai_area.moveTo(0);
        Platform.runLater(() -> c2w_ai_area.requestFollowCaret());

        c2w_ai_area.textProperty().addListener((obs, old, val) -> {
            c2w_ai_area.setPrefHeight(c2w_ai_area.getTotalHeightEstimate());
        });

        HBox c2w_ai_container = new HBox(c2w_ai_area);
        c2w_ai_container.setAlignment(Pos.CENTER_LEFT);

        Label c2w_ai_add = new Label("Add to Notes");
        c2w_ai_add.setStyle("-fx-font-size: 10px;");

        VBox c2w_ai_vb = new VBox(10, c2w_ai_container, c2w_ai_add);
        c2w_ai_add.setOnMouseClicked(arg0 -> {
            Note note = new Note();
            note.setQuestion(c2w_ai_question);
            note.setAnswer(c2w_ai_plainText);
            note.setUserName(c2w_ai_userName);
            c2w_ai_notesController.addNote(note);
            Snackbar.show(c2w_ai_stage, "Note Successfully Added");
            c2w_ai_vb.getChildren().remove(1);
        });

        c2w_ai_vb.setAlignment(Pos.CENTER_LEFT);
        c2w_ai_vb.setPadding(new Insets(2));
        c2w_ai_chatBox.getChildren().add(c2w_ai_vb);
        Platform.runLater(() -> c2w_ai_scrollPane.setVvalue(1.0));
    }
}
