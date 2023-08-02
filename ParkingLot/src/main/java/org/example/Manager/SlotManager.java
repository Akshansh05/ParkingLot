package org.example.Manager;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Blogic.Exceptions.ErrorCode;
import org.example.Blogic.ParkingStrategy.IParkingStrategy;
import org.example.Model.Slot.Slot;
import org.example.Model.Vehicle.Vehicle;

import java.util.*;

public class SlotManager {

    private final IParkingStrategy parkingStrategy;
    private final HashMap<Integer, Slot> slotMap; //slotNumber to SlotMap
    private final HashMap<String, Slot> regNumberSlotMap;//regNumber to SlotMap if O(1) required else we can stream


    protected SlotManager(IParkingStrategy parkingStrategy, int MAX_CAPACITY) {
        this.parkingStrategy = parkingStrategy;
        parkingStrategy.setMaximumCapacity(MAX_CAPACITY);
        this.slotMap = new HashMap<>(MAX_CAPACITY);
        this.regNumberSlotMap = new HashMap<>(MAX_CAPACITY);
    }


    Integer createEmptySlots(int maxSlots) throws BaseParkingLotException {
        for (int slotId = 0; slotId < maxSlots; slotId++) {
            parkingStrategy.addSlots(slotId);
            Slot newSlot = new Slot(slotId);
            slotMap.put(slotId, newSlot);
        }
        return getAllSlotCount();
    }

    Slot occupyNextAvailableSlot(Vehicle vehicle) throws BaseParkingLotException {
        if (regNumberSlotMap.containsKey(vehicle.getRegistrationNumber())) {
            throw new BaseParkingLotException(ErrorCode.VEHICLE_ALREADY_PARKED, "Vehicle Already parked");
        }
        Integer slotId = parkingStrategy.getNextFreeSlot();
        Slot slot = slotMap.get(slotId);
        slot.occupySlot(vehicle);
        regNumberSlotMap.put(vehicle.getRegistrationNumber(), slot);
        parkingStrategy.removeSlot(slotId);
        return slot;
    }

    void unOccupySlot(int slotId) throws BaseParkingLotException {
        if (!slotMap.containsKey(slotId)) {
            throw new BaseParkingLotException(ErrorCode.SLOT_NOT_FOUND, "No Such Slot found");
        }
        Slot slot = slotMap.get(slotId);
        if (!slot.isSlotOccupied()) {
            throw new BaseParkingLotException(ErrorCode.NO_VEHICLE_FOUND, "No Vehicle found on the slot");
        }
        regNumberSlotMap.remove(slot.getVehicle().getRegistrationNumber());
        slot.freeSlot();
        parkingStrategy.addSlots(slotId);
    }

    Integer getEmptySlotCount() {
        return parkingStrategy.getFreeSlotsCount();
    }

    Integer getAllSlotCount() {
        return slotMap.size();
    }

    Integer getOccupiedSlotCount() {
        return getAllSlotCount() - getEmptySlotCount();
    }

    HashMap<String, Slot> getStatus() throws BaseParkingLotException {
        if (slotMap.size() == 0) {
            throw new BaseParkingLotException(ErrorCode.SLOT_NOT_FOUND, "Slots not assigned");
        }
        if (regNumberSlotMap.size() == 0) {
            throw new BaseParkingLotException(ErrorCode.EMPTY_PARKING, "No cars Parked");
        }

        return regNumberSlotMap;

    }

    Integer getSlotNumberFromRegNumber(String regNumber) throws BaseParkingLotException {
        if (Objects.isNull(regNumber) || !regNumberSlotMap.containsKey(regNumber)) {
            throw new BaseParkingLotException(ErrorCode.NO_SUCH_VEHICLE_FOUND, "No Such Vehicle Found with given Registration Number");
        }
        Slot slot = regNumberSlotMap.get(regNumber);
        return slot.getSlotId();
    }

    public List<Integer> getSlotsNumbersForVehicleColour(String colour) throws BaseParkingLotException {
        if (Objects.isNull(colour)) {
            throw new BaseParkingLotException(ErrorCode.SLOT_NOT_FOUND, "No Such Slot Found for given colour");
        }
        List<Integer> slotList = new ArrayList<>();
        for (Map.Entry slot : slotMap.entrySet()) {
            Slot s = (Slot) slot.getValue();
            if (!Objects.isNull(s.getVehicle()) && s.getVehicle().getColour().equalsIgnoreCase(colour)) {
                slotList.add((Integer) slot.getKey());
            }
        }
        if (slotList.size() == 0) {
            throw new BaseParkingLotException(ErrorCode.SLOT_NOT_FOUND, "No Such Slot Found for given colour");
        }
        return slotList;
    }

    public List<String> getRegistrationNumberFromVehicleColour(String colour) throws BaseParkingLotException {
        if (Objects.isNull(colour)) {
            throw new BaseParkingLotException(ErrorCode.NO_SUCH_VEHICLE_FOUND, "No Such Vehicle Found for given colour");
        }
        List<String> regNumberList = new ArrayList<>();
        for (Map.Entry regNumber : regNumberSlotMap.entrySet()) {
            Slot s = (Slot) regNumber.getValue();
            if (!Objects.isNull(s.getVehicle()) && s.getVehicle().getColour().equalsIgnoreCase(colour)) {
                regNumberList.add((String) regNumber.getKey());
            }
        }
        if (regNumberList.size() == 0) {
            throw new BaseParkingLotException(ErrorCode.NO_SUCH_VEHICLE_FOUND, "No Such Vehicle Found for given colour");

        }
        return regNumberList;
    }
}
