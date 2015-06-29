package util;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;

/**@author:Tengfei Wang
 * This class contains 2 methods
 * 1.isStable returns true if the two hands in the same position and false otherwise
 * 2.sumOfDistance returns the change of position between two hands measured in mm.
 */

public class HandDetector {
	
	public boolean isStable(Hand hand1,Hand hand2){
		
		if(Math.abs(sumOfDistance(hand1)-sumOfDistance(hand1))==0.0){
			
			return true;
			
		}
		return false;
		
	}
	
	public double sumOfDistance(Hand hand){
		 FingerList fingers = hand.fingers();
         Vector handCenter = hand.palmPosition();
         double result= 0.0;
         //for(int i =0;i<5;i++){
        //	result += fingers.get(i).tipPosition().distanceTo(handCenter);
         //}
         for(int i=1;i<5;i++){
        	result +=fingers.get(0).tipPosition().distanceTo(fingers.get(i).tipPosition());
         }
		return result;
	}
	


}
