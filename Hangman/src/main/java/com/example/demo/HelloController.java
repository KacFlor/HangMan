package com.example.demo;
import javafx.scene.image.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class HelloController {
    @FXML
    private Label passwordCodedString;
    private String password;
    private char[] passwordNotCoded;
    private char[] passwordCoded;
    private int numIncorrect = 0;
    private int lvl = 1;
    public String[] buttonsTable = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "Ł", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Ą", "Ę", "Ź", "Ż", "Ń", "Ó", "Ć", "spacebar"};
    final private String[] hangmanElements = {"LD", "LL", "LG", "PD", "PP", "PG", "GG", "DD", "SS", "END2"};
    public void initialize() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        Random random = new Random();
        int index = random.nextInt(lines.size());
        password = lines.get(index);
        this.password = password.toUpperCase();
        updateNotCoded();
        updateCoded();
        updateCodedString();
    }
    public void setpassword(String newpassword) {
        password = newpassword;
        this.password = password.toUpperCase();
        updateNotCoded();
        updateCoded();
        updateCodedString();
    }
    private  void updateNotCoded(){
        passwordNotCoded = this.password.toCharArray();
    }
    private void updateCoded() {
        passwordCoded = new char[passwordNotCoded.length];
        Arrays.fill(passwordCoded, '_');
    }
    private void updateCodedString() {
        passwordCodedString.setText(String.valueOf(passwordCoded));
    }
    private void EndGame(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Koniec gry");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        Platform.exit();
    }
    private boolean Won() {
        for (int i = 0; i < passwordNotCoded.length; i++) {
            if (passwordCoded[i] != passwordNotCoded[i]) {
                return false;
            }
        }
        return true;
    }
    public void reset(String[] buttonsLabel) {
        updateCoded();
        updateCodedString();
        numIncorrect = 0;
        for (String element : hangmanElements) {
            ImageView imageView = (ImageView) passwordCodedString.getScene().lookup("#" + element);
            imageView.setVisible(false);
        }
        for (String label : buttonsLabel) {
            Button button = (Button) passwordCodedString.getScene().lookup("#" + label);
            button.setDisable(false);
            button.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
        }
        Button LvlB;
        LvlB = (Button) passwordCodedString.getScene().lookup("#lvll");
        LvlB.setDisable(false);
    }
    @FXML
    public void DecButton(ActionEvent event) {
        Stage newD = new Stage();
        newD.setResizable(false);
        newD.initModality(Modality.APPLICATION_MODAL);
        newD.initOwner(((Node) event.getSource()).getScene().getWindow());
        ImageView imageView;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        imageView = (ImageView) passwordCodedString.getScene().lookup("#D1");
        imageView2 = (ImageView) passwordCodedString.getScene().lookup("#D2");
        imageView3 = (ImageView) passwordCodedString.getScene().lookup("#D3");
        imageView4 = (ImageView) passwordCodedString.getScene().lookup("#D4");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        Label label = new Label("Wybierz widoczność dekoracji: ");
        Button OnButton = new Button("Włączone");
        Button OffButton = new Button("Wyłączone");
        vbox.getChildren().addAll(label,OnButton,OffButton);
        Background grayBackground = new Background(new BackgroundFill(Color.GRAY, null, null));
        vbox.setBackground(grayBackground);
        OnButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
        OffButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
        OnButton.setTextFill(Color.WHITE);
        OffButton.setTextFill(Color.WHITE);

        OnButton.setOnAction(e -> {
            imageView.setVisible(true);
            imageView2.setVisible(true);
            imageView3.setVisible(true);
            imageView4.setVisible(true);
            OnButton.setStyle("-fx-background-color: green; -fx-border-color: green;");
            OnButton.setDisable(true);
            OffButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
            OffButton.setDisable(false);
        });
        OffButton.setOnAction(e -> {
            OffButton.setStyle("-fx-background-color: green; -fx-border-color: green;");
            OffButton.setDisable(true);
            OnButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
            OnButton.setDisable(false);
            imageView.setVisible(false);
            imageView2.setVisible(false);
            imageView3.setVisible(false);
            imageView4.setVisible(false);
        });

        Scene scene = new Scene(vbox, 300, 200);
        newD.setScene(scene);
        newD.show();
    }
    @FXML
    public void lvlButton(ActionEvent event) {
        Stage newL = new Stage();
        newL.setResizable(false);
        newL.initModality(Modality.APPLICATION_MODAL);
        newL.initOwner(((Node) event.getSource()).getScene().getWindow());

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        Label label = new Label("Wybierz poziom trudności: ");
        Label label2 = new Label("Łatwy - 10 prób, Normalny - 6 prób, Trudny - 4 próby ");
        Button EButton = new Button("Łatwy");
        Button NButton = new Button("Normalny");
        Button HButton = new Button("Trudny");
        vbox.getChildren().addAll(label,label2,EButton,NButton,HButton);
        Background grayBackground = new Background(new BackgroundFill(Color.GRAY, null, null));
        vbox.setBackground(grayBackground);
        EButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
        NButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
        HButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
        EButton.setTextFill(Color.WHITE);
        NButton.setTextFill(Color.WHITE);
        HButton.setTextFill(Color.WHITE);
        EButton.setOnAction(e -> {
            lvl = 1;
            EButton.setStyle("-fx-background-color: green; -fx-border-color: green;");
            EButton.setDisable(true);
            NButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
            HButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
            NButton.setDisable(false);
            HButton.setDisable(false);
        });
        NButton.setOnAction(e -> {
            lvl = 2;
            NButton.setStyle("-fx-background-color: green; -fx-border-color: green;");
            NButton.setDisable(true);
            EButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
            HButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
            EButton.setDisable(false);
            HButton.setDisable(false);
        });
        HButton.setOnAction(e -> {
            lvl = 3;
            HButton.setStyle("-fx-background-color: green; -fx-border-color: green;");
            HButton.setDisable(true);
            EButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
            NButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
            EButton.setDisable(false);
            NButton.setDisable(false);
        });
        Button LvlB;
        LvlB = (Button) passwordCodedString.getScene().lookup("#lvll");
        LvlB.setDisable(true);
        Scene scene = new Scene(vbox, 300, 200);
        newL.setScene(scene);
        newL.show();
    }
    @FXML
    public void SettingsButton(ActionEvent event) {
        Stage newP = new Stage();
        newP.setResizable(false);
        newP.initModality(Modality.APPLICATION_MODAL);
        newP.initOwner(((Node) event.getSource()).getScene().getWindow());

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        Label label = new Label("Podaj nowe słowo(maksymalnie 16 znaków!!):");
        TextField textField = new TextField();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z ]*")) { textField.setText(newValue.replaceAll("[^a-zA-Z ]", "")); }
        });
        Button okButton = new Button("Zatwierdź");
        okButton.setStyle(" -fx-border-color: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #808080;");
        okButton.setTextFill(Color.WHITE);
        vbox.getChildren().addAll(label, textField, okButton);
        Background grayBackground = new Background(new BackgroundFill(Color.GRAY, null, null));
        vbox.setBackground(grayBackground);

        int maxLength = 16;
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                textField.setText(oldValue);
            }
        });

        okButton.setOnAction(e -> {
            setpassword(textField.getText());
            reset(buttonsTable);
            newP.close();
        });
        Scene scene = new Scene(vbox, 300, 150);
        newP.setScene(scene);
        newP.show();
    }
    @FXML
    public void ButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String letter = button.getText();
        boolean correctGuess = false;
        ImageView imageView;
        ImageView imageView2;
        ImageView imageView3;
        Button LvlB;
        LvlB = (Button) passwordCodedString.getScene().lookup("#lvll");

        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == letter.charAt(0)) {
                passwordCoded[i] = password.charAt(i);
                correctGuess = true;
            }
        }
        if(lvl == 1) {
            if (correctGuess) {
                updateCodedString();
                button.setStyle("-fx-background-color: green; -fx-border-color: green;");
                button.setDisable(true);

                LvlB.setDisable(true);
                if (Won()) { EndGame("Wygrana!"); }
            } else {
                button.setStyle("-fx-background-color: red; -fx-border-color: red;");
                button.setDisable(true);
                LvlB.setDisable(true);

                if (numIncorrect < 10) {
                    imageView = (ImageView) passwordCodedString.getScene().lookup("#" + hangmanElements[numIncorrect]);
                    imageView.setVisible(true);
                    numIncorrect++;
                    LvlB.setDisable(true);
                }

                if (numIncorrect == 10) {
                    ImageView ddImageView = (ImageView) passwordCodedString.getScene().lookup("#DD");
                    ddImageView.setVisible(false);
                    ImageView end2ImageView = (ImageView) passwordCodedString.getScene().lookup("#END2");
                    end2ImageView.setVisible(true);
                    EndGame("Przegrana! Hasło to: " + password);
                }
            }
        }

        if(lvl == 2) {
            if (correctGuess) {
                updateCodedString();
                button.setStyle("-fx-background-color: green; -fx-border-color: green;");
                button.setDisable(true);
                LvlB.setDisable(true);
                if (Won()) { EndGame("Wygrana!"); }
            } else {
                button.setStyle("-fx-background-color: red; -fx-border-color: red;");
                button.setDisable(true);
                LvlB.setDisable(true);

                if (numIncorrect < 8) {
                    imageView = (ImageView) passwordCodedString.getScene().lookup("#" + hangmanElements[numIncorrect]);
                    imageView.setVisible(true);
                    numIncorrect++;
                    LvlB.setDisable(true);

                    imageView2 = (ImageView) passwordCodedString.getScene().lookup("#" + hangmanElements[numIncorrect]);
                    imageView2.setVisible(true);
                    numIncorrect++;
                }
                else if (numIncorrect == 8) {
                    imageView = (ImageView) passwordCodedString.getScene().lookup("#" + hangmanElements[numIncorrect]);
                    imageView.setVisible(true);
                    numIncorrect++;
                }
                else if (numIncorrect == 9) {
                    imageView = (ImageView) passwordCodedString.getScene().lookup("#" + hangmanElements[numIncorrect]);
                    imageView.setVisible(true);
                    numIncorrect++;
                }

                if (numIncorrect == 10) {
                    ImageView ddImageView = (ImageView) passwordCodedString.getScene().lookup("#DD");
                    ddImageView.setVisible(false);
                    ImageView end2ImageView = (ImageView) passwordCodedString.getScene().lookup("#END2");
                    end2ImageView.setVisible(true);
                    EndGame("Przegrana! Hasło to: " + password);
                }
            }
        }

        if(lvl == 3) {
            if (correctGuess) {
                updateCodedString();
                button.setStyle("-fx-background-color: green; -fx-border-color: green;");
                button.setDisable(true);
                LvlB.setDisable(true);
                if (Won()) { EndGame("Wygrana!"); }
            } else {
                button.setStyle("-fx-background-color: red; -fx-border-color: red;");
                button.setDisable(true);
                LvlB.setDisable(true);

                if (numIncorrect < 9) {
                    imageView = (ImageView) passwordCodedString.getScene().lookup("#" + hangmanElements[numIncorrect]);
                    imageView.setVisible(true);
                    numIncorrect++;
                    LvlB.setDisable(true);

                    imageView2 = (ImageView) passwordCodedString.getScene().lookup("#" + hangmanElements[numIncorrect]);
                    imageView2.setVisible(true);
                    numIncorrect++;

                    imageView3 = (ImageView) passwordCodedString.getScene().lookup("#" + hangmanElements[numIncorrect]);
                    imageView3.setVisible(true);
                    numIncorrect++;
                }
                else if (numIncorrect == 9) {
                    imageView = (ImageView) passwordCodedString.getScene().lookup("#" + hangmanElements[numIncorrect]);
                    imageView.setVisible(true);
                    numIncorrect++;
                }

                if (numIncorrect == 10) {
                    ImageView ddImageView = (ImageView) passwordCodedString.getScene().lookup("#DD");
                    ddImageView.setVisible(false);
                    ImageView end2ImageView = (ImageView) passwordCodedString.getScene().lookup("#END2");
                    end2ImageView.setVisible(true);
                    EndGame("Przegrana! Hasło to: " + password);
                }
            }
        }

    }
}

