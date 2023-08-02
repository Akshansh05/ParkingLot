package org.example;

import org.example.Blogic.CommandParser.CommandInvoker;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ParkingLotApplication {
    public static void main(String[] args) {

        CommandInvoker commandInvoker = new CommandInvoker();

        switch (args.length) {
            case 0: {
                // Interactive mode
                System.out.println("Enter 'exit' to quit");
                System.out.println("Waiting for input");
                while (true) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        String inputText = bufferedReader.readLine();
                        if (inputText.equalsIgnoreCase("exit")) {
                            break;
                        } else {
                            commandInvoker.invokeTextCommand(inputText);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
                break;

            }
            case 1: {
                commandInvoker.invokeFile(args[0]);
                break;
            }
            default:
                System.out.println("Invalid Input");

        }
    }
}