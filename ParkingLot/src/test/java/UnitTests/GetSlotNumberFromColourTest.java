package UnitTests;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Manager.ParkingManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetSlotNumberFromColourTest {

    private ParkingManager parkingManager;

    @Before
    public void setup() {
        parkingManager = new ParkingManager();
    }

    @Test
    public void test_SlotNotInitialized() {
        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.getSlotsNumbersForVehicleColour("Red");
        });
        String expectedMessage = "No Such Slot Found for given colour";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_NoCarParked() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.getSlotsNumbersForVehicleColour("Red");
        });
        String expectedMessage = "No Such Slot Found for given colour";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_CarParkedWithSameColour() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        parkingManager.park("KA-05-KU-6218", "Red");
        assertEquals(parkingManager.getRegistrationNumbersForVehicleColour("Red").size(), 1);
    }

    @Test
    public void test_CarParkedWithDifferentColour() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        parkingManager.park("KA-05-KU-6218", "Red");

        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.getSlotsNumbersForVehicleColour("Black");
        });
        String expectedMessage = "No Such Slot Found for given colour";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}

