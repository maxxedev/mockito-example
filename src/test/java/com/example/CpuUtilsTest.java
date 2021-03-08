package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class CpuUtilsTest {

    @Test
    public void testGetIdleCpuCount() {
        OperatingSystemMXBean mockOsMxBean = mock(OperatingSystemMXBean.class);
        doReturn(10.0).when(mockOsMxBean).getSystemLoadAverage();
        doReturn(16).when(mockOsMxBean).getAvailableProcessors();

        try (MockedStatic<ManagementFactory> mocked = mockStatic(ManagementFactory.class)) {
            mocked.when(() -> ManagementFactory.getOperatingSystemMXBean())
                    .thenReturn(mockOsMxBean);

            assertThat(CpuUtils.getIdleCpuCount()).isEqualTo(6);
        }
    }

}
