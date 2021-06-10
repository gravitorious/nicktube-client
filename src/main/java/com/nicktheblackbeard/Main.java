package com.nicktheblackbeard;

import com.nicktheblackbeard.client.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
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
        /*
        primaryStage.setTitle("nickTube");
        GUI gui = new GUI(primaryStage);
        gui.setDownloadSpeedLabel();
        primaryStage.show();*/
    }


}


/*

 */