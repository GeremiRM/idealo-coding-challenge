package de.idealo.toyrobot;

import de.idealo.toyrobot.robot.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class RobotController {
    Coordinates DEFAULT_COORDINATES = new Coordinates(0, 0);
    Direction DEFAULT_DIRECTION = Direction.EAST;

    Robot robot = new Robot(DEFAULT_COORDINATES, DEFAULT_DIRECTION);

    @PostMapping("/robot")
    public Robot moveRobot(@RequestBody MovementRequest movementRequest) {
        robot.move(movementRequest.instructions);

        if(movementRequest.grid.isRobotOutLimits(robot)) {
            // Reset robot
            robot = new Robot(DEFAULT_COORDINATES, DEFAULT_DIRECTION);
        }
        return robot;
    }
}
