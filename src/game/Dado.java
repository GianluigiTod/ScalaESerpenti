package src.game;
import java.util.*;

public class Dado {
    public int roll(){
        Random rand = new Random();
        return (rand.nextInt(6))+1;
    }
}
