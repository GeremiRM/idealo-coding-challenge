package de.idealo.robot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RobotMovement {
    private Robot robot;

    public void move(List<String> instructions) {
        instructions.stream().forEach((instruction) -> instructionParser(instruction));
    }

    private void instructionParser(String instruction) {
        String[] splitInstruction = instruction.toUpperCase().split(" ");
        switch(splitInstruction[0]) {
            case "POSITION":
                this.movePosition(instruction);
                break;
            case "FORWARD":
                this.moveForward(instruction);
                break;
            case "WAIT":
                // do nothing
                break;
            case "TURNAROUND":
                this.moveTurnAround(instruction);
                break;
            case "RIGHT":
                this.moveRight(instruction);
                break;
        }
    }

    private void movePosition(String instruction) {
        // e.g POSITION 1 3 EAST
        String regex = "POSITION [1-9]+ [1-9]+ [A-Z]+";
        if(!instruction.matches(regex))
            return;

        String[] splitInstruction = instruction.split(" ");

        int x = Integer.parseInt(splitInstruction[1]);
        int y = Integer.parseInt(splitInstruction[2]);
        Coordinates newCoordinates = new Coordinates(x,y);

        Direction newDirection = Direction.valueOf(splitInstruction[3]);

        robot.setCoordinates(newCoordinates);
        robot.setDirection(newDirection);
    }

    private void moveForward(String instruction) {
        String regex = "FORWARD [1-3]";
        if(!instruction.matches(regex))
            return;

        String[] splitInstruction = instruction.split(" ");
        int steps = Integer.parseInt(splitInstruction[1]);

        int currentX = robot.getCoordinates().getX();
        int currentY = robot.getCoordinates().getY();

        switch(robot.getDirection()) {
            case NORTH:
                robot.setCoordinates(new Coordinates(currentX, currentY - steps));
                break;
            case EAST:
                robot.setCoordinates(new Coordinates(currentX + steps, currentY));
                break;
            case SOUTH:
                robot.setCoordinates(new Coordinates(currentX, currentY + steps ));
                break;
            case WEST:
                robot.setCoordinates(new Coordinates(currentX - steps, currentY ));
                break;
        }
    }

    private void moveTurnAround (String instruction) {
        if(!instruction.equals("TURNAROUND"))
            return;

        switch(robot.getDirection()) {
            case NORTH:
                robot.setDirection(Direction.SOUTH);
                break;
            case EAST:
                robot.setDirection(Direction.WEST);
                break;
            case WEST:
                robot.setDirection(Direction.EAST);
                break;
            case SOUTH:
                robot.setDirection(Direction.NORTH);
                break;

        }
    }

    private void moveRight (String instruction) {
        if(!instruction.equals("RIGHT"))
            return;

        switch(robot.getDirection()) {
            case NORTH:
                robot.setDirection(Direction.EAST);
                break;
            case EAST:
                robot.setDirection(Direction.SOUTH);
                break;
            case SOUTH:
                robot.setDirection(Direction.WEST);
                break;
            case WEST:
                robot.setDirection(Direction.NORTH);
                break;

        }

    }
}
