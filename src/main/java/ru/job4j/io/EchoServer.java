package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream outputStream = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    for (String str = input.readLine(); str != null && !str.isEmpty(); str = input.readLine()) {
                        if (!str.contains("/?msg=")) {
                            continue;
                        }
                        if (str.contains("/?msg=Exit")) {
                            server.close();
                            break;
                        }
                        if (str.contains("/?msg=Hello")) {
                            outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            outputStream.write("Hello dear friend".getBytes());
                            break;
                        }
                        outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        outputStream.write("What".getBytes());
                    }
                    outputStream.flush();
                }
            }
        }
    }
}
