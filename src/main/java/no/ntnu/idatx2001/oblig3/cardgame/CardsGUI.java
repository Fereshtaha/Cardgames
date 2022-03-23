package no.ntnu.idatx2001.oblig3.cardgame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class CardsGUI extends Application {
    private DeckOfCards deckOfCards = new DeckOfCards();

    private class ButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent ev) {

        }
    }

    public void start(Stage primaryStage) {

        primaryStage.setTitle("Card game");

        // Knapper
        Button button = new Button(" Deal Hand ");
        Button button1 = new Button(" Check Hand ");

        // Tekst
        Label text = new Label("Sum of the faces: ");
        Label text1 = new Label("Cards of hearts: ");
        Label text2 = new Label("Flush: ");
        Label text3 = new Label("Queen of spades: ");

        // tom label
        Label text4 = new Label(" ");

        // tekstfelter
        TextField textField = new TextField();
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();

        // lager en gridpane
        GridPane rootNode = new GridPane();
        rootNode.setMinSize(500, 300);
        rootNode.setPadding(new Insets(10,10,10,10));

        // setter vertikale og horisontale mellomrom
        rootNode.setVgap(10);
        rootNode.setHgap(10);


        // legger til knappene og tekstfeltene i gridpanen
        rootNode.add(button,1,0);
        rootNode.add(button1, 2, 0);

        rootNode.add(text, 0,2);
        rootNode.add(text1,1,2);
        rootNode.add(text2,2,2);
        rootNode.add(text3, 3, 2);

        rootNode.add(text4, 2,1);

        rootNode.add(textField, 0,3);
        rootNode.add(textField1,1,3);
        rootNode.add(textField2, 2, 3);
        rootNode.add(textField3,3,3);


        // sjekker om inputen er int, og hvis den er int, så kommer det opp en liste med hvilke kort spilleren fikk
        button.setOnAction(actionEvent -> {
            if (isInt(textField, textField.getText())) {
                // legger til Hbox,slik at kortene blir lagt i en horisontal liste
                HBox hBox = new HBox();
                for (PlayingCard playingCard : deckOfCards.dealHand(Integer.parseInt(textField.getText()))) {
                    System.out.println(playingCard.getAsString());
                    Label label = new Label("  " + playingCard.getAsString());

                    label.setMaxWidth(180);
                    label.setWrapText(true);

                    hBox.getChildren().add(label);

                    Font font1 = Font.font("Lato", FontWeight.BOLD, 30);
                    label.setFont(font1);

                }
                // legger
                 rootNode.add(hBox,1,1);

            }
        });

        button1.setOnAction(actionEvent -> {
            for (PlayingCard playingCard: deckOfCards.printHearts(deckOfCards.getCardDeck())) {


            }
            if (deckOfCards.hasFlush(deckOfCards.getCardDeck())) {
                textField2.setText("Yes :)");
            } else {
                textField2.setText("Nope");
            }

            if (deckOfCards.hasQueenOfSpades(deckOfCards.getCardDeck())) {
                textField3.setText("Yes");
            } else {
                textField3.setText("Nopee");
            }
        });




        Scene scene = new Scene(rootNode, 600, 400 );

        BackgroundFill backgroundFill = new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        rootNode.setBackground(background);

        Font font = Font.font("Lato", FontWeight.BOLD, 14);
        button.setFont(font);
        button1.setFont(font);
        text.setFont(font);
        text1.setFont(font);
        text2.setFont(font);
        text3.setFont(font);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean isInt(TextField input, String message) {
        try {
            int amount = Integer.parseInt(input.getText());
            System.out.println("Brukeren tastet inn " + amount);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error " + message);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong input");
            alert.setHeaderText("You have to type in a number");
            alert.show();
            return false;
        }
    }
}

