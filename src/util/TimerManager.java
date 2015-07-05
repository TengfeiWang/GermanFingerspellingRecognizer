package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;

import featureExtraction.FeatureExtractor;
import util.HandDetector;

/**
 * 
 * @author WangTF
 *this class defines a timer, it runs feature extraction every certain time(in ms), and 
 * finally returns a sequence a feature vector (training sequence or recognition sequence)
 * corresponding to one transition.
 */

public class TimerManager {

	  public Timer timer = new Timer();
	  private Controller subController =null;
	  private ObservationVector observationVector;
          private Frame previousFrame;

	  public ObservationSequence OS;
	  private double currentHandParameterSum;
	  private double previousHandParameterSum;
	  private long startTime; 
	  private  List<Integer> allFeatures;
	  private boolean isRecognition;
	  private double threshold;
          public boolean isStarted;

     
	  
      public TimerManager(Controller controller,ObservationSequence OS, List<Integer> allFeatures, boolean isRecognition){
    	  
    	  subController = controller;
    	  this.OS = OS;
    	  this.isRecognition = isRecognition;
    	  currentHandParameterSum = 0.0;
    	  previousHandParameterSum = 0.0;
    	  this.allFeatures = allFeatures;
    	  startTime=System.currentTimeMillis();
          OS.isStarted =false;
    	 // observationSequence = new ArrayList<ObservationVector>();
    	  
      }

	  
	  public  void start() {  
		  if(isRecognition == true){
			  timer.schedule(new RemindTask(), 0, 100); 
			  threshold =3.0;
			  
		  }else{
			  timer.schedule(new RemindTask(), 0, 10); 
			  threshold = 0.3;
		  }
		  
	       
	  
	  }

	  public  void stop(){
	       
	  timer.cancel();
		  
	  }
	  
	  
	  public  class RemindTask extends TimerTask {
	    	  
	           public void run() {
	      
	        	   FeatureExtractor fe =new FeatureExtractor(subController,allFeatures);
	        	   Frame currentFrame = fe.getFrame();
                           //Hand currentHand =fe.getHandObject();
	        	   
	        	   if(subController.isConnected()==false){
	        		   OS.leapConnected= false;
	        		   cancel();
	        	   }
	        	   
   	        	   if(currentFrame.hands().get(0).isValid() == false){
	        		 OS.shouldBreak = true;
	        		 cancel();
	        	   }
	 	   		   
	 	   	   currentHandParameterSum = new HandDetector().sumOfDistance(currentFrame.hands().get(0));
		           
		           if(currentHandParameterSum !=0 && previousHandParameterSum == 0.0 ){
		        	   double[] featureVector =  fe.getFeatureVector(previousFrame);
		 	           observationVector = new ObservationVector(featureVector);
	            	           OS.observationSequence.add(observationVector);
	            	           previousHandParameterSum = currentHandParameterSum;
                                   previousFrame = currentFrame;
		           }
		           else if(currentHandParameterSum !=0.0 && previousHandParameterSum != 0.0){
		                   if(OS.isStarted ==false  && Math.abs(currentHandParameterSum-previousHandParameterSum)>1.0){//the start of a transition
                                       OS.isStarted =true;
                                       //double[] featureVector =  fe.getFeatureVector(previousFrame);
			 	       //observationVector = new ObservationVector(featureVector);
		            	       //OS.observationSequence.add(observationVector);
		            	       //previousHandParameterSum = currentHandParameterSum;
                                       //previousFrame = currentFrame;
                                       startTime = System.currentTimeMillis();
                                   }
                                   if( OS.isStarted ){
                                       
                                        if( System.currentTimeMillis()-startTime >600 && Math.abs(currentHandParameterSum-previousHandParameterSum) <threshold ){
		   	               	      //System.out.println("length: "+ OS.observationSequence.size());
		   	                      OS.flag = true;
                                              cancel();
		   	                      
		   	        	   
		   	                }else {
                                            //if(Math.abs(currentHandParameterSum-previousHandParameterSum)>threshold){
                                        
                                        
			 	               double[] featureVector =  fe.getFeatureVector(previousFrame);
			 	               observationVector = new ObservationVector(featureVector);
		            	               OS.observationSequence.add(observationVector);
		            	               previousHandParameterSum = currentHandParameterSum;
                                               //previousFrame = currentFrame;
		            	   
		                        }
                                   }
		           }
		           
		          
	        	   
	           }
	  }


}
