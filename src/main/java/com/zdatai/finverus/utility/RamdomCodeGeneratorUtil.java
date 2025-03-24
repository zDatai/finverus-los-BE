package com.zdatai.finverus.utility;

import java.util.UUID;

public class RamdomCodeGeneratorUtil {
    private RamdomCodeGeneratorUtil() {}

    public static String generateCode(int from, int to) {
        return UUID.randomUUID().toString()
                .replaceAll("-", "")
                .substring(from, to)
                .toUpperCase();
    }
}
