package util;

import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import featureExtraction.FeatureExtractor;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import util.HandDetector;

/**
 * 
 * @author WangTF
 *this class defines a timer ,we run feature extraction every certain time(in ms)
 */

public class ContTimerManagerEntropy {

	  public Timer timer = new Timer();
	  private Controller subController;
	  private ObservationVector observationVector;
	  private  List<List<ObservationVector>> observationSequences;
          private Frame previousFrame =null;

	  public ObservationSequence OS;
	  private double currentHandParameterSum;
	  private double previousHandParameterSum;
	  private long startTime;
	  private  List<Integer> featureVectorUsed;
          private List<Integer> allFeaturs;
	  private double threshold;

     
	  
      public ContTimerManagerEntropy(Controller controller,ObservationSequence OS, List<Integer> allFeaturs,List<List<ObservationVector>> recordedSeqs){
    	  
    	  subController = controller;
    	  this.OS = OS;
    	  currentHandParameterSum = 0.0;
    	  previousHandParameterSum = 0.0;
    	  
          this.allFeaturs = allFeaturs;
    	  this.observationSequences = recordedSeqs;
    	  startTime=System.currentTimeMillis();
    	
    	  
      }


	  
	  public  void start() {  
		  
			  timer.schedule(new RemindTask(), 0, 50); 
			  threshold =2.0;
  
	  }

	  public  void stop(){
	       
	  timer.cancel();
		  
	  }
          

	  
	  
	  public  class RemindTask extends TimerTask {
	    	  
	           public void run() {
	      
	        	   FeatureExtractor fe =new FeatureExtractor(subController,allFeaturs);
	        	   Frame currentFrame = fe.getFrame();
	        	   
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
                               
                                   if(OS.isStarted ==false  && Math.abs(currentHandParameterSum-previousHandParameterSum)>20.0){//the start of a transition
                                       OS.isStarted =true;
                                       //double[] featureVector =  fe.getFeatureVector(previousFrame);
			 	       //observationVector = new ObservationVector(featureVector);
		            	       //OS.observationSequence.add(observationVector);
                                       //previousFrame = currentFrame;
		            	      // previousHandParameterSum = currentHandParameterSum;
                                       startTime = System.currentTimeMillis();
                                   }
                                   if( OS.isStarted ){
                                       if(OS.observationSequence.size()>4){
                                         System.out.println("entropy"+ SequencesProviderEntropyEstimation.computeEntropy(OS.observationSequence.subList(OS.observationSequence.size()-5,OS.observationSequence.size()))); 

                                       }
                                        if( System.currentTimeMillis()-startTime >400 && OS.observationSequence.size()>4 && SequencesProviderEntropyEstimation.computeEntropy(OS.observationSequence.subList(OS.observationSequence.size()-5,OS.observationSequence.size()))<10.0 ){
		   	                 //System.out.println("one transition is recorded");
		   	                      observationSequences.add(OS.observationSequence);
		   	                      OS.flag = true;
                                              OS.isStarted =false;
		   	                      //System.out.println(observationSequences.get(0));
		   	                      OS.observationSequence=new ArrayList<ObservationVector>();
		   	                      OS.observationSequence.add(observationVector);
		   	        	   
		   	                }else if(Math.abs(currentHandParameterSum-previousHandParameterSum)>threshold){
                                        
                                        
			 	               double[] featureVector =  fe.getFeatureVector(previousFrame);
			 	               observationVector = new ObservationVector(featureVector);
		            	               OS.observationSequence.add(observationVector);
		            	               previousHandParameterSum = currentHandParameterSum;
                                               previousFrame = currentFrame;
		            	   
		                        }
                                   }
		               
		   	
		           }
		           
		          
	        	   
	           }
	  }


}
