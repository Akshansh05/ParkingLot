package UnitTests;

import org.example.Blogic.Exceptions.BaseParkingLotException;
import org.example.Manager.ParkingManager;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class LeaveCarParkingTest {

    private ParkingManager parkingManager;

    @Before
    public void setup() {
        parkingManager = new ParkingManager();
    }

    @Test
    public void test_leaveEmptySlot() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        Exception exception = assertThrows(BaseParkingLotException.class, () -> {
            parkingManager.unPark("0");
        });
        String expectedMessage = "No Vehicle found on the slot";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_leaveSlotOccupied() throws BaseParkingLotException {
        parkingManager.createParkingLot("10");
        parkingManager.park("KA-05-KU-6218", "Red");
        parkingManager.unPark("0");
        assertEquals(10, parkingManager.getEmptySlotCount());
    }
}
