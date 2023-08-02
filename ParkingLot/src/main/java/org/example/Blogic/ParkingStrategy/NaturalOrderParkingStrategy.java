package org.example.Blogic.ParkingStrategy;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Blogic.Exceptions.ErrorCode;

import java.util.TreeSet;

public class NaturalOrderParkingStrategy implements IParkingStrategy {

    private final TreeSet<Integer> slots;
    private int maxCapacity;

    public NaturalOrderParkingStrategy() {
        this.slots = new TreeSet<>();
    }

    @Override
    public void addSlots(Integer slotId) throws BaseParkingLotException {
        if (!validateCapacity()) {
            throw new BaseParkingLotException(ErrorCode.SLOTS_SIZE_EXCEEDED, "Slot Size Exceeded");
        }
        slots.add(slotId);
    }

    @Override
    public void removeSlot(Integer slotId) throws BaseParkingLotException {
        if (!slots.contains(slotId)) {
            throw new BaseParkingLotException(ErrorCode.SLOT_NOT_FOUND, "No such Slot Present");
        }
        slots.remove(slotId);
    }

    @Override
    public int getNextFreeSlot() throws BaseParkingLotException {
        if (slots.isEmpty()) {
            throw new BaseParkingLotException(ErrorCode.NO_FREE_SLOTS_AVAILABLE, "No Free Slots Available");
        }
        return slots.first();
    }

    @Override
    public void setMaximumCapacity(int capacity) {
        this.maxCapacity = capacity;
    }

    @Override
    public int getFreeSlotsCount() {
        return slots.size();
    }

    private boolean validateCapacity() {
        return slots.size() <= maxCapacity;
    }

}
