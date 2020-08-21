package com.csdn.xs.exhausts.trafficmodoule.controller;

import com.csdn.xs.exhausts.trafficmodoule.service.ProcessDataService;
import com.csdn.xs.exhausts.trafficmodoule.service.VSPService;
import com.csdn.xs.exhausts.trafficmodoule.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YJJ
 * @Date: Created in 10:38 2020-06-18
 */
@RestController
@Slf4j
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    ProcessDataService processDataService;

    @Autowired
    VSPService vspService;

    @GetMapping("/api/getWeather")
    public String test() {
        try {
            weatherService.getWeather();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/api/test")
    public void batchTest() {
            //HashSet<String> set = CSVUtils.read();
            List<String> list = processDataService.findDistinctTestID();
            int i = 1;
            for (String s : list) {
                vspService.start(s);
                log.info("No." + i + " Completed");
                i++;
            }
    }

}
