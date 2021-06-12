package com.nicktheblackbeard;

import com.nicktheblackbeard.client.*;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.TimeUnit;

/**
 * @author nicktheblackbeard
 * 6/6/21
 */
public class Main extends Application{

    static public Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args){
        Main.log.info("Client is on...");
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        DownloadSpeed ds = new DownloadSpeed();
        ds.countSpeed(); //count the download speed
        TimeUnit.SECONDS.sleep(13); //wait for 13 seconds
        DownloadSpeed.floatDownloadSpeed =  DownloadSpeed.downloadSpeed.floatValue()/1000;
        Main.log.debug("Download speed is: " + DownloadSpeed.floatDownloadSpeed);
        ClientConnection connection = new ClientConnection(); //create the connection with the server
        primaryStage.setTitle("nickTube");
        GUI gui = new GUI(primaryStage); //create the GUI
        gui.setDownloadSpeedLabel();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        ClientConnection.output.writeObject("-1"); //inform server that client closed the app
        ClientConnection.closeConnection();
    }
}


/*

 */