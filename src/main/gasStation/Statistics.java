package main.gasStation;

import main.cars.Car;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Statistics extends Thread{
    private GasStation gasStation;
    private ConcurrentHashMap<String, ConcurrentHashMap<Car.FuelType, CopyOnWriteArrayList<ConcurrentHashMap<Integer, LocalDateTime>>>> generalStatistics;

    public Statistics(GasStation gasStation){
        this.gasStation = gasStation;
        generalStatistics = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, ConcurrentHashMap<Car.FuelType, CopyOnWriteArrayList<ConcurrentHashMap<Integer, LocalDateTime>>>> getGeneralStatistics() {
        return generalStatistics;
    }
}
