package main.cars;

import main.fuelLoaderDevice.FuelDispenser;
import main.gasStation.GasStation;
import main.util.Constants;

public class Car extends Thread{
    private static int uniqieId = 1;
    private int carId;
    private FuelType fuelType;
    private int littersToPutInTank;
    private GasStation gasStation;
    private FuelDispenser fuelDispenser;
    private boolean refueled;
    private boolean paid;
    private double bill;

    public Car(FuelType fuelType,GasStation gasStation){
        this.fuelType = fuelType;
        this.gasStation = gasStation;
        carId = uniqieId++;
    }

    @Override
    public void run() {
        gasStation.goToFuelDispenser(this);
        gasStation.goToPay(this);
        System.out.println("Car "+carId+" was refueled with "+littersToPutInTank+" litters of "+fuelType+" for "+bill);
        System.out.println("Car "+carId+" left "+gasStation.getName());

    }

    public enum FuelType {
        DIESEL (Constants.DIESEL_PRICE), PETROL (Constants.PETROL_PRICE), GAS (Constants.GAS_PRICE);
        double price;

        FuelType(double price){
            this.price = price;
        }

    }

    public FuelType getFuelType() {
        return fuelType;
    }


    public void addFuel(int litters){
        if(litters>0){
            littersToPutInTank=litters;
            refueled = true;
            bill = Math.round(litters*fuelType.price);
        }
    }

    public boolean isRefueled() {
        return refueled;
    }

    public int getCarId() {
        return carId;
    }

    public int getLittersToPutInTank() {
        return littersToPutInTank;
    }

    public double getBill() {
        return bill;
    }

    public void setFuelDispenser(FuelDispenser fuelDispenser) {
        this.fuelDispenser = fuelDispenser;
    }

    public FuelDispenser getFuelDispenser() {
        return fuelDispenser;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
