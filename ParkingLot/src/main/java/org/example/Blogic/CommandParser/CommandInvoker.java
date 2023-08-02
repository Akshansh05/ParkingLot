package org.example.Blogic.CommandParser;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Blogic.Exceptions.ErrorCode;
import org.example.Manager.ParkingManager;
import org.example.Model.Command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class CommandInvoker {

    private final InputCommandParser inputCommandParser;
    private final ParkingManager parkingManager;
    private Command command;
    private Method method;

    public CommandInvoker() {
        this.inputCommandParser = new InputCommandParser();
        this.parkingManager = new ParkingManager();
    }

    public void invokeTextCommand(String inputLine) {
        try {
            this.command = new Command(inputLine);
            method = inputCommandParser.getCommandMap().get(command.getCommandName());
            if (Objects.isNull(method)) {
                throw new BaseParkingLotException(ErrorCode.BAD_REQUEST_EXCEPTION, "No Such command Found");
            }

            //STATUS
            if (command.getParams().size() == 0) {
                method.invoke(parkingManager);
            } else if (command.getParams().size() == 1) {
                method.invoke(parkingManager, command.getParams().get(0));
            } else if (command.getParams().size() == 2) {
                method.invoke(parkingManager, command.getParams().get(0), command.getParams().get(1));
            }
        } catch (BaseParkingLotException e) {
            System.out.println(e.getMessage());
        } catch (InvocationTargetException | IllegalAccessException e) {
            String message = e.getCause().toString().replace("org.example.Blogic.Exceptions.BaseParkingLotException: ", "");
            System.out.println(message);

        }
    }

    public void invokeFile(String filePath) {
        try {
            File file = new File(filePath);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                invokeTextCommand(input);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
