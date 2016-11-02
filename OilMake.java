package edu.aurora.oilchange;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum OilMake {
	
	SYNTHETIC,
	CONVENTIONAL,
	SYTENTHIC_BLEND,
	HIGH_MILEAGE;


 
	public static final Map<OilMake, String> oilMap;
	
    static {
        Map<OilMake, String> map = new HashMap<>();
        
        map.put(OilMake.SYNTHETIC, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");
		map.put(OilMake.CONVENTIONAL, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");
		map.put(OilMake.SYTENTHIC_BLEND, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");
		map.put(OilMake.HIGH_MILEAGE, "5W-20, 5W-30, 5W-40, 10W-20, 10W-30, 10W-40, 15W-20, 15W-30, 15W-40, 20W-20, 20W-30, 20W-40");
		
        oilMap = Collections.unmodifiableMap(map);
    }

    
}

	

