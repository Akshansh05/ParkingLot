package org.example.Blogic.CommandParser;

import org.example.Blogic.ConfigConstants.CommandConstants;
import org.example.Manager.ParkingManager;

import java.lang.reflect.Method;
import java.util.HashMap;

public class InputCommandParser {

    private final HashMap<String, Method> commandMap;

    public InputCommandParser() {
        commandMap = new HashMap<>();
        try {
            populateCommandsInMap();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Method> getCommandMap() {
        return commandMap;
    }

    private void populateCommandsInMap() throws NoSuchMethodException {
        commandMap.put(CommandConstants.CREATE_PARKING_LOT, ParkingManager.class.getMethod("createParkingLot", String.class));
        commandMap.put(CommandConstants.PARK, ParkingManager.class.getMethod("park", String.class, String.class));
        commandMap.put(CommandConstants.LEAVE, ParkingManager.class.getMethod("unPark", String.class));
        commandMap.put(CommandConstants.STATUS, ParkingManager.class.getMethod("status"));
        commandMap.put(CommandConstants.REG_NUMBER_FOR_CARS_WITH_COLOR, ParkingManager.class.getMethod("getRegistrationNumbersForVehicleColour", String.class));
        commandMap.put(CommandConstants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR, ParkingManager.class.getMethod("getSlotsNumbersForVehicleColour", String.class));
        commandMap.put(CommandConstants.SLOTS_NUMBER_FOR_REG_NUMBER, ParkingManager.class.getMethod("getSlotNumberForRegNumber", String.class));
    }

}
