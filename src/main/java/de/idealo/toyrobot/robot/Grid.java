package de.idealo.toyrobot.robot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Grid {
    private int width;
    private int height;

    public boolean isRobotOutLimits(Robot robot) {
        int robotX = robot.getCoordinates().getX();
        int robotY = robot.getCoordinates().getY();
        return ((robotX < 0 || robotX >= this.width) || (robotY < 0 || robotY >= this.height));

    }

}
