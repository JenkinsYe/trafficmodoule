package com.csdn.xs.exhausts.trafficmodoule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author YJJ
 * @Date: Created in 15:04 2020-06-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDomain {

    private Long id;

    private String testID;

    private Date testTime;

    private Double v;

    private Double a;

    private Double vsp;

    private Integer vspBin;

    private Double no;

    private Double co;

    private Double hc;
}
