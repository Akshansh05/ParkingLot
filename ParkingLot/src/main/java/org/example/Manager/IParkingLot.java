package org.example.Manager;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Model.Slot.Slot;

import java.util.HashMap;
import java.util.List;

public interface IParkingLot {

    int createParkingLot(String lotSize) throws BaseParkingLotException;

    Slot park(String regNumber, String colour) throws BaseParkingLotException;

    void unPark(String slotNumber) throws BaseParkingLotException;

    HashMap<String, Slot> status() throws BaseParkingLotException;

    int getSlotNumberForRegNumber(String regNumber) throws BaseParkingLotException;

    List<Integer> getSlotsNumbersForVehicleColour(String colour) throws BaseParkingLotException;

    List<String> getRegistrationNumbersForVehicleColour(String colour) throws BaseParkingLotException;


}
