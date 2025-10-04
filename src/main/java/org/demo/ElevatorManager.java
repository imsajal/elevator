package org.demo;

import java.util.*;

import static org.demo.Type.CABIN;

public class ElevatorManager {
    private Map<String, Elevator> elevators;

    public ElevatorManager(int noOfElevators){
        elevators = new HashMap<>();
        while(noOfElevators > 0) {
            String id = UUID.randomUUID().toString();
            Elevator elevator = new Elevator(id);
            elevators.put(id, elevator);
            elevator.start();
            noOfElevators--;
        }
    }

    // handle cabin request
    // handle hall request
    public String handleRequest(Request request){
        String handlingElevatorId = null;
        switch(request.getType()){
            case CABIN:
                processCabinRequest(request);
                break;
            case HALL:
                handlingElevatorId = processHallRequest(request);
                break;
        }
        return handlingElevatorId;
    }

    private String processHallRequest(Request request) {
        // find best elevator
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        for(Elevator elevator : elevators.values()){
            if(bestElevator == null){
                bestElevator = elevator;
                minDistance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
            }
            else if(Math.abs(elevator.getCurrentFloor() - request.getFloor()) < minDistance){
             bestElevator = elevator;
            }
        }
        addRequestToElevator(bestElevator,request.getFloor());
        return bestElevator.getElevatorId();
    }


    private void addRequestToElevator(Elevator elevator, int floor) {
        elevator.addRequest(floor);
    }

    private void processCabinRequest(Request request) {
       addRequestToElevator(elevators.get(request.getElevatorId()), request.getFloor());
    }

    public void stopAllElevators(){
        for(Elevator elevator : elevators.values()){
            elevator.stopElevator();
        }
    }

    // wait and notify always inside synchronized
}
