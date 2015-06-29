package util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RecordingManager {
	
	private Map<String,String> gestureMap;
	
	public RecordingManager(){//construct method
		
		gestureMap = new HashMap<String,String>();
             
		gestureMap.put("AB", "B");gestureMap.put("HD", "D");gestureMap.put("NT", "T");gestureMap.put("TO", "O");
		gestureMap.put("AC", "C");gestureMap.put("HE", "E");gestureMap.put("NU", "U");gestureMap.put("TR", "R");
		gestureMap.put("AD", "D");gestureMap.put("HI", "I");gestureMap.put("NV", "V");gestureMap.put("TS", "S");
	        gestureMap.put("AE", "E");
		gestureMap.put("AF", "F");gestureMap.put("HL", "L");gestureMap.put("NW", "W");//gestureMap.put("TT", "T");
		gestureMap.put("AG", "G");gestureMap.put("HM", "M");gestureMap.put("NZ", "Z");gestureMap.put("TU", "U");
		gestureMap.put("AH", "H");gestureMap.put("HN", "N");gestureMap.put("OC", "C");gestureMap.put("TW", "W");
		gestureMap.put("AI", "I");
		gestureMap.put("AJ", "J");
		gestureMap.put("AK", "K");
		gestureMap.put("AL", "L");gestureMap.put("HO", "O");gestureMap.put("OD", "D");//gestureMap.put("TZ", "Z");
	        gestureMap.put("AO", "O");
		gestureMap.put("AM", "M");gestureMap.put("HR", "R");gestureMap.put("OL", "L");gestureMap.put("UC", "C");
		gestureMap.put("AN", "N");gestureMap.put("HS", "S");gestureMap.put("OM", "M");gestureMap.put("UE", "E");
		gestureMap.put("AR", "R");gestureMap.put("HT", "T");gestureMap.put("ON", "N");gestureMap.put("UF", "F");
		gestureMap.put("AS", "S");gestureMap.put("HU", "U");gestureMap.put("OR", "R");gestureMap.put("UG", "G");
		gestureMap.put("AT", "T");gestureMap.put("IC", "C");gestureMap.put("OS", "S");gestureMap.put("UM", "M");
		gestureMap.put("AU", "U");gestureMap.put("ID", "D");gestureMap.put("PA", "A");gestureMap.put("UN", "N");
		gestureMap.put("BA", "A");gestureMap.put("IE", "E");gestureMap.put("PE", "E");gestureMap.put("UR", "R");
		gestureMap.put("BE", "E");gestureMap.put("IG", "G");gestureMap.put("PR", "R");gestureMap.put("US", "S");
		gestureMap.put("BI", "I");gestureMap.put("IH", "H");gestureMap.put("RA", "A");//gestureMap.put("UT", "T");
		gestureMap.put("BL", "L");gestureMap.put("IK", "K");gestureMap.put("RB", "B");gestureMap.put("VE", "E");
		gestureMap.put("BR", "R");gestureMap.put("IL", "L");gestureMap.put("RC", "C");gestureMap.put("VO", "O");
		                          gestureMap.put("IM", "M");gestureMap.put("RD", "D");gestureMap.put("WE", "E");
		gestureMap.put("CH", "H");gestureMap.put("IN", "N");gestureMap.put("RE", "E");gestureMap.put("WI", "I");
		gestureMap.put("CK", "K");gestureMap.put("IO", "O");gestureMap.put("RF", "F");gestureMap.put("WO", "O");
		gestureMap.put("DA", "A");gestureMap.put("IR", "R");/*gestureMap.put("RG", "G");*///gestureMap.put("ZE", "E");
		gestureMap.put("DD", "D");
		gestureMap.put("DE", "E");gestureMap.put("IS", "S");gestureMap.put("RH", "H");//gestureMap.put("ZI", "I");
		gestureMap.put("DI", "I");gestureMap.put("IT", "T");gestureMap.put("RI", "I");//gestureMap.put("ZU", "U");
		gestureMap.put("DS", "S");gestureMap.put("KA", "A");gestureMap.put("RK", "K");
		gestureMap.put("DU", "U");gestureMap.put("KE", "E");gestureMap.put("RL", "L");
		gestureMap.put("EA", "A");                          gestureMap.put("RM", "M");
		gestureMap.put("EB", "B");gestureMap.put("KL", "L");gestureMap.put("RN", "N");
		gestureMap.put("EC", "C");gestureMap.put("KO", "O");gestureMap.put("RO", "O");
		gestureMap.put("ED", "D");gestureMap.put("KR", "R");//gestureMap.put("RR", "R");
		gestureMap.put("EE", "E");
		gestureMap.put("EF", "F");gestureMap.put("KT", "T");gestureMap.put("RS", "S");
		gestureMap.put("EG", "G");gestureMap.put("LA", "A");gestureMap.put("RT", "T");
		gestureMap.put("EH", "H");gestureMap.put("LD", "D");/*gestureMap.put("RU", "U");*/
		gestureMap.put("EI", "I");gestureMap.put("LE", "E");gestureMap.put("RV", "V");
		gestureMap.put("EK", "K");gestureMap.put("LI", "I");gestureMap.put("RW", "W");
		gestureMap.put("EL", "L");//gestureMap.put("LL", "L");
		gestureMap.put("EM", "M");gestureMap.put("LO", "O");gestureMap.put("RZ", "Z");
		gestureMap.put("EN", "N");gestureMap.put("LS", "S");//gestureMap.put("RUE", "UE");
		gestureMap.put("ER", "R");gestureMap.put("LT", "T");gestureMap.put("SA", "A");
		gestureMap.put("ES", "S");gestureMap.put("LU", "U");gestureMap.put("SB", "B");
		gestureMap.put("ET", "T");//gestureMap.put("LAE", "AE");
		gestureMap.put("EU", "U");gestureMap.put("MA", "A");gestureMap.put("SC", "C");
	
		gestureMap.put("EV", "V");gestureMap.put("ME", "E");gestureMap.put("SD", "D");
		gestureMap.put("EW", "W");gestureMap.put("MI", "I");gestureMap.put("SE", "E");
		
		gestureMap.put("EZ", "Z");//gestureMap.put("MM", "M");
		gestureMap.put("FA", "A");gestureMap.put("MU", "U");/*gestureMap.put("SG", "G");*/
		gestureMap.put("FE", "E");gestureMap.put("NA", "A");gestureMap.put("SI", "I");
		gestureMap.put("FO", "O");gestureMap.put("NB", "B");gestureMap.put("SO", "O");
		gestureMap.put("FR", "R");gestureMap.put("ND", "D");//gestureMap.put("SP", "P");
		gestureMap.put("FT", "T");gestureMap.put("NE", "E");//gestureMap.put("SS", "S");
		gestureMap.put("FUE", "UE");
		gestureMap.put("GA", "A");gestureMap.put("NF", "F");gestureMap.put("ST", "T");
		gestureMap.put("GD", "D");gestureMap.put("NG", "G");gestureMap.put("SU", "U");
		/*gestureMap.put("GE", "E");*/gestureMap.put("NH", "H");gestureMap.put("SW", "W");
		gestureMap.put("GI", "I");gestureMap.put("NI", "I");gestureMap.put("TA", "A");
		gestureMap.put("GL", "L");gestureMap.put("NK", "K");gestureMap.put("TD", "D");
		gestureMap.put("GR", "R");gestureMap.put("NL", "L");gestureMap.put("TE", "E");
		/*gestureMap.put("GS", "S");*/gestureMap.put("NM", "M");gestureMap.put("TH", "H");
		gestureMap.put("GT", "T");///gestureMap.put("NN", "N");
		gestureMap.put("GU", "U");gestureMap.put("NO", "O");gestureMap.put("TI", "I");
		gestureMap.put("HA", "A");gestureMap.put("NS", "S");gestureMap.put("TL", "L");
	

	}
	
	
	public Map<String,String> unrecordedGesture(String dir){
		 String[] names = new DataFileOperator(dir).getFileNames();
		 for(int i =0;i< names.length;i++){
			 if(gestureMap.containsKey(names[i]) == true){
				 gestureMap.remove(names[i]);
			 }
		 }
		 //System.out.println("number of unrecorded gesture:"+gestureMap.size());

		 return gestureMap;
	}
        
        public Map<String,String> recordedGesture(String dir){
            String[] names = new DataFileOperator(dir).getFileNames();
            Map<String,String> recordedGestureMap = new HashMap<String,String>();
            for(int i =0;i< names.length;i++){
			 
		recordedGestureMap.put(names[i], names[i].substring(1));
			
            }
            return recordedGestureMap;
            
        }
	
	

}
