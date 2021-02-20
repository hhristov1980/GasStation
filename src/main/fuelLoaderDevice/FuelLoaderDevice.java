package main.fuelLoaderDevice;

import main.cars.Car;
import main.gasStation.GasStation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class FuelLoaderDevice {
    private static int uniqueId = 1;
    private int deviceId;
    private GasStation gasStation;
    private ConcurrentHashMap<Car.FuelType,Integer> statistics;
    private BlockingQueue<Car> carQueue;


    public FuelLoaderDevice(GasStation gasStation){
        this.deviceId = uniqueId++;
        this.gasStation = gasStation;
        statistics = new ConcurrentHashMap<>();
        carQueue = new LinkedBlockingQueue<>();
        statistics.put(Car.FuelType.PETROL,0);
        statistics.put(Car.FuelType.DIESEL,0);
        statistics.put(Car.FuelType.GAS,0);
    }


}
