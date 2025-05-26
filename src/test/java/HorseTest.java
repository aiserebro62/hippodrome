import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


public class HorseTest {
    @Test
     void nullNameException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", e.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "\t\t", "\n\n\n\n\n\n"})
     void blankNameException(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
     void speedNegativeException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, -1, 1));
        assertEquals("Name cannot be null.", e.getMessage());

    }

    @Test
    void distanceNegativeException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, -1));
        assertEquals("Name cannot be null.", e.getMessage());

    }

    @Test
    void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("qwerty", 1, 1);
        assertEquals("qwerty", horse.getName());

    }

    @Test
    void getSpeed() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("qwerty", 200, 1);
        assertEquals(200, horse.getSpeed());

    }

    @Test
    void getDistance() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("qwerty", 1, 200);
        assertEquals(200, horse.getDistance());

    }

    @Test
    void zeroDistanceDefault() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("qwerty", 1);
        assertEquals(0, horse.getDistance());

    }
    @Test
    void moveGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
             new Horse("qwerty", 1,2).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.5, 0.7, 0.9})
    void moveDistance(double randomValue) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);


            Horse horse = new Horse("qwerty", 2, 10);

            horse.move();
            assertEquals(10+2*randomValue, horse.getDistance());
        }
    }

}

