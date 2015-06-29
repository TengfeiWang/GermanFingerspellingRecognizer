package userInterface;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import com.leapmotion.leap.Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import recognitionModule.GestureRecognition;
import util.ContTimerManager;
import util.ObservationSequence;
import util.TimerManager;


public class ContRecPanel extends javax.swing.JPanel {
	private JPanel recording;
	private JPanel result;
	private JPanel recordingandResult;
	private java.awt.Component box$Filler_IL1;
	private JTextArea resultTextArea;
	private JButton startButton;
	private  ContTimerManager task;
    private  Controller controller;
    public  ObservationSequence OS;
    private List<Integer> allFeaturs;
    private List<Integer> featureVectorUsed;
    private int recognizedNum =0;
    private boolean recordingFinished ;
    private String workDir;
   
    
    private List<List<ObservationVector>> recordedSeqs =  new ArrayList<List<ObservationVector>>();
    private JLabel instLabel1;
    private JLabel instLabel2;
    private JLabel instLabel3;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public ContRecPanel( List<Integer> allFeaturs, String workDir,List<Integer> featureVectorUsed) {
		super();
                this.featureVectorUsed = featureVectorUsed;
		this.allFeaturs = allFeaturs;
		this.workDir = workDir;
		controller = new Controller();

		initGUI();
	}
	
