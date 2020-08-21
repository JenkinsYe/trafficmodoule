package com.csdn.xs.exhausts.trafficmodoule.mapper;


import com.csdn.xs.exhausts.trafficmodoule.domain.ProcessDomain;

import java.util.List;

/**
 * @author YJJ
 * @Date: Created in 15:12 2020-06-06
 */
public interface ProcessMapper {

    List<ProcessDomain> findProcessByTestID(String testID);

    void updateProcess(List<ProcessDomain> domains);

    List<String> findDistinctTestId();
}
