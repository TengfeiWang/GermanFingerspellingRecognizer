package util;

import java.util.ArrayList;

import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.ObservationVector;

/**
 * Define one observation sequence and some flag variables.
 * @author WangTF
 * 
 */

public class ObservationSequence {

	//store one feature vector
	public List<ObservationVector> observationSequence;
	
	//if one feature vector is recorded
	public boolean flag;
	
	//if hand is detected during recording
	public boolean shouldBreak;
	
	//if user press any key to stop recording
	public boolean userBreak;
	
	//if the leap is connected
	public boolean leapConnected;
        
        //if start to record
        public boolean isStarted;
	
    public ObservationSequence(){
    	observationSequence = new ArrayList<ObservationVector>();
    	flag= false;
    	shouldBreak = false;
    	leapConnected = true;
    	userBreak = false;
        isStarted =false;
    }
}
