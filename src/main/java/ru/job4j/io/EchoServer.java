package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream outputStream = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String text = input.readLine();
                    if (text.contains("/?msg=Hello")) {
                        outputStream.write("Hello dear friend".getBytes());
                    } else if (text.contains("/?msg=Exit")) {
                        server.close();
                    } else {
                        outputStream.write("What".getBytes());
                    }
                    outputStream.flush();
                }
            }
        } catch (IOException exception) {
            LOGGER.error("I/O error occurs when opening the socket. ", exception);
        }
    }
}

