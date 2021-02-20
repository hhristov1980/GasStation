package main.fuelLoaderDevice;

import main.cars.Car;
import main.gasStation.GasStation;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class FuelDispenser {
    private static int uniqueId = 1;
    private String deviceId;
    private GasStation gasStation;
    private ConcurrentHashMap<Car.FuelType,Integer> statistics;
    private BlockingQueue<Car> carQueue;
    private int currentLitters;


    public FuelDispenser(GasStation gasStation){
        this.deviceId = "Device "+uniqueId++;
        this.gasStation = gasStation;
        statistics = new ConcurrentHashMap<>();
        carQueue = new LinkedBlockingQueue<>();
        statistics.put(Car.FuelType.PETROL,0);
        statistics.put(Car.FuelType.DIESEL,0);
        statistics.put(Car.FuelType.GAS,0);
    }

    public BlockingQueue<Car> getCarQueue() {
        return carQueue;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setCurrentLitters(int currentLitters) {
        if(currentLitters>0){
            this.currentLitters = currentLitters;
        }
    }
}
