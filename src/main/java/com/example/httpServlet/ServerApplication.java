package com.example.httpServlet;

import com.example.httpServlet.exception.ServerException;
import com.example.httpServlet.server.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerApplication {

    private static final int portNum = 8080;

    public static void main(String[] args) {

        try (final ServerSocket serverSocket = new ServerSocket(portNum)) {
            serverSocket.setReuseAddress(true);
            Server server = new Server(serverSocket);
            server.run();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
    }
}
