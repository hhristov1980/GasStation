package main.payDesk;

import main.cars.Car;
import main.gasStation.GasStation;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class PayDesk {
    private static int uniqueId = 1;
    private int deviceId;
    private GasStation gasStation;
    private ConcurrentHashMap<Car.FuelType,Integer> statistics;
    private BlockingQueue<Car> carQueue;

}
