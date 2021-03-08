package com.example;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class CpuUtils {

    public static int getIdleCpuCount() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        double availableProcessors = operatingSystemMXBean.getAvailableProcessors();
        double averageLoad = operatingSystemMXBean.getSystemLoadAverage();
        int idleProcessors = (int) (availableProcessors - averageLoad);
        if (idleProcessors <= 0) {
            return 1;
        }
        return idleProcessors <= 0 ? 1 : idleProcessors;
    }
}
