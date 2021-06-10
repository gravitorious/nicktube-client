package com.nicktheblackbeard;

import com.nicktheblackbeard.client.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import java.lang.ProcessBuilder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author nicktheblackbeard
 * 6/6/21
 */
public class Main extends Application{


    public static void main(String[] args) throws IOException, InterruptedException {
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {


        DownloadSpeed ds = new DownloadSpeed();
        ds.countSpeed();
        TimeUnit.SECONDS.sleep(13);
        DownloadSpeed.floatDownloadSpeed =  DownloadSpeed.downloadSpeed.floatValue()/1000;
        System.out.println(DownloadSpeed.floatDownloadSpeed);
        ClientConnection connection = new ClientConnection();

        primaryStage.setTitle("nickTube");
        GUI gui = new GUI(primaryStage);
        gui.setDownloadSpeedLabel();
        primaryStage.show();


    }

    @Override
    public void stop() throws Exception {
        ClientConnection.closeConnection();
    }
}


/*

 */