package hmm;



import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.Opdf;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianMixture;

import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchLearner;
import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchScaledLearner;
import java.util.ArrayList;
import java.util.List;
import util.SequencesProvider;
import util.SequencesProviderEntropyEstimation;

/**
 * 
 * This class contains methods to train an HMM.
 * 
 * @author Tengfei Wang
 **/
public class HmmTrainer  {


	/** N: number of states */
	protected int N = 5;

	/** D: dimension used for multivariate Gaussian distributions */
	protected int D ;

	/** M: the number of mixtures in a multivariate Gaussian mixture distribution */
	protected int M = 3;

	/** Delta: the delta used for a left-right HMM. */
	protected int delta = 1;


	protected LeftRightHmm<ObservationVector> hmm;

	protected BaumWelchScaledLearner learner = new BaumWelchScaledLearner();
	
	protected SequencesProvider dataSequenceProvider = new SequencesProvider();
        //protected SequencesProviderEntropyEstimation dataSequenceProvider = new SequencesProviderEntropyEstimation();
	protected List<List<ObservationVector>> sequences;
        protected List<Integer> featureVectorUsed;
	public HmmTrainer(List<List<ObservationVector>> sequence,List<Integer> featureVectorUsed) {
		this.sequences = sequence;
		this.featureVectorUsed = featureVectorUsed;
		this.D = featureVectorUsed.size();
	}



	public Hmm<ObservationVector> train() {
		
		List<List<ObservationVector>> seqs = dataSequenceProvider.provide(N, sequences);
		
		List<Opdf<ObservationVector>> opdfs = initOpdfs(seqs);
		hmm = initHmm(seqs, opdfs);
		//System.out.println("initial hmm:"+hmm);
		hmm = (LeftRightHmm<ObservationVector>) learner.learn(hmm, sequences);
		//System.out.println("learnt hmm:"+hmm);
		return hmm;
	}

	public LeftRightHmm<ObservationVector> getTrainedHmm() {
		return hmm;
	}

	protected LeftRightHmm<ObservationVector> initHmm(List<List<ObservationVector>> seqs, List<Opdf<ObservationVector>> opdfs) {
		int total = getTotalNumberOfFtseSequence(seqs);
		LeftRightHmm<ObservationVector> hmm = new LeftRightHmm<ObservationVector>(N, delta, opdfs);
		refineTransitions(hmm, total);
		return hmm;
	}

	protected List<Opdf<ObservationVector>> initOpdfs(List<List<ObservationVector>> seqs) {
		List<Opdf<ObservationVector>> opdfs = new ArrayList<Opdf<ObservationVector>>();
		for (int i = 0; i < N; i++) {
			OpdfMultiGaussianMixture opdf = new OpdfMultiGaussianMixture(M,D);
			opdf.fit(seqs.get(i));
			opdfs.add(opdf);
		}
		return opdfs;
	}

	public static void refineTransitions(Hmm<?> hmm, int sequenceSize) {//need modifications
		double bucketSize = (double) sequenceSize / hmm.nbStates();
		double p = 1. / bucketSize;
		for (int i = 0; i < hmm.nbStates(); ++i) {
			if(i==hmm.nbStates()-1)
			    hmm.setAij(i, i, 1 );
			else
				hmm.setAij(i, i, 1 - p);	
			for (int j = i + 1; j < hmm.nbStates(); ++j) {
				hmm.setAij(i, j, p);
				//hmm.setAij(j, i, p);
			}
		}
	
	
	}

	private int getTotalNumberOfFtseSequence(List<List<ObservationVector>> seqs) {
		int size = 0;
		for (List<ObservationVector> list : seqs) {
			size += list.size();
		}
		return size;
	}

	public void setNumberOfStates(int states) {
		this.N = states;
	}

	public void setDistributionDimension(int dim) {
		this.D = dim;
	}

	public void setNumberOfDistributionMixtures(int mix) {
		this.M = mix;
	}

	public void setLeftRightHmmDelta(int delta) {
		this.delta = delta;
	}

	/*public void setDataSequenceProvider(SequencesProvider dataSequenceProvider) {
		this.dataSequenceProvider = dataSequenceProvider;
	}*/



	public void setLearner(BaumWelchScaledLearner learner) {
		this.learner = learner;
	}

	public void setNumberOfTrainingIterations(Integer numberOfIterations) {
		learner.setNbIterations(numberOfIterations);
	}

}
