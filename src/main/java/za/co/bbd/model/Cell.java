package za.co.bbd.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Cell {
    public boolean isClicked;
    public int value;
    public boolean flag;
    public Map<Integer, Integer> position = new HashMap<Integer, Integer>();
}
