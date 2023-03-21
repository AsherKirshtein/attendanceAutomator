package com.automation.Server;

public class serverRunner {
    public static void main(String[] args) {
        InfoServer server = new InfoServer(8080);
        server.run();
    }
}
