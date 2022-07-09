package de.idealo.robot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RobotController {

    @PostMapping("/robot")
    public String RobotMovement(@RequestBody List<String> instructions) {
        return "Hello World";
    }
}
