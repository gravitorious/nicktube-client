package com.nicktheblackbeard.client;

import com.nicktheblackbeard.Main;
import com.nicktheblackbeard.clientdata.NClient;
import com.nicktheblackbeard.clientdata.NFile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author nicktheblackbeard
 * 9/6/21
 */

/*
    We use this class to connect to server
    We use output and input variables to communicate with server in GUI
 */

public class ClientConnection {

    public static Socket socket = null;
    public static ObjectOutputStream output = null;
    public static ObjectInputStream input = null;
    public static NClient client = null;

    public ClientConnection() throws IOException, ClassNotFoundException {

        socket = new Socket("127.0.0.1", 5000);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        output.writeObject(DownloadSpeed.floatDownloadSpeed);
        client = (NClient) input.readObject();
        ArrayList<NFile> avi_files = client.getAviFiles();
        ArrayList<NFile> mp4_files = client.getMp4Files();
        ArrayList<NFile> mkv_files = client.getMkvFiles();
        Main.log.info("Avi list read from server");
        Main.log.info(this.listToString(avi_files));
        Main.log.info("Mp4 list read from server");
        Main.log.info(this.listToString(mp4_files));
        Main.log.info("Mkv list read from server");
        Main.log.info(this.listToString(mkv_files));

    }

    public static void closeConnection() throws IOException {
        input.close();
        output.close();
        socket.close();
    }


    private String listToString(ArrayList<NFile> files){
        StringBuffer buffer = new StringBuffer();
        for(NFile file : files){
            buffer.append(file.toString());
        }
        String fileslist = buffer.toString();
        return fileslist;
    }
}
