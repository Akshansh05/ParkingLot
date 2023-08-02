package UnitTests;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Manager.ParkingManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetSlotNumberFromRegistrationNumberTest {
    private ParkingManager parkingManager;

    @Before
    public void setup() {
        parkingManager = new ParkingManager();
    }

    @Test
    public void test_SlotNotInitialized() {
        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.getSlotNumberForRegNumber("ABCD");
        });
        String expectedMessage = "No Such Vehicle Found with given Registration Number";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_NoCarParked() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.getSlotNumberForRegNumber("ABCD");
        });
        String expectedMessage = "No Such Vehicle Found with given Registration Number";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_CarParkedWithSameRegNumber() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        parkingManager.park("KA-05-KU-6218", "Red");
        assertEquals(parkingManager.getSlotNumberForRegNumber("KA-05-KU-6218"), 0);
    }

    @Test
    public void test_CarParkedWithDifferentRegNumber() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        parkingManager.park("KA-05-KU-6218", "Red");

        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.getSlotNumberForRegNumber("ABCD");
        });
        String expectedMessage = "No Such Vehicle Found with given Registration Number";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
