package featureExtraction;

import java.util.List;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Vector;

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
	

	public double[] getFeatureVector(Frame previousFrame){
		//this function returns an array with double real values which is a feature vector
           double[] result = new double[allFeatureVector.size()];
       

           Frame frame = controller.frame(); //The latest frame
           HandList hands = frame.hands();
           Hand hand = hands.get(0);
           FingerList fingers = hand.fingers();
           Vector handCenter = hand.palmPosition();
           Vector thumbTipPosition = fingers.get(0).tipPosition();//tip position of thumb
           Vector indexTipPosition = fingers.get(1).tipPosition();//tip position of index finger
           Vector middleTipPosition = fingers.get(2).tipPosition();//tip position of middle finger
           Vector ringTipPosition = fingers.get(3).tipPosition();//tip position of ring finger
           Vector pinkyTipPosition = fingers.get(4).tipPosition();//tip position of little finger
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
        	allFeatureVector.put(3, "Middle to Hand Center");
        	allFeatureVector.put(4, "Ring to Hand Center");
        	allFeatureVector.put(5, "Pinkey to Hand Center");
        	allFeatureVector.put(6, "Thumb to Index");
        	allFeatureVector.put(7, "Thumb to Middle");
        	allFeatureVector.put(8, "Index to Middle");
        	allFeatureVector.put(9, "Thumb to Ring");
        	allFeatureVector.put(10,"Thumb to Pinky" );
        	allFeatureVector.put(11,"Index Bending" );
        	allFeatureVector.put(12,"Middle Bending" );*/
    	   int counter=0;
           for(int i = 0; i<allFeatureVector.size();i++){
        	    if(allFeatureVector.get(i)==1){//Thumb to Hand Center
        	    	result[counter] = thumbTipPosition.distanceTo(handCenter);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==2){//Index Finger Openness
        	    	result[counter] = indexTipPosition.distanceTo(indexBaseJoint);
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
        	    if(allFeatureVector.get(i)==6){//Thumb to Index
        	    	result[counter] = thumbTipPosition.distanceTo(indexTipPosition);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==7){//Thumb to Middle
        	    	result[counter] = thumbTipPosition.distanceTo(middleSecondJoint);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==8){//Index to Middle
        	    	result[counter] = indexTipPosition.distanceTo(middleTipPosition);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==9){//Thumb to Ring
        	    	result[counter] = thumbTipPosition.distanceTo(ringSecondJoint);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==10){//Thumb to Pinky
        	    	result[counter] = thumbTipPosition.distanceTo(pinkyTipPosition);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==11){//pinky to ring
        	    	result[counter] = pinkyTipPosition.distanceTo(ringTipPosition);
        	    	counter ++;
        		    continue;
        	    }
        	    if(allFeatureVector.get(i)==12){//Middle to Ring
        	    	result[counter] = middleTipPosition.distanceTo(ringTipPosition);
        	    	counter ++;
        		    continue;
        	    }
                    if(allFeatureVector.get(i)==13){//hand rotation angel
                            if(previousFrame!=null){
                               result[counter] =  (100/Math.PI)*hand.rotationAngle(previousFrame);
                               
                            }else{
        	    	       result[counter] = 0.0;
                            }
        	    	    counter ++;
        		    continue;
        	    }
                    if(allFeatureVector.get(i)==14){//hand position change
        	    	    if(previousFrame!=null){
                               result[counter] =  handCenter.distanceTo(previousFrame.hands().get(0).palmPosition());
                               
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
