package org.demo;

public class Request {
   private  int floor;
   private Direction direction;
   private Type type;
   private String elevatorId;

    public Request(int floor, Direction direction, Type type, String elevatorId) {
        this.floor = floor;
        this.direction = direction;
        this.type = type;
        this.elevatorId = elevatorId;
    }

    public String getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(String elevatorId) {
        this.elevatorId = elevatorId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
