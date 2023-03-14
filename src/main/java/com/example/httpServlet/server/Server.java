package com.example.httpServlet.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void run() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            ServerThread thread = new ServerThread(socket);
            thread.start();
        }
    }
}
