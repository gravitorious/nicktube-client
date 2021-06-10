package com.nicktheblackbeard.client;

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
        printList(avi_files);
        printList(mp4_files);
        printList(mkv_files);

        System.out.println();



    }

    public static void closeConnection() throws IOException {
        input.close();
        output.close();
        socket.close();
    }


    void printList(ArrayList<NFile> files){
        for(NFile file : files){
            System.out.println(file.toString());
        }
    }
}
