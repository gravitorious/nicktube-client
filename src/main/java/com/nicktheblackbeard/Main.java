package com.nicktheblackbeard;

import com.nicktheblackbeard.client.*;
import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;
import fr.bmartel.speedtest.SpeedTestSocket;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Guard;
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
        TimeUnit.SECONDS.sleep(9);
        primaryStage.setTitle("nickTube");
        GUI gui = new GUI(primaryStage);
        gui.setDownloadSpeedLabel(DownloadSpeed.downloadSpeed.doubleValue()/1000);
        primaryStage.show();
    }


}


/*

 */