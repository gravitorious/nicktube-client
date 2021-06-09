package com.nicktheblackbeard.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author nicktheblackbeard
 * 9/6/21
 */
public class ClientConnection {

    public ClientConnection() throws IOException {

        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        for(;;){


            socket = new Socket("127.0.0.1", 5000);
            oos = new ObjectOutputStream(socket.getOutputStream());
            String msg = "Kalispera server";
            oos.writeObject(msg);
            oos.close();
            socket.close();
        }

    }
}
