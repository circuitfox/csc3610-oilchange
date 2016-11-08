package edu.aurora.oilchange;

import java.util.*;
import java.util.stream.Collectors;

public enum OilType {
    SYNTHETIC,
    CONVENTIONAL,
    SYNTHETIC_BLEND,
    HIGH_MILEAGE;

    public static final Map<OilType, String> oilMap;

    static {
        Map<OilType, String> map = new HashMap<>();

        map.put(OilType.SYNTHETIC, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");
        map.put(OilType.CONVENTIONAL, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");
        map.put(OilType.SYNTHETIC_BLEND, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");
        map.put(OilType.HIGH_MILEAGE, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");

        oilMap = Collections.unmodifiableMap(map);
    }

    public static OilType fromString(String s) {
        String type = s.trim().replace(" ", "_");
        for (OilType o : values()) {
            if (type.equalsIgnoreCase(o.name())) {
                return o;
            }
        }
        throw new IllegalArgumentException("Value " + s + "not an oil type");
    }

    public static String[] stringValues() {
        return Arrays.stream(values()).map(OilType::toString).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return Arrays.stream(name().split("_"))
                .map(s -> Character.toTitleCase(s.charAt(0)) + s.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
