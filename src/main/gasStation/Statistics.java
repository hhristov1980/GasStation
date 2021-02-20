package main.gasStation;

import main.cars.Car;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Statistics extends Thread{
    private GasStation gasStation;
    private ConcurrentHashMap<String, ConcurrentHashMap<Car.FuelType,
            CopyOnWriteArrayList<ConcurrentHashMap<Integer, LocalDateTime>>>> generalStatistics;

    public Statistics(GasStation gasStation){
        this.gasStation = gasStation;
        generalStatistics = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, ConcurrentHashMap<Car.FuelType,
            CopyOnWriteArrayList<ConcurrentHashMap<Integer, LocalDateTime>>>> getGeneralStatistics() {
        return generalStatistics;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printStatistics();
        }


    }
    private void printStatistics(){
        System.out.println("================ STATISTICS ===============");
        for(Map.Entry<String, ConcurrentHashMap<Car.FuelType,
                CopyOnWriteArrayList<ConcurrentHashMap<Integer, LocalDateTime>>>> general: generalStatistics.entrySet()){
            System.out.println(general.getKey()+":");
            for(Map.Entry<Car.FuelType, CopyOnWriteArrayList<ConcurrentHashMap<Integer, LocalDateTime>>> type: general.getValue().entrySet()){
                System.out.println("Fuel type: "+type.getKey());
                for(int i = 0; i<type.getValue().size(); i++){
                    if(!type.getValue().get(i).isEmpty()){
                        System.out.println(type.getValue().get(i).entrySet());
                    }

                }
            }
        }
        System.out.println("================ END OF STATISTICS ===============");
    }


}
