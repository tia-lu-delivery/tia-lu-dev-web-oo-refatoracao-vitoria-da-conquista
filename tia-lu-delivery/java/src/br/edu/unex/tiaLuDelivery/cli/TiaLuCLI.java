package br.edu.unex.tiaLuDelivery.cli;

import java.util.Scanner;

public class TiaLuCLI {

    public void start() {

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Pressione 0 para sair...");
            option = scanner.nextInt();
        } while(option != 0);

    }
}
