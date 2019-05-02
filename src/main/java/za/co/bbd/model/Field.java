package za.co.bbd.model;

import lombok.Data;

@Data
public class Field {
    public String element;
    public int x;
    public int y;
    public boolean isFlagged;
    public boolean isRevealed;
    public boolean isMine;
    public boolean isEmpty;
}
