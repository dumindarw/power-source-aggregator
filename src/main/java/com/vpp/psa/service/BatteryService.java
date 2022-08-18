package com.vpp.psa.service;

import com.vpp.psa.model.Battery;
import com.vpp.psa.repository.BatteryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BatteryService {

    @Autowired
    BatteryRepo batteryRepo;

    public Map<String, Integer> createBatteries(List<Battery> batteries){

        int requestCount = batteries.size();

        int successCount =  batteries.stream()
                .filter(battery ->
                        battery.getCapacity() > 0 &&
                                battery.getName() != null &&
                                battery.getPostcode() > 0)
                        .map(battery -> batteryRepo.createBattery(battery))
                        .reduce(0, Integer::sum);

        return Map.of("sent", requestCount, "accepted", successCount);

    }

    public Map<String,Object> getBatteriesByPostcodeRange(int from, int to){

        Map<String,Double> batteries = batteryRepo.getBatteriesByPostcodeRange(from, to)
                .entrySet().stream()
                .collect(Collectors.toMap(entry -> (String)entry.getKey(), entry -> (Double)entry.getValue(), (oldValue, newValue) -> newValue, TreeMap::new));

        DoubleSummaryStatistics summery = batteries
                .values()
                .stream()
                .mapToDouble(value ->  value)
                .summaryStatistics();

        return  Map.of("batteries", batteries.keySet(),
                "sum", summery.getSum(),
                "average", summery.getAverage());
    }
}
