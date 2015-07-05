package recognitionModule;

import be.ac.ulg.montefiore.run.jahmm.ForwardBackwardCalculator;
import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.io.HmmReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationSequencesReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationVectorReader;
import be.ac.ulg.montefiore.run.jahmm.io.OpdfMultiGaussianMixtureReader;
import hmm.TrainHmmModels;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import util.DataFileOperator;



/**
 * This class contains methods to do system evaluation.
 * @author tengfei
 */
public class ModelsEvaluation {
        
    private List<Integer> featureVectorUsed;
    private String workDir;
	
    public ModelsEvaluation(List<Integer> featureVectorUsed, String workDir){
        this.featureVectorUsed = featureVectorUsed;
        this.workDir = workDir;
        System.out.println("Evaluating......");
    }

    public void evaluation () throws IOException, FileFormatException{

        DataFileOperator testFiles = new DataFileOperator(workDir+"/testsequences/");
        int testFileNum = testFiles.getFileNum();
        String[] gestureNames = testFiles.getFileNames();
        

        DataFileOperator hmmFiles = new DataFileOperator(workDir+"/hmmdata/");
        int hmmNum = hmmFiles.getFileNum();
        String[] hmmNames = hmmFiles.getFileNames();
        
        int correctNum =0;
        int totalTestedNum = 0;

        for(int i=0;i<testFileNum;i++){
            
            boolean isContained = false;
            
            for(int k = 0;k<hmmNum;k++){
                if(gestureNames[i].equals(hmmNames[k])){
                    isContained =true;
                    totalTestedNum++;
                    break;
                }
                
            }
            if(isContained){

                System.out.println("Test Gesture: "+gestureNames[i]);

                List<ObservationVector> sequence;
                
                try (Reader testSeqReader = new FileReader (workDir+"/testsequences/"+gestureNames[i]+".seq")) {
                    sequence = ObservationSequencesReader .
                            readSequences (new ObservationVectorReader (),
                                    testSeqReader).get(0);
                }
                List<ObservationVector> seq  = new TrainHmmModels().provideSeqAccordingFV(featureVectorUsed, sequence);
                System.out.println(seq);
               
                double tempProbability = 0.0;
                String bestFitModel = "failed";

                for(int j= 0;j<hmmNum; j ++){

                     try{
                         double probability;
                         
                         Reader hmmReader = new FileReader (workDir+"/hmmdata/"+hmmNames[j]+".hmm");
                         Hmm <ObservationVector> hmm = HmmReader.read(hmmReader, new OpdfMultiGaussianMixtureReader());
                         ForwardBackwardCalculator forwardBackward = new ForwardBackwardCalculator(seq,hmm);
                         probability = forwardBackward.probability();
                         // double probability = hmm.probability(OS.observationSequence);
                         hmmReader.close();
                         //System.out.println(hmmNames[j]+"............"+probability+"\n");
                         

                          if(probability >tempProbability){
                              bestFitModel = hmmNames[j];
                              tempProbability = probability;
                          }
                      }catch(Exception e){
                          e.printStackTrace();
                          continue;
                      }

                }
                System.out.println("Recognized as: "+bestFitModel);

                if( bestFitModel.equals(gestureNames[i])){
                        correctNum++;
                }
            }

        }

        System.out.println("Test Sequences Number:"+" "+totalTestedNum);
        System.out.println("Correctly Recognized Test Sequences Number:"+" "+correctNum);
        System.out.println("Accuracy:"+" "+((double)correctNum/(double)totalTestedNum));


    }

}
