package com.automation.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * INFO SERVER collects information from the client and prints it to the console
 *
 */
public class InfoServer extends Thread {

    private ServerSocket serverSocket;
    private int PORT;
    HashMap<Integer, Student> studentInfo = new HashMap<Integer, Student>();
    private static final Logger LOGGER = Logger.getLogger(InfoServer.class.getName());

    public InfoServer(int port) {
        this.PORT = port;
        this.studentInfo = new HashMap<Integer, Student>();
        configureClass();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                this.serverSocket = new ServerSocket(this.PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String message = in.readLine();
                    if (message != null) {
                        Student student = getStudent(message);
                        LOGGER.info(student.toString() + " has checked in");
                    }
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private Student getStudent(String id) {
        Integer studentId = Integer.parseInt(id);
        return this.studentInfo.get(studentId);
    }

    private void configureClass() {
        Student student1 = new Student("John", 1768154154);
        Student student2 = new Student("Jane", 1767775370);
        this.studentInfo.put(student1.getId(), student1);
        this.studentInfo.put(student2.getId(), student2);
    }

}
