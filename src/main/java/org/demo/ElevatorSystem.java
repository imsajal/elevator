package org.demo;


public class ElevatorSystem {
    public static void main(String[] args) throws InterruptedException {

        ElevatorManager elevatorManager = new ElevatorManager(2);
        String elevatorId1 = elevatorManager.handleRequest(new Request(5, Direction.DOWN, Type.HALL, null));
        Thread.sleep(1000);
        elevatorManager.handleRequest(new Request(4, null, Type.CABIN, elevatorId1));

        String elevatorId2 = elevatorManager.handleRequest(new Request(1, Direction.UP, Type.HALL, null));
        Thread.sleep(1000);
        elevatorManager.handleRequest(new Request(9, null, Type.CABIN, elevatorId2));

        String elevatorId3 = elevatorManager.handleRequest(new Request(11, Direction.DOWN, Type.HALL, null));
        elevatorManager.handleRequest(new Request(1, null, Type.CABIN, elevatorId3));
        Thread.sleep(5000);
        elevatorManager.stopAllElevators();

    }
}