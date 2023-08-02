package UnitTests;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Manager.ParkingManager;
import org.example.Model.Slot.Slot;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class StatusTest {
    private ParkingManager parkingManager;

    @Before
    public void setup() {
        parkingManager = new ParkingManager();
    }


    @Test
    public void test_SlotNotInitialized() throws BaseParkingLotException {

        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.status();
        });
        String expectedMessage = "Slots not assigned";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_EmptySlots() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.status();
        });
        String expectedMessage = "No cars Parked";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_StatusWithCarParked() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        parkingManager.park("KA-05-KU-6218", "Red");
        HashMap<String, Slot> status = parkingManager.status();
        assertEquals(status.size(), 1);
    }
}
