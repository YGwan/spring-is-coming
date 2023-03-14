package com.example.httpServlet.server;

import com.example.httpServlet.exception.ServerException;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread {

    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            String request = receive(in);
            send(out, handle(request));
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                throw new ServerException(e.getMessage());
            }
        }
    }

    private String handle(String message) throws IOException {
        final String firstLine = message.split("\n")[0];
        final String responseFile = checkRequestType(firstLine.split(" "));
        final String response = "HTTP/1.1 200 OK\n" +
                "Date: Mon, 27 Jul 2009 12:28:53 GMT\n" +
                "Server: Apache/2.2.14 (Win32)\n" +
                "Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT\n" +
                "Content-Length: " + responseFile.length() + "\n" +
                "Content-Type: text/html\n" +
                "Connection: Closed\n\n" +
                responseFile;
        handlingTime();
        return response;
    }

    private void handlingTime() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new ServerException(e.getMessage());
        }
    }

    private String checkRequestType(String[] index) throws IOException {
        final String responseFile;
        final String filePath;
        if ((index[0].equals("GET")) & index[2].equals("HTTP/1.1")) {
            filePath = "html/index.html";
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            responseFile = String.join("\n", lines);
        } else {
            filePath = "html/wrongPage.html";
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            responseFile = String.join("\n", lines);
        }
        return responseFile;
    }

    private String receive(BufferedReader in) throws IOException {
        List<String> msgLines = new ArrayList<>();
        String msg;
        while (!(msg = in.readLine()).equals("")) {
            msgLines.add(msg);
        }
        return String.join("\n", msgLines);
    }

    private void send(BufferedWriter out, String message) throws IOException {
        out.write(message);
        out.flush();
    }
}
