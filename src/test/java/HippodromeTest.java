import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    void nullListHorsesException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());

    }
    @Test
    void emptyListHorsesException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());

    }
    @Test
    void getHorsesList30Horses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(" " + i, i ,i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }
    @Test
    void moveHorsesList50Horses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }
    @Test
    void getWinnerListHorse() {


        Horse horse1= new Horse("Horse1", 1.0, 1.0);
        Horse horse2=  new Horse("Horse2", 1.0, 2.0);
        Horse horse3= new Horse("Horse3",1.0,5.0);
        Horse horse4= new Horse("Horse4", 1.0, 4.0);


        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));


        assertEquals(horse3, hippodrome.getWinner());
    }
}

