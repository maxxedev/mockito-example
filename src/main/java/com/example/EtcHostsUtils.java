package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EtcHostsUtils {

    /**
     * returns /etc/hosts mappings for the given ipAddress
     */
    public static List<String> findEtcHostsMappings(String ipAddress) throws IOException {
        Path path = Paths.get("/etc/hosts");
        String[] lineTokens = Files.readAllLines(path)
                .stream()
                .map(line -> line.split("[ \t]+", 2))
                .filter(tokens -> tokens.length > 1)
                .filter(tokens -> tokens[0].equals(ipAddress))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find mapping for " + ipAddress));

        List<String> mappings = new ArrayList<>();
        mappings.addAll(Arrays.asList(lineTokens));
        mappings.remove(0);

        return mappings;
    }
}
