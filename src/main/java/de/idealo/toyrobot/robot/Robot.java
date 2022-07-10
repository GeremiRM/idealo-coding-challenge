package de.idealo.toyrobot.robot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Robot {
    private Coordinates coordinates;
    private Direction direction;

    public void move(List<String> instructions) {
        instructions.forEach(this::movementInstruction);
    }

    private void movementInstruction(String instruction) {
        String instructionType = instruction.toUpperCase().split(" ")[0];

        switch(instructionType) {
            case "POSITION" -> this.movePosition(instruction);
            case "FORWARD" -> this.moveForward(instruction);
            case "TURNAROUND" -> this.moveTurnAround(instruction);
            case "RIGHT" -> this.moveRight(instruction);
            case "WAIT" -> this.moveWait();
            default -> throw new IllegalArgumentException("Wrong instruction. Read the available instructions carefully and try again");
        }
    }

    private void movePosition(String instruction) {
        // e.g POSITION 1 3 EAST
        String regex = "POSITION \\d+ \\d+ [A-Z]+";
        if(!instruction.matches(regex))
            throw new IllegalArgumentException("The instructions have to be in the written in the following way: POSITION [coord x] [coord y] [direction]");

        String[] splitInstruction = instruction.split(" ");

        int x = Integer.parseInt(splitInstruction[1]);
        int y = Integer.parseInt(splitInstruction[2]);
        Coordinates newCoordinates = new Coordinates(x,y);

        Direction newDirection = Direction.valueOf(splitInstruction[3]);

        this.setCoordinates(newCoordinates);
        this.setDirection(newDirection);
    }


    private void moveForward(String instruction) {
        // e.g FORWARD 1
        String regex = "FORWARD [1-3]";
        if(!instruction.matches(regex))
            throw new IllegalArgumentException("The instructions have to be in the written in the following way: FORWARD [number of steps]");

        String[] splitInstruction = instruction.split(" ");
        int moves = Integer.parseInt(splitInstruction[1]);

        int currentX = this.getCoordinates().getX();
        int currentY = this.getCoordinates().getY();

        switch(this.getDirection()) {
            // Minus to Y
            case NORTH -> this.setCoordinates(new Coordinates(currentX, currentY - moves));
            // Plus to X
            case EAST -> this.setCoordinates(new Coordinates(currentX + moves, currentY));
            // Plus to Y
            case SOUTH -> this.setCoordinates(new Coordinates(currentX, currentY + moves ));
            // Minus to X
            case WEST -> this.setCoordinates(new Coordinates(currentX - moves, currentY ));
        }
    }

    private void moveTurnAround (String instruction) {
        switch(this.getDirection()) {
            case NORTH -> this.setDirection(Direction.SOUTH);
            case EAST -> this.setDirection(Direction.WEST);
            case WEST -> this.setDirection(Direction.EAST);
            case SOUTH -> this.setDirection(Direction.NORTH);
        }
    }

    private void moveRight (String instruction) {
        switch(this.getDirection()) {
            case NORTH -> this.setDirection(Direction.EAST);
            case EAST -> this.setDirection(Direction.SOUTH);
            case SOUTH -> this.setDirection(Direction.WEST);
            case WEST -> this.setDirection(Direction.NORTH);
        }
    }

    private void moveWait () {}
}
