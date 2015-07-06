package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import java.util.Collections;
import java.util.HashMap;



/**
 * This class does the sequences segmentation.
 * 
 * 
 * @author Tengfei WANG
 **/
public class SequencesProviderEntropyEstimation {

       
        /**
        * @param states number of states 3
        * @param seqs training sequences
        * @return  3 groups of feature vectors corresponding each state used for GMM building.
        */
	public List<List<ObservationVector>> provide(int states, List<List<ObservationVector>> seqs) {
	
            List<List<ObservationVector>> sequences = new ArrayList<List<ObservationVector>>();
            List<ObservationVector> state1 = new ArrayList<ObservationVector>();
            List<ObservationVector> state2 = new ArrayList<ObservationVector>();
            List<ObservationVector> state3 = new ArrayList<ObservationVector>();
           // List<ObservationVector> state4 = new ArrayList<ObservationVector>();
            
             for(int i=0;i<seqs.size();i++){
                 
                 
                 HashMap<Double,Integer> entropys = new HashMap<Double,Integer>();
                 List<ObservationVector> sequence= seqs.get(i);
                 for(int j =4;j<sequence.size();j++){
                     List<ObservationVector> entropyGroup = new ArrayList<ObservationVector>();
                     for(int m =j;m>=j-3;m--){
                          entropyGroup.add(sequence.get(m));
                     }
                     entropys.put(computeEntropy(entropyGroup), j);
                 }
                 ArrayList<Double> keys = new ArrayList<Double>(entropys.keySet());   
                 
    		 Collections.sort(keys);
                 
                 int[] peaks = new int[2];
                 peaks[0] = entropys.get(keys.get(keys.size()-1));
                 int k;
                 for(k=keys.size()-2;k>0;k--){
                     
                     if(entropys.get(keys.get(k))>(peaks[0]+8) || entropys.get(keys.get(k))<peaks[0]-8){
                         peaks[1] = entropys.get(keys.get(k));
                         break;
                     }
                 }
      
                 Arrays.sort(peaks);
                 
                // for(int l =0;l<peaks.length;l++){
                  //   System.out.print(peaks[l]);
                 //    System.out.println();
                 //}
                 
                // System.out.println();
                 
                 for(int n1=0;n1<states;n1++){
                         if(n1 ==0){
                             for(int x1 =0;x1<peaks[0];x1++){
                                state1.add(sequence.get(x1));
                             }


                         }else if(n1==1){
                              for(int x2 =peaks[0];x2<peaks[1];x2++){
                                 state2.add(sequence.get(x2));
                             }
                         }else {
                              for(int x3 =peaks[1];x3<sequence.size();x3++){
                                 state3.add(sequence.get(x3));
                             }
                         }
                  
                        /* else{
                             for(int x4 =peaks[2];x4<sequence.size();x4++){
                                 state4.add(sequence.get(x4));
                             }
                         }*/
                     }
                 
                 /*if(i==0){
                       for(int n=0;n<states;n++){
                         if(n ==0){
                           
                               sequences.add( sequence.subList(0, peaks[0]));
  
                         }
                         else if(n==states-1){
                               sequences.add( sequence.subList(peaks[n-1],sequence.size() ));
                         }
                         else{
                       
                             sequences.add( sequence.subList(peaks[n-1], peaks[n]));
                         }
                     }
                 }else{
                     for(int n1=0;n1<states;n1++){
                         if(n1 ==0){
                             for(int x1 =0;x1<peaks[0];x1++){
                                sequences.get(n1).add(sequence.get(x1));
                             }


                         }
                         else if(n1==states-1){
                             for(int x2 =peaks[n1-1];x2<sequence.size();x2++){
                                sequences.get(n1).add(sequence.get(x2));
                             }
                         }
                         else{
                             for(int x3 =peaks[n1-1];x3<peaks[n1];x3++){
                                 sequences.get(n1).add(sequence.get(x3));
                             }
                         }
                     }
                 }*/
                 
              
             }
            sequences.add(state1);
            sequences.add(state2);
            sequences.add(state3);
            
     
	    return sequences;
	}
        
        
       /**
       * computer the entropy value of a group of feature vectors
       **/
        public static Double computeEntropy(List<ObservationVector> group){
            
            double entropy = 0.0;
            for(int i=0;i<group.get(0).dimension();i++){
               double variance =0.0;
               double average =0.0;
               double sum1 =0.0;
               double sum2 =0.0;
               
               for(int j=0;j<group.size();j++)
                  sum1 += group.get(j).value(i);
               
               average = sum1/group.size();
                
               
               for(int k=0;k<group.size();k++){
                   //System.out.println("power"+group.get(k).value(i));
                   sum2 += (double)Math.pow(group.get(k).value(i)-average, 2.0);
               }
              // System.out.println("sum2:"+sum2);
               variance =sum2/group.size();
              // System.out.println("variance:"+variance);
               entropy += variance;
               
            }
                 
            //System.out.println("entropy:"+entropy);
            return entropy;
        }



}
