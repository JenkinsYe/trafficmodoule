package com.csdn.xs.exhausts.trafficmodoule.service;

import com.csdn.xs.exhausts.trafficmodoule.domain.ProcessDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YJJ
 * @Date: Created in 15:34 2020-06-06
 */
@Service
@Slf4j
public class VSPService {

    @Autowired
    private ProcessDataService processDataService;

    public void start(String testID) {
        calculateVSPByTestID(testID);
    }

    public void calculateVSPByTestID(String testID) {
        List<ProcessDomain> domains = processDataService.findProcessByTestID(testID);
        if (domains.size() == 0)
            return;
        for (int i = 1; i < domains.size(); i++) {
            ProcessDomain end = domains.get(i);
            ProcessDomain start = domains.get(i-1);
            domains.get(i).setA(getAcceleration(end, start));
            domains.get(i).setVsp(getVSP(end.getV(), domains.get(i).getA()));
            domains.get(i).setVspBin(getVSPBin(domains.get(i).getVsp()));
        }
        processDataService.updateProcess(domains);
    }

    public Double getVSP(Double v, Double a) {
        return v * (1.1 * a + 0.132) + 0.000302 * Math.pow(v, 3);
    }

    public Double getAcceleration(ProcessDomain start, ProcessDomain end) {
        Double deltaV = end.getV() - start.getV();
        Long deltaTime = (end.getTestTime().getTime() - start.getTestTime().getTime()) / 1000;
        if (deltaV == 0 && deltaTime != 0) {
            return 0d;
        }
        if (deltaTime == 0){
            return start.getA() != null ? start.getA() : 0d;
        }

        return deltaV/deltaTime;
    }

    public Integer getVSPBin(Double vsp) {
        if (vsp < -20)
            return -21;
        if (vsp > 20)
            return 21;
        if (vsp >= 0)
            return (int)Math.ceil(vsp);
        else
            return (int)Math.floor(vsp);
    }
}
