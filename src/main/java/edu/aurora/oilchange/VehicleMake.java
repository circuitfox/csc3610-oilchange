package edu.aurora.oilchange;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum VehicleMake {
    ACURA,
    AUDI,
    BENTLEY,
    BMW,
    BUICK,
    CADILLAC,
    CHEVROLET,
    CHRYSLER,
    DODGE,
    FORD,
    HONDA,
    HYUNDAI,
    INFINITI,
    JEEP,
    KIA,
    LEXUS,
    MAZDA,
    MINI,
    MITSUBISHI,
    NISSAN,
    PORSCHE,
    SUBARU,
    SUZUKI,
    TESLA,
    TOYOTA,
    VOLKSWAGEN,
    VOLVO,
    OTHER;

    // Statically initialize the map.
    // TODO: Oil map
    public static final Map<VehicleMake, String> vehicleMap;

    static {
        Map<VehicleMake, String> map = new HashMap<>();

        map.put(VehicleMake.ACURA, "Legend, MDX, RDX, RLX, TSX");
        map.put(VehicleMake.AUDI, " A2, DKW Monza, RS2, TT, Quatro");
        map.put(VehicleMake.BENTLEY, "Arnage, Azure, Brooklyns, Continental Flying Spur, Continental GT,");
        map.put(VehicleMake.BMW, "3 Series, M3, M5, X3, Z4");
        map.put(VehicleMake.BUICK, "Enclave, Grand Sport, Lacrosse, LeSabre, Wildcat");
        map.put(VehicleMake.CADILLAC, "Cimarron, CTS, CTS-V, Escalade, Fleetwood");
        map.put(VehicleMake.CHEVROLET, "Camaro, Chevette, Corvette, Tahoe, Volt");
        map.put(VehicleMake.CHRYSLER, "300, Crossfire,  PT Cruiser, Town and Country, Voyager");
        map.put(VehicleMake.DODGE, " Avenger, Challenger, Caravan, Charger, Dart,");
        map.put(VehicleMake.FORD, "Falcon, Fiesta, Focus, Fusion, GT");
        map.put(VehicleMake.HONDA, "Acord, Civic, CR-V, Fit, Odyssey");
        map.put(VehicleMake.HYUNDAI, "Accent, Elantra, Santa Fe, Sonata, Tucson");
        map.put(VehicleMake.INFINITI, "G20, G25, I30, IPLQ50, J30");
        map.put(VehicleMake.JEEP, "Compass, Grand Cherokee, Liberty, Patriot, Wrangler");
        map.put(VehicleMake.KIA, "Forte, Sportage, Optima, Rio, Soul");
        map.put(VehicleMake.LEXUS, "CT, ES, GS, HS, IS");
        map.put(VehicleMake.MAZDA, "Bongo, CX-3, CX-5, CX-9, Flair");
        map.put(VehicleMake.MINI, "Cooper, Clubman, Mark 2, Mark 3");
        map.put(VehicleMake.MITSUBISHI, "Carisma, Galant, Lancer, Lancer Evolution, Outlander");
        map.put(VehicleMake.NISSAN, "370Z, Altima, Cube, GT-R, Rogue");
        map.put(VehicleMake.PORSCHE, "911, 911 Classic, Boxster, Carrera GT, Cayman");
        map.put(VehicleMake.SUBARU, "Baja, Forester, Impreza, Legacy, Outback");
        map.put(VehicleMake.SUZUKI, "Equator, Escudo, SJ, Wagon R, X-90");
        map.put(VehicleMake.TESLA, "Model 3, Model S, Model X, Roadster");
        map.put(VehicleMake.TOYOTA, "86, 4Runner, Agya, Camara, Highlander");
        map.put(VehicleMake.VOLKSWAGEN, "BW, Jetta, Transporter, Type 2, Type 82");
        map.put(VehicleMake.VOLVO, "140 Series, 164, 200 Series, 300 Series, 440-460");

        vehicleMap = Collections.unmodifiableMap(map);
    }

    public static boolean hasModel(VehicleMake make, String model) {
        String regex = "\\b" + model + "\\b";
        String modelName = vehicleMap.get(make);
        return modelName != null && modelName.matches(regex);
    }

    public static VehicleMake fromString(String s) {
        for (VehicleMake v : values()) {
            if (s.equalsIgnoreCase(v.name())) {
                return v;
            }
        }
        return VehicleMake.OTHER;
    }

    public static String[] stringValues() {
        return Arrays.stream(values()).map(VehicleMake::toString).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return Character.toTitleCase(name().charAt(0)) + name().substring(1).toLowerCase();
    }
}
