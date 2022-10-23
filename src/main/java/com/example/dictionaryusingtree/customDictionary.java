package com.example.dictionaryusingtree;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;

public class customDictionary extends Application {

    private Group tileGrp= new Group();
    DictionaryBackend dictionary;

    private Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(400,400);
        root.getChildren().addAll(tileGrp);

        int xLine=20;
        int yLine=20;
        int yLine2=50;

        dictionary = new DictionaryBackend();

        TextField wordTxt= new TextField("Type the word here");

        wordTxt.setTranslateX(xLine);
        wordTxt.setTranslateY(yLine);

        Label txtMeaning= new Label("I am meaning");
        txtMeaning.setTranslateX(xLine);
        txtMeaning.setTranslateY(yLine2);


        Button searchButton= new Button("Search");
        searchButton.setTranslateX(xLine+200);
        searchButton.setTranslateY(yLine);

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String word= wordTxt.getText();
                if(word.isBlank()){
                    txtMeaning.setText("Please enter a word");
                }
                else{
                    txtMeaning.setText(dictionary.findMeaning(word));
                }
            }
        });

        tileGrp.getChildren().addAll(wordTxt,searchButton,txtMeaning);

        return  root;
    }

    public void connectToDatabase(){
        final String DB_URL = "jdbc:mysql://localhost:3306/dictionarydb";
        final String USER = "root";
        final String PASS = "Lipsa@1*";

        System.out.println("Connecting to database");
        String newId = "select * from word_list";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(newId);
        ) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + rs.getString("word") + rs.getString("meaning"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        connectToDatabase();
        Scene scene = new Scene(createContent());
        stage.setTitle("Custom Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}