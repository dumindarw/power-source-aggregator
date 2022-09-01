package com.vpp.psa.repository;

import com.google.common.base.Throwables;
import com.vpp.psa.exception.BatteryException;
import com.vpp.psa.generated.tables.BatteryInfo;
import com.vpp.psa.model.Battery;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.jooq.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class BatteryRepo {

    @Autowired
    private DSLContext dslContext;

    public List<Integer> createBatteries(List<Battery> batteries){

        BatteryInfo batteryInfo = BatteryInfo.BATTERY_INFO;

        return batteries.stream()
                .map(battery ->  dslContext.insertInto(batteryInfo).values(battery).execute())
                .collect(Collectors.toList());
    }

    public Integer createBattery(Battery battery){

        log.debug("{}", battery);

        AtomicInteger transactionResult = new AtomicInteger();

        try {
            dslContext.transaction(trx -> {
                BatteryInfo batteryInfo = BatteryInfo.BATTERY_INFO;

                transactionResult.set(trx.dsl().insertInto(batteryInfo)
                        .set(batteryInfo.NAME, battery.getName())
                        .set(batteryInfo.POST_CODE, battery.getPostcode())
                        .set(batteryInfo.WATT_CAPACITY, battery.getWattCapacity())
                        .execute());

                if (transactionResult.get() <= 0) {
                    throw new BatteryException("Could not create battery info");
                }

                log.debug(String.valueOf(transactionResult.get()));

            });
        }catch(RuntimeException e){
            log.error(Throwables.getStackTraceAsString(e));

            return transactionResult.get();
        }

         return transactionResult.get();
    }



    public Map<?, ?> getBatteriesByPostcodeRange(int from, int to){

        BatteryInfo batteryInfo = BatteryInfo.BATTERY_INFO;

        return  dslContext.select(batteryInfo.NAME, batteryInfo.WATT_CAPACITY)
                .from(batteryInfo)
                .where(batteryInfo.POST_CODE.between(from, to))
                .fetch()
                .intoMap(batteryInfo.NAME.getName(), batteryInfo.WATT_CAPACITY.getName());
    }

    public Battery getBatteryByName(String name) {

        BatteryInfo batteryInfo = BatteryInfo.BATTERY_INFO;

        return dslContext.select().from(batteryInfo).where(batteryInfo.NAME.eq(name))
                .fetchOptional().orElseThrow(NoDataFoundException::new).into(Battery.class);
    }
}
