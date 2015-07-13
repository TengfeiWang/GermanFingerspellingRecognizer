package recognitionModule;

import be.ac.ulg.montefiore.run.jahmm.ForwardBackwardCalculator;
import be.ac.ulg.montefiore.run.jahmm.ForwardBackwardScaledCalculator;
import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.io.HmmReader;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfMultiGaussianMixtureReader;
import hmm.TrainHmmModels;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import util.DataFileOperator;
import util.ObservationSequence;


/**
 * This class holds method to do an isolate recognition.
 * @author tengfei
 */

public class TransitionRecognition {
	
	private JTextArea dataAnalysis;
	private JLabel resultLabel;
        private List<Integer> featureVectorUsed = new ArrayList<>();

	private ObservationSequence OS;
	private List<ObservationVector> sequence;
	private boolean isContinuous=false;
	public  boolean isFinished =false;
	public String bestFitModel ="failed";
        private String workDir =null;
	//private List<ObservationVector> modifiedSeq= new ArrayList<ObservationVector>();
	
	
	public TransitionRecognition(ObservationSequence OSS,JTextArea dataAnalysis, JLabel resultLabel, String workDir, List<Integer> featureVectorUsed){
		this.OS =OSS;
                this.workDir = workDir;
                this.featureVectorUsed =featureVectorUsed;
		this.dataAnalysis = dataAnalysis;
		this.resultLabel = resultLabel;
		dataAnalysis.setText("");
	}
	public TransitionRecognition(List<ObservationVector> sequence,boolean isContinuous, String workDir,List<Integer> featureVectorUsed){
	
            this.sequence =sequence;
            this.workDir = workDir;
            this.featureVectorUsed = featureVectorUsed;
	    this.isContinuous = isContinuous;
		//resultTextArea.setText("");
	}
	
	public void showResult(){
		
		RecognitionThread rt = new RecognitionThread();
		rt.start();
		
		
	}
	
    class RecognitionThread extends Thread {
		
                @Override
		public void run(){
			if(isContinuous){
				try{
				    
				     DataFileOperator fileOperator = new DataFileOperator(workDir+"/hmmdata/");
				     int modelNumber = fileOperator.getFileNum();
				     String[] modelNames = fileOperator.getFileNames();
				     List<ObservationVector> seq =new TrainHmmModels().provideSeqAccordingFV(featureVectorUsed, sequence);
				     
				     double tempProbability = 0.0;
				     
				     
		
				     
				     for(int i= 0;i<modelNumber; i ++){
					
				    	 try{
					      Reader reader = new FileReader (workDir+"/hmmdata/"+modelNames[i]+".hmm");
					      Hmm <ObservationVector> hmm = HmmReader.read(reader, new OpdfMultiGaussianMixtureReader());
					      
					       //ForwardBackwardScaledCalculator forwardBackward = new ForwardBackwardScaledCalculator(OS.observationSequence,hmm);
					    	  //ForwardBackwardScaledCalculator forwardBackward = new ForwardBackwardScaledCalculator(testSeq.get(2),hmm);
					      ForwardBackwardCalculator 
					  
					    	  forwardBackward = new ForwardBackwardCalculator(seq,hmm);
					   
						      double probability = forwardBackward.probability();
					     // double probability = hmm.probability(OS.observationSequence);
					 
					          reader.close();
					      
				      	      //writer.append(modelNames[i]+"............"+probability+"\r\n");
				
					          //dataAnalysis.append(modelNames[i]+"............"+probability+"\n");
					          //dataAnalysis.setText(modelNames[i]+"............"+probability);
					          if(probability >tempProbability){
					    	      bestFitModel = modelNames[i];
					    	      tempProbability = probability;
					          }
					      }catch(Exception e){
					    	 // e.printStackTrace();
					    	 continue;
					      }
				     }
				    

				     
				     isFinished =true;
				    
				}catch (Exception e) {
		     	    //add your error handling code here
					
		     		//e.printStackTrace();
		     	}
				
			}else{
				try{
				    
				     DataFileOperator fileOperator = new DataFileOperator(workDir+"/hmmdata/");
				     int modelNumber = fileOperator.getFileNum();
				     String[] modelNames = fileOperator.getFileNames();
				     Date now = new Date(); 
				     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				     String currentTime = dateFormat.format( now );
				     Writer writer = new FileWriter(workDir+"/logs/"+"recognition"+currentTime+".log");
				     
				     double tempProbability = 0.0;
		                     long startTime=System.currentTimeMillis();
				     
					/*	int obsGap = OS.observationSequence.size()/5;
						System.out.println(obsGap);
						int m;
						for(m =0;m<OS.observationSequence.size();m=m+obsGap)
						{
							modifiedSeq.add(OS.observationSequence.get(m));
						}
						if(m!=OS.observationSequence.size()-1){
							modifiedSeq.add(OS.observationSequence.get(OS.observationSequence.size()-1));
						}
						System.out.println(modifiedSeq);*/
				     
				     //this is only a test
				    /* Reader readerTest = new FileReader ("test/EU.seq");
				     testSeq = ObservationSequencesReader .
								readSequences(new ObservationVectorReader (),  
								readerTest);
				     readerTest.close();*/
				     //test end
				     List<ObservationVector> seq =new TrainHmmModels().provideSeqAccordingFV(featureVectorUsed, OS.observationSequence);
				     for(int i= 0;i<modelNumber; i ++){
					
				    	 try{
					      Reader reader = new FileReader (workDir+"/hmmdata/"+modelNames[i]+".hmm");
					      Hmm <ObservationVector> hmm = HmmReader.read(reader, new OpdfMultiGaussianMixtureReader());
					      
					       
					      //ForwardBackwardScaledCalculator forwardBackward = new ForwardBackwardScaledCalculator(seq,hmm);
					     ForwardBackwardCalculator forwardBackward  = new ForwardBackwardCalculator(seq,hmm);
					      
				
					     double probability = forwardBackward.probability();
					      //double probability =Math.abs(forwardBackward.lnProbability()) ;
					 
					          reader.close();
					      
				      	      writer.append(modelNames[i]+"............"+probability+"\r\n");
				
					          dataAnalysis.append(modelNames[i]+"............"+probability+"\n");
					          //dataAnalysis.setText(modelNames[i]+"............"+probability);
                                                  
                                                 if(probability >tempProbability){
                                                      bestFitModel = modelNames[i];
					    	      tempProbability = probability;
                                                  }
					      }catch(Exception e){
					    	  //e.printStackTrace();
					    	 continue;
					      }
				     }
				     writer.close();
                                     //System.out.println(System.currentTimeMillis()-startTime);
				     if(bestFitModel ==null){
				    	 resultLabel.setText("Unrecognized gesture!");
				    	 //resultTextArea.append("unrecognize");
				     }else{
				    	 resultLabel.setText(bestFitModel);
				    	 //resultTextArea.append(bestFitModel);
				     }
				     
				     
				    
				}catch (Exception e) {
		     	    //add your error handling code here
					
		     		e.printStackTrace();
		     	}
			}

		}
    }

}
