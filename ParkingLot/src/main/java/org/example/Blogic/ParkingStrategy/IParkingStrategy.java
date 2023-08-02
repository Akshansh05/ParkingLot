package org.example.Blogic.ParkingStrategy;

import org.example.Blogic.Exceptions.BaseParkingLotException;

public interface IParkingStrategy {

    void addSlots(Integer slotId) throws BaseParkingLotException;

    void removeSlot(Integer slotId) throws BaseParkingLotException;

    int getNextFreeSlot() throws BaseParkingLotException;

    void setMaximumCapacity(int capacity);

    int getFreeSlotsCount();

}
