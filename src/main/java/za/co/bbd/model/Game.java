package za.co.bbd.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Game {
    public boolean isClicked;
    public int value;
    public Map<Integer ,Integer> position = new HashMap<Integer,Integer>();
}
