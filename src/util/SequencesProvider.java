package util;

import java.util.ArrayList;
import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.ObservationVector;



/**
 * Divide each sequence equally.
 * 
 * 
 * @author Tengfei WANG
 **/
public class SequencesProvider {


	public List<List<ObservationVector>> provide(int states, List<List<ObservationVector>> seqs) {
		//System.out.println("14/5="+14/5);
		List<List<ObservationVector>> sequences = new ArrayList<List<ObservationVector>>();
	
		for (int i = 0; i < states; i++) {

			
			
			List<ObservationVector> data = new ArrayList<ObservationVector>();
			
			for(int j=0;j<seqs.size();j++){
				
				int length = 0;
	            if(seqs.get(j).size()%states <= states/2){
	            	
	            	length = seqs.get(j).size()/states;
	            }else{
	            	length = seqs.get(j).size()/states+1;
	            }
			
				if(i!=states-1){
					for(int m= i*length;m<(i+1)*length;m++){
						data.add(seqs.get(j).get(m));
					}
				}else{
					for(int n=i*length;n<seqs.get(j).size();n++){
						data.add(seqs.get(j).get(n));
					}
				}
		
			
			}
		    
			sequences.add(data);
		
		}
		
		/*for(int x =0;x<sequences.size();x++){
			System.out.println("state :"+x+1);
			System.out.println(sequences.get(x));
			System.out.println("average");
			
			
		}*/
	
		
		return sequences;
	}



}
