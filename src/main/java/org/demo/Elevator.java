package org.demo;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class Elevator extends Thread {
    private String id;
    private int currentFloor;
    private Direction direction;
    private PriorityBlockingQueue<Integer> upQueue;
    private PriorityBlockingQueue<Integer> downQueue;
    private boolean isRunning;

    public Elevator(String id) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.upQueue = new PriorityBlockingQueue<>(); // min-heap for UP
        this.downQueue = new PriorityBlockingQueue<>(11, Comparator.reverseOrder()); // max-heap for DOWN
        this.isRunning = true;
    }

    public String getElevatorId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // add req
    public synchronized void addRequest(int floor){
        if(floor > currentFloor){
            upQueue.add(floor);
        }
        else if(floor < currentFloor) {
            downQueue.add(floor);
        }
        else {
            System.out.println("Elevator " + getElevatorId() + " at same floor opening doors immediately");
        }
        notifyAll();
    }

    @Override
    public void run(){
        // pick from queue execute or wait
        while(isRunning){
           synchronized (this) {
               if (upQueue.isEmpty() && downQueue.isEmpty()) {
                   try {
                       direction = Direction.IDLE;
                       wait();
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
           }
           while(!upQueue.isEmpty()){
               direction = Direction.UP;
               moveToFloor(upQueue.poll());
           }
           while(!downQueue.isEmpty()){
               direction = Direction.DOWN;
               moveToFloor(downQueue.poll());
           }
        }
    }

    private void moveToFloor(Integer floor) {
        while(currentFloor != floor){
            if(floor > currentFloor){
                currentFloor++;
            }
            else currentFloor--;
        }
        System.out.println("Elevator " + getElevatorId() + " Reached Floor " + floor);
    }

    public void stopElevator() {
        isRunning = false;
        synchronized (this) {
            notifyAll();
        }
    }

}
