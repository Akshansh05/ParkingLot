package org.example.Manager;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Blogic.ParkingStrategy.IParkingStrategy;
import org.example.Blogic.ParkingStrategy.NaturalOrderParkingStrategy;
import org.example.Model.Slot.Slot;
import org.example.Model.Vehicle.Car;
import org.example.Model.Vehicle.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ParkingManager implements IParkingLot {

    private final SlotManager slotManager;

    public ParkingManager(IParkingStrategy parkingStrategy, int MAX_CAPACITY) {
        slotManager = new SlotManager(parkingStrategy, MAX_CAPACITY);
    }

    public ParkingManager() {
        //default Configuration
        slotManager = new SlotManager(new NaturalOrderParkingStrategy(), 100);
    }

    @Override
    public int createParkingLot(String lotSize) throws BaseParkingLotException {
        Integer size = slotManager.createEmptySlots(Integer.parseInt(lotSize));
        System.out.println("Slots Created with size " + lotSize);
        return size;
    }

    @Override
    public Slot park(String regNumber, String colour) throws BaseParkingLotException {
        Vehicle vehicle = new Car(regNumber, colour); //can have factory Design pattern
        Slot slot = slotManager.occupyNextAvailableSlot(vehicle);
        System.out.println("Parked  Vehicle " + regNumber);
        return slot;
    }


    @Override
    public void unPark(String slotNumber) throws BaseParkingLotException {
        slotManager.unOccupySlot(Integer.parseInt(slotNumber));
        System.out.println("Vehicle Removed at " + slotNumber);
    }

    public HashMap<String, Slot> status() throws BaseParkingLotException {
        HashMap<String, Slot> regNumberSlotMap = slotManager.getStatus();
        for (Map.Entry regNumber : regNumberSlotMap.entrySet()) {
            Slot s = (Slot) regNumber.getValue();
            if (!Objects.isNull(s.getVehicle())) {
                System.out.println("Car " + regNumber.getKey() + " is parked on slot " + s.getSlotId() + " and its colour is " + s.getVehicle().getColour() + ".");
            }
        }
        return regNumberSlotMap;
    }

    @Override
    public int getSlotNumberForRegNumber(String regNumber) throws BaseParkingLotException {
        Integer slotNumber = slotManager.getSlotNumberFromRegNumber(regNumber);
        System.out.println("Slot for " + regNumber + " is " + slotNumber);
        return slotNumber;
    }

    @Override
    public List<Integer> getSlotsNumbersForVehicleColour(String colour) throws BaseParkingLotException {
        List<Integer> slotNumbers = slotManager.getSlotsNumbersForVehicleColour(colour);
        String out = "";
        for (int i = 0; i < slotNumbers.size(); i++) {
            out += slotNumbers.get(i) + ",";
        }
        System.out.println(out.substring(0, out.length() - 1));
        return slotNumbers;
    }

    @Override
    public List<String> getRegistrationNumbersForVehicleColour(String colour) throws BaseParkingLotException {
        List<String> regNumbers = slotManager.getRegistrationNumberFromVehicleColour(colour);
        String out = "";
        for (int i = 0; i < regNumbers.size(); i++) {
            out += regNumbers.get(i) + ",";
        }
        System.out.println(out.substring(0, out.length() - 1));

        return regNumbers;
    }

    public int getEmptySlotCount() {
        return slotManager.getEmptySlotCount();
    }
}
