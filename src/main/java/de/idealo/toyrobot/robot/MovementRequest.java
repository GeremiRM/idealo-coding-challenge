package de.idealo.toyrobot.robot;

import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class MovementRequest {
    public List<String> instructions;
    public Grid grid;
}
