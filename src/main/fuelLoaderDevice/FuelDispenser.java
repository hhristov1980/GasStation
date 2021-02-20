package main.fuelLoaderDevice;

import main.cars.Car;
import main.gasStation.GasStation;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class FuelLoaderDevice {
    private static int uniqueId = 1;
    private String deviceId;
    private GasStation gasStation;
    private ConcurrentHashMap<Car.FuelType,Integer> statistics;
    private BlockingQueue<Car> carQueue;


    public FuelLoaderDevice(GasStation gasStation){
        this.deviceId = "Device "+uniqueId++;
        this.gasStation = gasStation;
        statistics = new ConcurrentHashMap<>();
        carQueue = new LinkedBlockingQueue<>();
        statistics.put(Car.FuelType.PETROL,0);
        statistics.put(Car.FuelType.DIESEL,0);
        statistics.put(Car.FuelType.GAS,0);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelLoaderDevice that = (FuelLoaderDevice) o;
        return deviceId == that.deviceId && gasStation.equals(that.gasStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, gasStation);
    }
}
