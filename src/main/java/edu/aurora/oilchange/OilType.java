package edu.aurora.oilchange;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum OilType {
    SYNTHETIC,
    CONVENTIONAL,
    SYTENTHIC_BLEND,
    HIGH_MILEAGE;

    public static final Map<OilType, String> oilMap;

    static {
        Map<OilType, String> map = new HashMap<>();

        map.put(OilType.SYNTHETIC, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");
        map.put(OilType.CONVENTIONAL, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");
        map.put(OilType.SYTENTHIC_BLEND, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");
        map.put(OilType.HIGH_MILEAGE, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");

        oilMap = Collections.unmodifiableMap(map);
    }

    public static String[] stringValues() {
        return Arrays.stream(values()).map(OilType::toString).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return Character.toTitleCase(name().charAt(0)) + name().substring(1).toLowerCase();
    }
}
