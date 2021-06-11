package com.nicktheblackbeard.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public static String sendingProtocol;

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
        this.addListenerToPlayButton();
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
        this.fileNames.getSelectionModel().selectFirst();
        this.boxForPrintingFiles.getChildren().addAll(this.listFilesLabel, this.fileNames);
        this.boxForButton = new HBox(15);
        this.boxForButton.setAlignment(Pos.CENTER);
        ImageView imageView = new ImageView("button.jpeg");
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);
        this.playButton = new Button("",imageView);
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
            this.fileNames.getSelectionModel().selectFirst();
        });
    }


    void addListenerToPlayButton(){
        this.playButton.setOnAction((event) -> {

            String nameOfSelectedFile = (String) this.fileNames.getSelectionModel().getSelectedItem();
            String nameOfSelectedProtocol = (String) this.protocol.getSelectionModel().getSelectedItem();
            try {
                ClientConnection.output.writeObject(nameOfSelectedFile);
                if(nameOfSelectedProtocol.equals("-")){
                    String[] splitname = nameOfSelectedFile.split("-");
                    String[] secondsplit = splitname[1].split("\\.");
                    if(secondsplit[0].equals("240p")) sendingProtocol = "TCP";
                    else if(secondsplit[0].equals("360p") || secondsplit[0].equals("480p")) sendingProtocol = "UDP";
                    else if(secondsplit[0].equals("720p") || secondsplit[0].equals("1080p")) sendingProtocol = "RTP/UDP";
                }
                else{
                    sendingProtocol = nameOfSelectedProtocol;
                }
                ClientConnection.output.writeObject(sendingProtocol);
                String answerToBeginFFplay = (String) ClientConnection.input.readObject();
                System.out.println("διάβασα:" + answerToBeginFFplay);
                if(answerToBeginFFplay.equals("TCP")){
                    TimeUnit.SECONDS.sleep(3);
                    this.streamWithTCP();
                }
                else if(answerToBeginFFplay.equals("UDP")){
                    this.streamWithUDP();
                }
                else if(answerToBeginFFplay.equals("RTP/UDP")){
                    this.streamWithRTP();
                }

                //και το πρωτόκολλο
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void streamWithTCP() throws IOException {
        List<String> commands = new ArrayList<>();
        commands.add("ffplay"); // command
        commands.add("tcp://127.0.0.1:4010");

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File(System.getProperty("user.dir") + "/ffmpeg/"));
        pb.redirectErrorStream(true);

        // startinf the process
        Process process = pb.start();

        BufferedReader stdInput
                = new BufferedReader(new InputStreamReader(
                process.getInputStream()));

        String s;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
    }


    private void streamWithUDP() throws IOException {
        List<String> commands = new ArrayList<>();
        commands.add("ffplay"); // command
        commands.add("udp://127.0.0.1:5000");

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File(System.getProperty("user.dir") + "/ffmpeg/"));
        pb.redirectErrorStream(true);

        // startinf the process
        Process process = pb.start();

        BufferedReader stdInput
                = new BufferedReader(new InputStreamReader(
                process.getInputStream()));

        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
    }

    private void streamWithRTP() throws IOException {
        List<String> commands = new ArrayList<>();
        commands.add("ffplay");
        commands.add("-protocol_whitelist");
        commands.add("file,rtp,udp");
        commands.add("-i");
        commands.add("video.sdp");
        commands.add("-fflags");
        commands.add("nobuffer");

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File(System.getProperty("user.dir") + "/ffmpeg/"));
        pb.redirectErrorStream(true);

        // startinf the process
        Process process = pb.start();

        BufferedReader stdInput
                = new BufferedReader(new InputStreamReader(
                process.getInputStream()));

        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
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
