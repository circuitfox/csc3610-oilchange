package edu.aurora.oilchange;

import java.util.HashMap;

public class VehicleMake {
	
	enum Make{
		
		ACURA, AUDI, BENTLEY, BMW, BUICK, CADILLAC, CHEVROLET, CHRYSLER, DODGE, FORD,
		HONDA, HYUNDAI, INFINITI, JEEP, KIA, LEXUS, MAZDA, MINI,
		MITSUBUSHI, NISSAN, PORSCHE, SUBARU, SUZUKI, TESLA, TOYOTA, VOLKSWAGEN, VOLVO;
		
		
	
	}



 public HashMap<Make, String> generateMap() {
	 
	 HashMap<Make, String> map = new HashMap<Make, String>(); 
	 
	 map.put(Make.ACURA, "Legend, MDX, RDX, RLX, TSX");
	 map.put(Make.AUDI, " A2, DKW Monza, RS2, TT, Quatro");
	 map.put(Make.BENTLEY, "Arnage, Azure, Brooklyns, Continental Flying Spur, Continental GT,");
	 map.put(Make.BMW, "3 Series, M3, M5, X3, Z4");
	 map.put(Make.BUICK, "Enclave, Grand Sport, Lacrosse, LeSabre, Wildcat");
	 map.put(Make.CADILLAC, "Cimarron, CTS, CTS-V, Escalade, Fleetwood");
	 map.put(Make.CHEVROLET, "Camaro, Chevette, Corvette, Tahoe, Volt");
	 map.put(Make.CHRYSLER, "300, Crossfire,  PT Cruiser, Town and Country, Voyager");
	 map.put(Make.DODGE, " Avenger, Challenger, Caravan, Charger, Dart,");
	 map.put(Make.FORD, "Falcon, Fiesta, Focus, Fusion, GT");
	 map.put(Make.HONDA, "Acord, Civic, CR-V, Fit, Odyssey");
	 map.put(Make.HYUNDAI, "Accent, Elantra, Santa Fe, Sonata, Tucson");
	 map.put(Make.INFINITI, "G20, G25, I30, IPLQ50, J30");
	 map.put(Make.JEEP, "Compass, Grand Cherokee, Liberty, Patriot, Wrangler");
	 map.put(Make.KIA, "Forte, Sportage, Optima, Rio, Soul");
	 map.put(Make.LEXUS, "CT, ES, GS, HS, IS");
	 map.put(Make.MAZDA, "Bongo, CX-3, CX-5, CX-9, Flair");
	 map.put(Make.MINI, "Cooper, Clubman, Mark 2, Mark 3");
	 map.put(Make.MITSUBUSHI, "Carisma, Galant, Lancer, Lancer Evolution, Outlander");
	 map.put(Make.NISSAN, "370Z, Altima, Cube, GT-R, Rogue");
	 map.put(Make.PORSCHE, "911, 911 Classic, Boxster, Carrera GT, Cayman");
	 map.put(Make.SUBARU, "Baja, Forester, Impreza, Legacy, Outback");
	 map.put(Make.SUZUKI, "Equator, Escudo, SJ, Wagon R, X-90");
	 map.put(Make.TESLA, "Model 3, Model S, Model X, Roadster");
	 map.put(Make.TOYOTA, "86, 4Runner, Agya, Camara, Highlander");
	 map.put(Make.VOLKSWAGEN, "BW, Jetta, Transporter, Type 2, Type 82");
	 map.put(Make.VOLVO, "140 Series, 164, 200 Series, 300 Series, 440-460");
	 
	 
	 return map;
 
 

	 }
}

	

