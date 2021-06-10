package com.nicktheblackbeard.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    HBox boxForButton;

    Scene scene;

    public static int metr = 0;

    public GUI(Stage primaryStage){
        this.root = new HBox(40);
        //this.root.setAlignment(Pos.BASELINE_LEFT);
        this.scene = new Scene(root, 570, 450);
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

        this.addListenerToFileTypeList();
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
        this.fileType = new ChoiceBox();
        this.fileType.getItems().add("mkv");
        this.fileType.getItems().add("mp4");
        this.fileType.getItems().add("avi");
        this.fileType.getSelectionModel().selectFirst();
        this.boxForTypeFiles = new HBox(5);
        this.boxForTypeFiles.getChildren().addAll(this.fileTypeLabel, this.fileType);

        this.protocolLabel = new Label("Protocol: ");
        this.protocol = new ChoiceBox();
        this.protocol.getItems().add("-");
        this.protocol.getItems().add("TCP");
        this.protocol.getItems().add("UDP");
        this.protocol.getItems().add("RTP/UDP");
        this.protocol.getSelectionModel().selectFirst();
        this.boxForProtocol = new HBox(5);
        this.boxForProtocol.getChildren().addAll(this.protocolLabel, this.protocol);

        this.downloadSpeedLabel = new Label();
        this.boxForSpeedLabel = new HBox(5);
        this.boxForSpeedLabel.setAlignment(Pos.BOTTOM_LEFT);
        this.boxForSpeedLabel.getChildren().add(this.downloadSpeedLabel);

        this.leftBox.getChildren().addAll(this.boxForTypeFiles, this.boxForProtocol, this.boxForSpeedLabel);
    }


    public void createRightBox(){
        this.boxForPrintingFiles = new VBox(14);
        this.listFilesLabel = new Label("Choose one file");
        this.fileNames = new ListView();
        this.fileNames.setPrefSize(230 ,280);
        this.addMkvList();
        this.boxForPrintingFiles.getChildren().addAll(this.listFilesLabel, this.fileNames);
        this.boxForButton = new HBox(15);
        this.boxForButton.setAlignment(Pos.CENTER);
        this.playButton = new Button("Play");
        this.boxForButton.getChildren().add(playButton);

        this.rightBox.getChildren().addAll(this.boxForPrintingFiles, this.boxForButton);
    }

    public void setDownloadSpeedLabel(){
        this.downloadSpeedLabel.setText("Your download speed is: " + DownloadSpeed.floatDownloadSpeed + " kbps");
    }

    void addListenerToFileTypeList(){
        this.fileType.setOnAction((event) -> {
            //int selectedIndex = this.fileType.getSelectionModel().getSelectedIndex();
            this.clearFilesList();
            String selectedItem = (String) this.fileType.getSelectionModel().getSelectedItem();
            if(selectedItem.equals("mkv")) this.addMkvList();
            else if(selectedItem.equals("mp4")) this.addMp4List();
            else if(selectedItem.equals("avi")) this.addAviList();
        });
    }

    private void clearFilesList(){
        this.fileNames.getItems().clear();
    }

    private void addMkvList(){
        String filename;
        String format;
        String quality;
        int size;
        for(int i = 0; i < ClientConnection.client.getMkvFiles().size(); i++){
            filename = ClientConnection.client.getMkvFiles().get(i).getName();
            format = ClientConnection.client.getMkvFiles().get(i).getFormat();
            size = ClientConnection.client.getMkvFiles().get(i).getQualities().size();
            for(int j = 0; j < size; j++){
                quality = ClientConnection.client.getMkvFiles().get(i).getQualities().get(j);
                this.fileNames.getItems().add(filename + "-" + quality + "." + format);
            }
        }
    }

    private void addMp4List(){
        String filename;
        String format;
        String quality;
        int size;
        for(int i = 0; i < ClientConnection.client.getMp4Files().size(); i++){
            filename = ClientConnection.client.getMp4Files().get(i).getName();
            format = ClientConnection.client.getMp4Files().get(i).getFormat();
            size = ClientConnection.client.getMp4Files().get(i).getQualities().size();
            for(int j = 0; j < size; j++){
                quality = ClientConnection.client.getMp4Files().get(i).getQualities().get(j);
                this.fileNames.getItems().add(filename + "-" + quality + "." + format);
            }
        }
    }

    private void addAviList(){
        String filename;
        String format;
        String quality;
        int size;
        for(int i = 0; i < ClientConnection.client.getAviFiles().size(); i++){
            filename = ClientConnection.client.getAviFiles().get(i).getName();
            format = ClientConnection.client.getAviFiles().get(i).getFormat();
            size = ClientConnection.client.getAviFiles().get(i).getQualities().size();
            for(int j = 0; j < size; j++){
                quality = ClientConnection.client.getAviFiles().get(i).getQualities().get(j);
                this.fileNames.getItems().add(filename + "-" + quality + "." + format);
            }
        }
    }

}
