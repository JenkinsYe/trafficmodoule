package com.csdn.xs.exhausts.trafficmodoule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author YJJ
 * @Date: Created in 16:24 2020-06-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDomain {

    private Long id;

    private String channelName;

    private String channelID;

    private Date time;

    private Integer amount;

    private String typeAndNum;

}
