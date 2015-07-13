package featureExtraction;

import java.util.List;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Vector;


/**
 * This class defines methods to get a feature vector from a specific leap frame.
 * @author tengfei
 */
public class FeatureExtractor {
	
	private Controller controller;
	private  List<Integer> allFeatureVector;
	
	
	public FeatureExtractor(Controller controller, List<Integer> allFeatureVector ){
		   this.controller = controller;
		   this.allFeatureVector = allFeatureVector;

	}
	
	
	public Hand getHandObject(){
	
	Frame frame = controller.frame(); //The latest frame
        HandList hands = frame.hands();
        Hand hand = hands.get(0);
        return hand;
	}
        
        public Frame getFrame(){
            Frame frame = controller.frame(); //The latest frame
            return frame;
        }
	
        /**
         * 
         * @param previousFrame the previous frame returned from the leap.
         * @return a double valued feature vector.
         */
	public double[] getFeatureVector(Frame previousFrame){
		//this function returns an array with double real values which is a feature vector
           double[] result = new double[allFeatureVector.size()];
       

           Frame frame = controller.frame(); //The latest frame
           HandList hands = frame.hands();
           Hand hand = hands.get(0);
           FingerList fingers = hand.fingers();
           
           
           Vector handCenter = hand.palmPosition();// position of hand center
           Vector palmNormal = hand.palmNormal();// direction out the serface of the hand
           Vector palmDirection = hand.direction();//The direction from the palm position toward the fingers.
           
           Vector thumbTipPosition = fingers.get(0).tipPosition();//tip position of thumb
           Vector indexTipPosition = fingers.get(1).tipPosition();//tip position of index finger
           Vector middleTipPosition = fingers.get(2).tipPosition();//tip position of middle finger
           Vector ringTipPosition = fingers.get(3).tipPosition();//tip position of ring finger
           Vector pinkyTipPosition = fingers.get(4).tipPosition();//tip position of little finger
           
           Vector thumbTipDirection = fingers.get(0).direction();// thumb tip direction
           Vector indexTipDirection = fingers.get(1).direction();//index tip direction
           Vector middleTipDirection = fingers.get(2).direction();//middle tip directin
           Vector ringTipDirection = fingers.get(3).direction();//ring tip  direction
           Vector pinkyTipDirection = fingers.get(4).direction();//pinky tip direction
           
           
           Vector indexBaseJoint = fingers.get(1).bone(Bone.Type.TYPE_PROXIMAL).prevJoint();//the base joint position of index finger
           Vector indexSecondJoint =fingers.get(1).bone(Bone.Type.TYPE_PROXIMAL).nextJoint();
           Vector middleBaseJoint = fingers.get(2).bone(Bone.Type.TYPE_PROXIMAL).prevJoint();//
           Vector middleSecondJoint = fingers.get(2).bone(Bone.Type.TYPE_PROXIMAL).nextJoint();
           Vector ringSecondJoint = fingers.get(3).bone(Bone.Type.TYPE_PROXIMAL).nextJoint();
           Vector thumbDistalCenter = fingers.get(0).bone(Bone.Type.TYPE_DISTAL).center(); 
           Vector indexDistalCenter = fingers.get(1).bone(Bone.Type.TYPE_DISTAL).center();
           Vector middleDistalCenter = fingers.get(2).bone(Bone.Type.TYPE_DISTAL).center();
           Vector ringDistalCenter = fingers.get(3).bone(Bone.Type.TYPE_DISTAL).center();
           Vector pinkyDistalCenter = fingers.get(4).bone(Bone.Type.TYPE_DISTAL).center();
     
         	/*allFeatureVector.put(1, "Thumb to Hand Center");
        	allFeatureVector.put(2, "Index to Hand Center");
                allFeatureVector.put(3, "Middle to hand center");
                allFeatureVector.put(4, "Ring to hand center");
                allFeatureVector.put(5, "Pinkey to Hand Center");
        	allFeatureVector.put(6, "Extended finger count");
           
        	allFeatureVector.put(7, "Index Openness");
        	
        	allFeatureVector.put(8, "thumb index angle");
        	allFeatureVector.put(9, "index middle angle");
        	allFeatureVector.put(10, "middle ring angle");
        	allFeatureVector.put(11, "ring pinky angke");
        	allFeatureVector.put(12,"thumb palmnormal angle" );
                allFeatureVector.put(13,"index palmnormal angle" );
                allFeatureVector.put(14,"middle palmnormal angle" );
                allFeatureVector.put(15,"ring palmnormal angle" );
                allFeatureVector.put(16,"pinky palmnormal angle" );
                allFeatureVector.put(17, "hand rotation angel");
                allFeatureVector.put(18, "hand position change magnitude");
                allFeatureVector.put(19 "dot product hand movement vector and first frame hand normal"):
           */
    	   int counter=0;
           for(int i = 0; i<allFeatureVector.size();i++){
        	    if(allFeatureVector.get(i)==1){//Thumb to Hand Center
        	    	result[counter] = thumbTipPosition.distanceTo(handCenter);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==2){//Index to Hand Center 
        	    	result[counter] = indexTipPosition.distanceTo(handCenter);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==3){//Middle to Hand Center
        	    	result[counter] = middleTipPosition.distanceTo(handCenter);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==4){//Ring to Hand Center
        	    	result[counter] = ringTipPosition.distanceTo(handCenter);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==5){//Pinky to Hand Center
        	    	result[counter] = pinkyTipPosition.distanceTo(handCenter);
        	    	counter ++;
        		    continue;
        	    }
                    
                     if(allFeatureVector.get(i)==6){//thumb to index
        	    	result[counter] = thumbTipPosition.distanceTo(indexTipPosition);
        	    	counter ++;
        		    continue;
        	    }
                      if(allFeatureVector.get(i)==7){//index to middle
        	    	result[counter] = indexTipPosition.distanceTo(middleTipPosition);
        	    	counter ++;
        		    continue;
        	    }
                       if(allFeatureVector.get(i)==8){//middle to ring
        	    	result[counter] = middleTipPosition.distanceTo(ringTipPosition);
        	    	counter ++;
        		    continue;
        	    } if(allFeatureVector.get(i)==9){//ring to pinky
        	    	result[counter] = ringTipPosition.distanceTo(pinkyTipPosition);
        	    	counter ++;
        		    continue;
        	    }
                    
                    
                       
                    
        	    if(allFeatureVector.get(i)==10){//Extended fingers count
        	    	result[counter] = fingers.extended().count();
        	    	counter ++;
        		    continue;
        	    }
                    if(allFeatureVector.get(i)==11){//Index Finger Openness
        	    	result[counter] = indexTipPosition.distanceTo(indexBaseJoint);
        	    	counter ++;
        		    continue;
        	    }
                      if(allFeatureVector.get(i)==12){//thumb index angle
        	    	result[counter] = (180/Math.PI)*thumbTipDirection.angleTo(indexTipDirection);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==13){//index middle angle
        	    	result[counter] = (180/Math.PI)*indexTipDirection.angleTo(middleTipDirection);
        	    	counter ++;
        		    continue;
        	    }
                      if(allFeatureVector.get(i)==14){//middle ring angle
        	    	result[counter] = (180/Math.PI)*middleTipDirection.angleTo(ringTipDirection);
        	    	counter ++;
        		    continue;
        	    }
                      if(allFeatureVector.get(i)==15){//ring pinky angle
        	    	result[counter] = (180/Math.PI)*ringTipDirection.angleTo(pinkyTipDirection);
        	    	counter ++;
        		    continue;
        	    }
                       if(allFeatureVector.get(i)==16){//thumb palmnormal angle
        	    	result[counter] = (180/Math.PI)*thumbTipDirection.angleTo(palmNormal);
        	    	counter ++;
        		    continue;
        	    }
                        if(allFeatureVector.get(i)==17){//index palmnormal angle
        	    	result[counter] = (180/Math.PI)*indexTipDirection.angleTo(palmNormal);
        	    	counter ++;
        		    continue;
        	    }
                         if(allFeatureVector.get(i)==18){//middle palmnormal angle
        	    	result[counter] = (180/Math.PI)*middleTipDirection.angleTo(palmNormal);
        	    	counter ++;
        		    continue;
        	    }
                          if(allFeatureVector.get(i)==19){//ring palmnormal angle
        	    	result[counter] = (180/Math.PI)*ringTipDirection.angleTo(palmNormal);
        	    	counter ++;
        		    continue;
        	    }
                           if(allFeatureVector.get(i)==20){//pinky palmnormal angle
        	    	result[counter] = (180/Math.PI)*pinkyTipDirection.angleTo(palmNormal);
        	    	counter ++;
        		    continue;
        	    }
                    if(allFeatureVector.get(i)==21){//hand rotation angel
                            if(previousFrame!=null){
                               result[counter] =(180/Math.PI)* hand.rotationAngle(previousFrame);
                               
                            }else{
        	    	       result[counter] = 0.0;
                            }
        	    	    counter ++;
        		    continue;
        	    }
                    if(allFeatureVector.get(i)==22){//hand position change magnitude
        	    	    if(previousFrame!=null){
                               result[counter] =  handCenter.distanceTo(previousFrame.hands().get(0).palmPosition());
                               
                            }else{
        	    	       result[counter] = 0.0;
                            }
        	    	    counter ++;
        		    continue;
        	    }
                       if(allFeatureVector.get(i)==23){//dot product hand movement vector and first frame hand normal
        	    	    if(previousFrame!=null){
                               result[counter] =  thumbTipPosition.distanceTo(indexTipPosition);
                               
                            }else{
        	    	       result[counter] = 0.0;
                            }
        	    	    counter ++;
        		    continue;
        	    }
        	    
           }
          // result[3]=ringDistalCenter.distanceTo(handCenter);
          // result[4]=pinkyDistalCenter.distanceTo(handCenter);
           /*for (int i =0;i<2;i++) {
        	   Finger finger = fingers.get(i);
        	   System.out.println(finger.type());
        	   TYPE_THUMB
        	   TYPE_INDEX
        	   TYPE_MIDDLE
        	   TYPE_RING
        	   TYPE_PINKY
        	   //if(finger.id()==1010 || finger.id()==1011)
        	    for(Bone.Type boneType : Bone.Type.values()) {
        	    	//System.out.println(boneType.name());
        	    	
        	    	TYPE_METACARPAL
        	    	TYPE_PROXIMAL
        	    	TYPE_INTERMEDIATE
        	    	TYPE_DISTAL
        	    	if(boneType.name() == "TYPE_PROXIMAL" ){
        	 
 
        	        counter++;
        	    	}
        	        //System.out.println(bone.direction());
        	        // ... Use the bone
        	    }
        	}*/
          // System.out.println("device is connected and  "+counter+"  bones are detected!" );
           
 
       
       return result;
		
	}

}
