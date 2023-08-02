package UnitTests;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Manager.ParkingManager;
import org.example.Model.Slot.Slot;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.assertEquals;

public class CarParkTest {
    private ParkingManager parkingManager;


    @Before
    public void setup() {
        parkingManager = new ParkingManager();
    }

    @Test
    public void test_createParkingLot() throws BaseParkingLotException {
        int size = parkingManager.createParkingLot("10");
        assertEquals(size, 10);
    }

    @Test
    public void test_carkPark() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        Slot slot = parkingManager.park("KA-05-KU-6218", "red");
        assertEquals(0, slot.getSlotId());
        assertEquals("KA-05-KU-6218", slot.getVehicle().getRegistrationNumber());
        assertEquals(9, parkingManager.getEmptySlotCount());
    }
}
