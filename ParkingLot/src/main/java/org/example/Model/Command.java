package org.example.Model;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Blogic.Exceptions.ErrorCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    private static final String SPACE = " ";

    private final String commandName;

    private final List<String> params;

    public Command(String inputLine) throws BaseParkingLotException {

        final List<String> tokenList = Arrays.stream(inputLine.trim().split(SPACE)).map(String::trim).filter(token -> token.length() > 0).collect(Collectors.toList());
        if (tokenList.size() == 0) {
            throw new BaseParkingLotException(ErrorCode.BAD_REQUEST_EXCEPTION, "Invalid Command");
        }
        commandName = tokenList.get(0);
        tokenList.remove(0);
        if (tokenList.size() > 2) {
            throw new BaseParkingLotException(ErrorCode.BAD_REQUEST_EXCEPTION, "Too Many Query Parameters");
        }
        params = tokenList;
    }

    public String getCommandName() {
        return commandName;
    }

    public List<String> getParams() {
        return params;
    }
}
