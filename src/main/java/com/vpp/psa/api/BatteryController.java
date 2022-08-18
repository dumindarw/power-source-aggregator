package com.vpp.psa.api;

import com.vpp.psa.model.Battery;
import com.vpp.psa.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BatteryController {

    @Autowired
    BatteryService batteryService;

    @PostMapping
    public ResponseEntity addBatteries(@RequestBody List<Battery> batteries){

        Map<String, Integer> batteryCounts = batteryService.createBatteries(batteries);

        int createdCount = batteryCounts.get("accepted");
        int sentCount = batteryCounts.get("sent");

        return createdCount == sentCount ?
                ResponseEntity.ok().body(batteryCounts) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(batteryCounts);

    }

    @GetMapping
    public ResponseEntity getBatteries(@RequestParam int postCodeFrom, @RequestParam int postCodeTo){

        return ResponseEntity.ok().body(batteryService.getBatteriesByPostcodeRange(postCodeFrom, postCodeTo));
    }
}
