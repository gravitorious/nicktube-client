package com.nicktheblackbeard.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.border.Border;

/**
 * @author nicktheblackbeard
 * 9/6/21
 */
public class GUI{

    Label appNameLabel;
    Label fileTypeLabel;
    Label protocolLabel;
    Label downloadSpeedLabel;
    Label listFilesLabel;
    Button playButton;
    ChoiceBox fileType;
    ChoiceBox protocol;
    ListView fileNames;

    HBox root;

    HBox boxForName;

    VBox leftBox;
    HBox boxForTypeFiles;
    HBox boxForProtocol;
    HBox boxForSpeedLabel;

    VBox rightBox;
    VBox boxForPrintingFiles;

    Scene scene;

    public GUI(Stage primaryStage){
        this.root = new HBox(40);
        //this.root.setAlignment(Pos.BASELINE_LEFT);
        this.scene = new Scene(root, 550, 450);
        this.scene.getStylesheets().add(getClass().getResource("form.css").toExternalForm());
        this.root.getStyleClass().add("bg");
        this.leftBox = new VBox(40);
        this.leftBox.setAlignment(Pos.TOP_LEFT);
        this.rightBox = new VBox(40);
        this.rightBox.setAlignment(Pos.TOP_RIGHT);

        this.createName();
        this.createLeftBox();
        this.createRightBox();

        this.root.getChildren().addAll(this.leftBox, this.rightBox);
        primaryStage.setScene(this.scene);
    }


    private void createName(){
        this.boxForName = new HBox();
        this.boxForName.setAlignment(Pos.TOP_LEFT);
        this.appNameLabel = new Label("nickTube");
        this.appNameLabel.getStyleClass().add("namelabel");
        this.boxForName.getChildren().add(this.appNameLabel);
        this.leftBox.getChildren().add(this.boxForName);
    }

    private void createLeftBox(){

        this.fileTypeLabel = new Label("File type: ");
        this.fileTypeLabel.getStyleClass().add("label1");
        this.fileType = new ChoiceBox();
        this.fileType.getItems().add("mkv");
        this.fileType.getItems().add("mp4");
        this.fileType.getItems().add("avi");
        this.boxForTypeFiles = new HBox(5);
        this.boxForTypeFiles.getChildren().addAll(this.fileTypeLabel, this.fileType);

        this.protocolLabel = new Label("Protocol: ");
        this.protocol = new ChoiceBox();
        this.protocol.getItems().add("-");
        this.protocol.getItems().add("TCP");
        this.protocol.getItems().add("UDP");
        this.protocol.getItems().add("RTP/UDP");
        this.boxForProtocol = new HBox(5);
        this.boxForProtocol.getChildren().addAll(this.protocolLabel, this.protocol);

        this.downloadSpeedLabel = new Label();
        this.boxForSpeedLabel = new HBox(5);
        this.boxForSpeedLabel.setAlignment(Pos.BOTTOM_LEFT);
        this.boxForSpeedLabel.getChildren().add(this.downloadSpeedLabel);

        this.leftBox.getChildren().addAll(this.boxForTypeFiles, this.boxForProtocol, this.boxForSpeedLabel);
    }


    public void createRightBox(){
        this.boxForPrintingFiles = new VBox(7);
        this.listFilesLabel = new Label("Choose one file");
        this.fileNames = new ListView();
        this.fileNames.setPrefSize(230 ,280);
        fileNames.getItems().add("Item 1gergregrgrger");
        fileNames.getItems().add("Item 1gergregerverbegerer");
        fileNames.getItems().add("Item 1vegergergreer");
        fileNames.getItems().add("Item 1gergregre");
        fileNames.getItems().add("Item 1grgergerg");
        fileNames.getItems().add("Item 1");
        fileNames.getItems().add("Item 1");
        fileNames.getItems().add("Item gergregergerg1");
        fileNames.getItems().add("Item 1greger");
        fileNames.getItems().add("Item gergr1");
        this.boxForPrintingFiles.getChildren().addAll(this.listFilesLabel, this.fileNames);

        this.playButton = new Button("Play");

        this.rightBox.getChildren().addAll(this.boxForPrintingFiles, this.playButton);
    }
    
}
