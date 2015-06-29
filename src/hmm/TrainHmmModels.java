package hmm;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.OpdfGaussianMixtureFactory;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianFactory;

import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.io.HmmWriter;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationSequencesReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationVectorReader;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfGaussianMixtureWriter;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfMultiGaussianMixtureWriter;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfMultiGaussianWriter;
import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchLearner;
import be.ac.ulg.montefiore.run.jahmm.learn.KMeansLearner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import userInterface.MainGUI;
import util.DataFileOperator;
import util.LeftRightHmm;

/**
 * 
 * @author WangTF
 * This class contains methods to train all the recorded transitions and store the 
 * trained models in the "hmmdata" folder.
 */
public class TrainHmmModels {
	
	public JProgressBar progressBar;
	public int exampleNum;
	public String[] names;
	public Reader reader;
	public Writer writer;
	public JLabel failureInfo;
        public JButton cancelTraining;
        public TrainThread trainThread;
	public int failed;
        private String workDir;
	private  List<Integer> featureVectorUsed;
	public static boolean exitThread = false;
        
	
	public TrainHmmModels(JProgressBar trainingProcess,JLabel jLabel,List<Integer> featureVectorUsed,JButton cancelTraining,String workDir){
		progressBar = trainingProcess;
                this.workDir = workDir;
		failureInfo = jLabel;
                this.cancelTraining = cancelTraining;
                cancelTraining.addActionListener(new ButtonListener());
		this.featureVectorUsed = featureVectorUsed;
                exitThread = false;
	}
        public TrainHmmModels(){
            
        }
	
	
	public void trainAll() throws IOException{
		
		failed = 0;
		
        System.out.println("start  to write");
		try{
		      DataFileOperator dataFileOperator= new DataFileOperator(workDir+"/sequencedata/");
		
	          exampleNum = dataFileOperator.getFileNum();
	          progressBar.setMaximum(exampleNum);
	          names =  new String[exampleNum];
	          names = dataFileOperator.getFileNames();//string names stores all the training data file names
		 }catch (Exception e) {
	     	    //add your error handling code here
	     		e.printStackTrace();
	     }
		
		trainThread = new TrainThread();
		trainThread.start();
    
	         
	}
        
        public List<List<ObservationVector>>  provideSeqsAccordingFV(List<Integer> featureVectorUsed, List<List<ObservationVector>> seqs){
            
           List<List<ObservationVector>> sequences = new ArrayList<List<ObservationVector>>();
           for(int i = 0;i<seqs.size();i++){
               
               List<ObservationVector> sequence = seqs.get(i);
               List<ObservationVector> newSequence = new ArrayList<ObservationVector>();
               for(int j=0;j<sequence.size();j++){
                   double[] observationVector = new double[featureVectorUsed.size()];
                   for(int m=0;m<featureVectorUsed.size();m++){
                       observationVector[m]=sequence.get(j).value(featureVectorUsed.get(m)-1);
                   }
                   ObservationVector OV = new ObservationVector(observationVector);
                   newSequence.add(OV);
               }
               sequences.add(newSequence);
           }
            
            return sequences;
        }
        
        public List<ObservationVector>  provideSeqAccordingFV(List<Integer> featureVectorUsed, List<ObservationVector> seq){
            List<ObservationVector> sequence = new ArrayList<ObservationVector>();
            for(int i = 0;i<seq.size();i++){
                double[] observationVector = new double[featureVectorUsed.size()];
                for(int m=0;m<featureVectorUsed.size();m++){
                       observationVector[m]=seq.get(i).value(featureVectorUsed.get(m)-1);
                }
                ObservationVector OV = new ObservationVector(observationVector);
                sequence.add(OV);
                
            }
            return sequence;
        }

    private static class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
             JButton o = (JButton) e.getSource();
             String label = o.getText();
             if(label=="Cancel"){
                 System.out.println("Training thread stopped");
                 exitThread = true;
             }
        }
    }
	class TrainThread extends Thread {
		
		public void run(){
			
		// TODO Auto-generated method stub
                int i = 0;
		while(i<exampleNum  && !exitThread ){
	        System.out.println("Training thread running");
				      
		try {
		   reader = new FileReader (workDir+"/sequencedata/"+names[i]+".seq");
		} catch (FileNotFoundException e2) {
		// TODO Auto-generated catch block
		   e2.printStackTrace();
		}

		List<List<ObservationVector>> seqs = new ArrayList<List<ObservationVector>>();
		try {
			seqs =  
				ObservationSequencesReader .
				readSequences (new ObservationVectorReader (),  
				reader);
		} catch (FileFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			reader.close ();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		 try{

                     /* for(int x=0;x<seqs.size();x++){
                    	  System.out.println("sequence:"+x+1);
                    	  System.out.println(seqs.get(x));
                      }*/
                      //System.out.println("extracted size"+provideSeqsAccordingFV(featureVectorUsed,seqs).size());
                     // System.out.println("extracted seqs"+provideSeqsAccordingFV(featureVectorUsed,seqs));
		      Hmm<ObservationVector> learntHmm = new HmmTrainer(provideSeqsAccordingFV(featureVectorUsed,seqs),featureVectorUsed).train();
		      writer = new FileWriter (workDir+"/hmmdata/"+names[i]+".hmm");
		      HmmWriter.write(writer,new OpdfMultiGaussianMixtureWriter(), learntHmm);
		      writer.close();
		        	  /*KMeansLearner <ObservationVector > kml =
		          
		            		new KMeansLearner <ObservationVector >(7,
		            		new  OpdfMultiGaussianMixtureFactory (3,featureVectorUsed.size()) , seqs);
		            		 System.out.println(seqs);
		              //System.out.println("sequences size"+seqs.size());
		              Hmm<ObservationVector> initHmm = kml.iterate();
		              //System.out.println("initHmm:"+initHmm);

		              BaumWelchLearner bwl =
		            		new BaumWelchLearner ();
		            		Hmm <ObservationVector > learntHmm = bwl.learn(initHmm ,  
		            		seqs);
		              //System.out.println("learntHmm: "+learntHmm);
		
				
					  writer = new FileWriter ("hmmdata/"+names[i]+".hmm");
					  HmmWriter.write(writer,new OpdfMultiGaussianMixtureWriter(), learntHmm);
		              writer.close();*/
		        	     	
		  }catch(Exception e){
		       failed++;
		       e.printStackTrace();
		       continue;
		  }
		 System.out.println("hmm has been written to the txt file");
		           
		 progressBar.setValue((i+1)*(progressBar.getMaximum()/exampleNum));
		 failureInfo.setText((i+1-failed)+" sucess "+failed+" failed");
		 failureInfo.setVisible(true);
	         i++;       
	          }
		
	        }
		
			
	}
		
}





