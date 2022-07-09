package de.idealo.robot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RobotController {

    Robot robot = new Robot(new Coordinates(0, 0), Direction.EAST);
    RobotMovement robotMovement = new RobotMovement(robot);

    @PostMapping("/robot")
    public Robot MoveRobot(@RequestBody List<String> instructions) {
        robotMovement.move(instructions);
        return robot;
    }
}
