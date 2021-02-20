package main.payDesk;

import main.cars.Car;
import main.gasStation.GasStation;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class PayDesk {
    private static int uniqueId = 1;
    private String deviceId;
    private GasStation gasStation;
    private ConcurrentHashMap<Car.FuelType,Integer> statistics;
    private double money;
    private BlockingQueue<Car> carQueue;

    public PayDesk(GasStation gasStation){
        this.gasStation = gasStation;
        deviceId = "Pay desk "+uniqueId++;
        statistics = new ConcurrentHashMap<>();
        carQueue = new LinkedBlockingQueue<>();
    }

    public BlockingQueue<Car> getCarQueue() {
        return carQueue;
    }

    public ConcurrentHashMap<Car.FuelType, Integer> getStatistics() {
        return statistics;
    }

    public void addMoney(double money){
        if(money>0){
            this.money = money;
        }
    }
}
