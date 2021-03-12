package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

public class EtcHostsUtilsTest {

    @TempDir
    Path tempDir;

    @Test
    public void testFindEtcHostsMappings() throws IOException {
        Path tempFile = tempDir.resolve("test-etc-hosts.txt");
        Files.write(tempFile, asList("192.168.10.10        database-server"));

        try (MockedStatic<Paths> mocked = mockStatic(Paths.class)) {
            mocked.when(() -> Paths.get("/etc/hosts"))
                    .thenReturn(tempFile);

            assertThat(EtcHostsUtils.findEtcHostsMappings("192.168.10.10"))
                    .containsExactly("database-server");
        }
    }
}
