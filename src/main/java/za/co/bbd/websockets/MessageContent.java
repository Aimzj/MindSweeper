package za.co.bbd.websockets;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class MessageContent {
    public String playerName;
    public Float gameTime;
}