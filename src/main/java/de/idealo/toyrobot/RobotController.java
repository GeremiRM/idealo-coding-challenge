package de.idealo.toyrobot;

import de.idealo.toyrobot.robot.Coordinates;
import de.idealo.toyrobot.robot.Direction;
import de.idealo.toyrobot.robot.Robot;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RobotController {
    Coordinates DEFAULT_COORDINATES = new Coordinates(0, 0);
    Direction DEFAULT_DIRECTION = Direction.EAST;

    Robot robot = new Robot(DEFAULT_COORDINATES, DEFAULT_DIRECTION);

    @PostMapping("/robot")
    public Robot moveRobot(@RequestBody List<String> instructions) {
        robot.move(instructions);
        return robot;
    }
}
