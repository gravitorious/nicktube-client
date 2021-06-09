package com.nicktheblackbeard.client;

import com.nicktheblackbeard.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author nicktheblackbeard
 * 9/6/21
 */
public class ClientConnection {

    public static Socket socket = null;
    public static ObjectOutputStream output = null;
    public static ObjectInputStream input = null;

    public ClientConnection() throws IOException {


        for(;;){
            socket = new Socket("127.0.0.1", 5000);
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(DownloadSpeed.floatDownloadSpeed);
            output.close();
            socket.close();
        }

    }
}
