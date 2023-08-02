package UnitTests;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Manager.ParkingManager;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class GetRegistrationNumberFromColourTest {

    private ParkingManager parkingManager;

    @Before
    public void setup() {
        parkingManager = new ParkingManager();
    }

    @Test
    public void test_SlotNotInitialized() {
        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.getRegistrationNumbersForVehicleColour("White");
        });
        String expectedMessage = "No Such Vehicle Found for given colour";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    public void test_NoCarParked() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.getRegistrationNumbersForVehicleColour("White");
        });
        String expectedMessage = "No Such Vehicle Found for given colour";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_carParkedForSameColour() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        parkingManager.park("KA-05-KU6218", "White");
        assertEquals(parkingManager.getRegistrationNumbersForVehicleColour("White").size(), 1);
    }

    @Test
    public void test_carParkedForDifferentColour() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        parkingManager.park("KA-05-KU6218", "White");

        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.getRegistrationNumbersForVehicleColour("Black");
        });
        String expectedMessage = "No Such Vehicle Found for given colour";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
