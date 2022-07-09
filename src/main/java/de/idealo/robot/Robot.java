package de.idealo.robot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Robot {
    private Coordinates coordinates;
    private Direction direction;
}
