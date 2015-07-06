package util;

import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import com.leapmotion.leap.Bone;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;

/**@author:Tengfei Wang
 * return hand parameters
 */

public class HandDetector {
	
    
        /**
         * Judge if the hand is moved in two frames.
         * @param hand1 hand object from frame 1.
         * @param hand2 hand object from frame 2.
         * @return true if hand is moved.
         */
	public static boolean isStable(Hand hand1,Hand hand2){
	    
            FingerList fingers1 = hand1.fingers();
            FingerList fingers2 = hand2.fingers();
            Vector hand1Center = hand1.palmPosition();	
            Vector hand2Center = hand2.palmPosition();	
		
            double difference= 0.0;
            difference = hand1Center.distanceTo(hand2Center);
            for(int i=1;i<5;i++){
        	difference +=fingers1.get(0).tipPosition().distanceTo(fingers2.get(i).tipPosition());
            }
            
            if(difference<0.5){
			
			return true;
			
            }
            return false;
		
	}

    static boolean isStable(double[] previousFV, double[] featureVector) {
       double difference =0.0;
       for(int i=0;i<previousFV.length;i++){
           difference += Math.abs(previousFV[i]-featureVector[i]);
       }
       System.out.println(difference);
       if(difference <0.5){
           return true;
       }
       return false;
     
    }

  
        
        
        /**
         * 
         * @param hand
         * @return hand paramenters
         */
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
