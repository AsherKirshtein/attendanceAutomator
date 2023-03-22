package com.automation;

import java.util.Scanner;

public class InputReader {

    static String input;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        scanner.close();
    }

    public String getInput() {
        return input;
    }
}