	private void initGUI() {
              try {
               BoxLayout thisLayout = new BoxLayout(this, javax.swing.BoxLayout.X_AXIS);
               this.setLayout(thisLayout);


               this.setPreferredSize(new java.awt.Dimension(459, 415));


               this.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent evt) {
                 thisKeyPressed(evt);
                }
               });
               this.add(Box.createHorizontalStrut(30));


               {
                recordingandResult = new JPanel();
                BoxLayout recordingandResultLayout = new BoxLayout(recordingandResult, javax.swing.BoxLayout.Y_AXIS);

                recordingandResult.setLayout(recordingandResultLayout);
                recordingandResult.add(Box.createVerticalStrut(30));
                this.add(recordingandResult);
                {
                 recording = new JPanel();
                 recordingandResult.add(recording);
                 GridBagLayout recordingLayout = new GridBagLayout();
                 recording.setPreferredSize(new java.awt.Dimension(415, 217));
                 recording.setBorder(BorderFactory.createCompoundBorder(
                   null, 
                   BorderFactory.createTitledBorder("Recording")));
                 recordingLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
                 recordingLayout.rowHeights = new int[] {77, 38, 39, 7};
                 recordingLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
                 recordingLayout.columnWidths = new int[] {7, 7, 7, 7};
                 recording.setLayout(recordingLayout);
                 {
                  startButton = new JButton();
                  startButton.setFocusable(true);
                  recording.add(startButton, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(7, 0, 3, 0), 0, 0));
                  startButton.setText("Start");
                                                            startButton.setBackground(Color.LIGHT_GRAY);
                  startButton.addMouseListener(new MouseAdapter() {
                   public void mouseClicked(MouseEvent evt) {
                    startButtonMouseClicked(evt);
                   }
                  });

                  /*startButton.addFocusListener(new FocusAdapter() {

                   public void focusGained(FocusEvent evt) {
                    startButtonFocusGained(evt);
                   }
                  });*/
                  startButton.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent evt) {
                    startButtonActionPerformed(evt);
                   }
                  });
                 }
                 {
                  instLabel1 = new JLabel();
                  recording.add(instLabel1, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  instLabel1.setText("Orange: Waiting for Recording");
                  instLabel1.setAutoscrolls(true);
                  instLabel1.setVerticalAlignment(SwingConstants.BOTTOM);
                 }
                 {
                  instLabel2 = new JLabel();
                  recording.add(instLabel2, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  instLabel2.setText("Green: Recording in Process");
                 }
                 {
                  instLabel3 = new JLabel();
                  recording.add(instLabel3, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  instLabel3.setText("Red: One Transition is Recorded ");
                  instLabel3.setVerticalAlignment(SwingConstants.TOP);
                 }
                }
                {
                 result = new JPanel();
                 recordingandResult.add(result);
                 BorderLayout resultLayout = new BorderLayout();
                 result.setLayout(resultLayout);
                 result.setPreferredSize(new java.awt.Dimension(459, 147));
                 result.setBorder(BorderFactory.createCompoundBorder(
                   null, 
                   BorderFactory.createTitledBorder(null, "Result", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null)));
                 {
                  resultTextArea = new JTextArea();
                  resultTextArea.setLineWrap(true);
                  result.add(resultTextArea, BorderLayout.CENTER);
                 }
                }
                recordingandResult.add(Box.createVerticalStrut(30));
                this.add(Box.createHorizontalStrut(30));
               }

              } catch (Exception e) {
               e.printStackTrace();
              }
 }
	
	private void startButtonActionPerformed(ActionEvent evt) {
		new Thread(new Runnable() {
            @Override
            public void run() {
            	recordingFinished =false;
                recognizedNum =0;          	
            	OS = new ObservationSequence();
            	recordedSeqs.clear();
            	
            	for(int i=2;i>=-1;i--){
        			try {
        				
        			Thread.sleep(1000);
        			
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			
        			startButton.setText(String.valueOf(i));
        			
        			
        		
        		}
            	//startButton.setText("recording.......");
            	
            	task = new ContTimerManager(controller,OS,allFeaturs,recordedSeqs);
    		task.start();
                startButton.setText("Please Perform");
            	startButton.setBackground(Color.ORANGE);
                while(!OS.isStarted){
                }
        
            	while(controller.isConnected() && OS.shouldBreak == false&& OS.userBreak ==false ){
            		
            		startButton.setText("recording.......");
            		startButton.setBackground(Color.green);

        		while(controller.isConnected() && OS.shouldBreak == false&& OS.userBreak ==false&& OS.flag ==false ){
        		}
        
                        OS.flag=false;
        		startButton.setText("pausing.......");
        		startButton.setBackground(Color.red);
        		while(!OS.isStarted && controller.isConnected() && OS.shouldBreak == false&& OS.userBreak ==false){
                        }
    
        			//continue;
        		    
        	
            	}
   	 	      if(!controller.isConnected() ){
  	 	    	 startButton.setText("Start");
  	 	    	 startButton.setBackground(Color.LIGHT_GRAY);
    	        	  JOptionPane.showMessageDialog(null, "Please connect the leap!", "Error", JOptionPane.ERROR_MESSAGE);
    	        	 // break;
    	        	  
    	          }
         	      else if(OS.shouldBreak){
         	    	  startButton.setText("Start");
         	    	  startButton.setBackground(Color.LIGHT_GRAY);
    	        	  JOptionPane.showMessageDialog(null, "Please put you hand in front of the leap", "Error", JOptionPane.ERROR_MESSAGE);
    	        	 // break;
    	          }
         	      else if(!OS.userBreak){
         	    	  startButton.setText("Start");
         	    	 startButton.setBackground(Color.LIGHT_GRAY);
    	        	  JOptionPane.showMessageDialog(null, "Recording stopped", "Info", JOptionPane.INFORMATION_MESSAGE);
    	        	  //break;
    	          }
               
              recordingFinished = true;
            }
        }).start();
	}
	
	private void thisKeyPressed(KeyEvent evt) {
	    //user press any key to stop recording
		OS.userBreak = true;

	}
	
	/*private void startButtonFocusGained(FocusEvent evt) {
		//System.out.println("startButton.focusGained, event="+evt);
		//TODO add your code for startButton.focusGained
		new Thread(new Runnable() {
            @Override
            public void run() {
            
              while(recordedSeqs.size() ==0){
            	
              }
    
        	  while(recognizedNum < recordedSeqs.size()){
        		
        		  try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		  GestureRecognition gr = new GestureRecognition(recordedSeqs.get(recognizedNum),true);
        		  gr.showResult();
        		  while(!gr.isFinished){
        			  
        		  }
        		  resultTextArea.append(gr.bestFitModel);
                  System.out.println("result:"+gr.bestFitModel);
        		  
        		  recognizedNum++;
        		  
        	  }
        	  
            }
           
		}).start();
		
	}*/
	
	private void startButtonMouseClicked(MouseEvent evt) {
		//System.out.println("startButton.mouseClicked, event="+evt);
		//TODO add your code for startButton.mouseClicked
		new Thread(new Runnable() {
            @Override
            public void run() {
            	System.out.println("start");
            	try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	while(recordedSeqs.isEmpty() && !recordingFinished){
            		
            	}
            
              while(!recordingFinished){
             	  while(recognizedNum < recordedSeqs.size()){
            		  
                          //System.out.println("Computing"+recordedSeqs.size());
                          //System.out.println("@@@@@@@@@"+recordedSeqs.get(recognizedNum)+recognizedNum);
                        
            		  GestureRecognition gr = new GestureRecognition(recordedSeqs.get(recognizedNum),true,workDir,featureVectorUsed);
            		  gr.showResult();
            		  while(!gr.isFinished){
            			  
            		  }
                        
            		  resultTextArea.append(gr.bestFitModel);
                          System.out.println("result:"+gr.bestFitModel);
            		  
            		  recognizedNum++;
            		  
            	  }
   
              }
       
              recordedSeqs.clear();
              System.out.println("Stop");
        	  
            }
           
		}).start();
	}

}
