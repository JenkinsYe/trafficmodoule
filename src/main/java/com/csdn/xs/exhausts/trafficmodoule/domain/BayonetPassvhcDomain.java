package com.csdn.xs.exhausts.trafficmodoule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author YJJ
 * @Date: Created in 14:13 2020-06-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BayonetPassvhcDomain {

    private String id;

    private String deviceID;

    private String channelID;

    private String deviceName;

    private String channelName;

    private String vhcplateType;

    private String dir;

    private String laneID;

    private String currTime;

    private String channelNum;

    private String speed;

    private String osTime;

    private String ds;

    private Date writeTime;

}
