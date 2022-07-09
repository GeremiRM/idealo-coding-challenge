package de.idealo.robot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Robot {
    private Coordinates coordinates;
    private Direction direction;

        public void move(String instruction) {
            instructionParser(instruction);
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
                    // handlePosition
                    break;
                case "RIGHT":
                    // handlePosition
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

            this.setCoordinates(newCoordinates);
            this.setDirection(newDirection);
        }

        private void moveForward(String instruction) {
            String regex = "FORWARD [1-3]";
            if(!instruction.matches(regex))
                return;

            String[] splitInstruction = instruction.split(" ");
            int steps = Integer.parseInt(splitInstruction[1]);

            int currentX = this.getCoordinates().getX();
            int currentY = this.getCoordinates().getY();

            switch(this.direction) {
                case NORTH:
                    this.setCoordinates(new Coordinates(currentX, currentY - steps));
                    break;
                case EAST:
                    this.setCoordinates(new Coordinates(currentX + steps, currentY));
                    break;
                case SOUTH:
                    this.setCoordinates(new Coordinates(currentX, currentY + steps ));
                    break;
                case WEST:
                    this.setCoordinates(new Coordinates(currentX - steps, currentY ));
                    break;
            }
        }

        private void moveWait(String instruction) {

        }


}
