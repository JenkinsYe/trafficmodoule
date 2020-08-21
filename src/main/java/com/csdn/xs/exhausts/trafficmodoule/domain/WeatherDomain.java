package com.csdn.xs.exhausts.trafficmodoule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author YJJ
 * @Date: Created in 12:12 2020-06-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDomain {

    private Long id;

    private String city;

    private String weather;

    private Double temperature;

    private String windDirection;

    private String windPower;

    private Double humidity;

    private Date reportTime;
}
