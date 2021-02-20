package main.cars;

public class Car extends Thread{
    private FuelType fuelType;
    private int putInTank;

    public Car(FuelType fuelType){
        this.fuelType = fuelType;
    }

    @Override
    public void run() {

    }

    public enum FuelType {
        DIESEL, PETROL, GAS
    }
}
