package org.example.Model.Slot;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Blogic.Exceptions.ErrorCode;
import org.example.Model.Vehicle.Vehicle;

public class Slot {

    private final int slotId;
    private Vehicle vehicle;

    public Slot(int slotId) {
        this.slotId = slotId;
    }

    public void occupySlot(Vehicle v) throws BaseParkingLotException {
        if (vehicle != null) {
            throw new BaseParkingLotException(ErrorCode.SLOT_NOT_FREE, "Slot Already occupied");
        }
        this.vehicle = v;
    }

    public void freeSlot() throws BaseParkingLotException {
        if (vehicle == null) {
            throw new BaseParkingLotException(ErrorCode.SLOT_ALREADY_EMPTY, "Slot Already Empty");

        }
        vehicle = null;
    }

    public boolean isSlotOccupied() {
        return vehicle != null;
    }

    public int getSlotId() {
        return slotId;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }
}
