package dataCollection;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import util.ObservationSequence;
import util.TimerManager;
import com.leapmotion.leap.Controller;
import java.awt.Color;


/**
 * @author Tengfei Wang
*/
public class RecordingGesture extends javax.swing.JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private  JLabel timerLabel;
	private JLabel jLabel1;
	private  TimerManager task;
        private  Controller controller;
        //public  List<ObservationVector> observationSequence = null;
        public  ObservationSequence OS;
        private JPanel container;
        private String imageName;
        private String imageName0;
        private List<Integer> allFeatures;
        private boolean isTest =false;

	/**
	* Auto-generated main method to display this JDialog
	*/
		
	public RecordingGesture(ObservationSequence OS,String iName,String gName,List<Integer> allFeatures,boolean isTest) {
		super();
		this.imageName = iName;
		this.imageName0 = gName;
		this.allFeatures = allFeatures;
		this.OS = OS;
                this.isTest =isTest;
		
		initGUI();
		controller = new Controller();
		//getObservationSequence();
		
	}
		
	
	private void initGUI() {
		try {
			{
				BorderLayout thisLayout = new BorderLayout();
				this.setTitle("Recording Gesture");
				getContentPane().setLayout(thisLayout);
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent evt) {
						//System.out.println("this.windowClosing, event="+evt);
						//TODO add your code for this.windowClosing
						OS.userBreak = true;
						dispose();
					}
				});
				{
					container = new JPanel();
					getContentPane().add(container, BorderLayout.CENTER);
					GridBagLayout containerLayout = new GridBagLayout();
					containerLayout.rowWeights = new double[] {0.1, 0.0, 0.1, 0.1};
					containerLayout.rowHeights = new int[] {7, 197, 7, 7};
					containerLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
					containerLayout.columnWidths = new int[] {101, 147, 94, 7};
					container.setLayout(containerLayout);
					container.setPreferredSize(new java.awt.Dimension(584, 412));
					{
						jLabel5 = new JLabel();
						container.add(jLabel5, new GridBagConstraints(0, 3, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jLabel5.setText("Press any key to exit.");
						jLabel5.setPreferredSize(new java.awt.Dimension(584, 38));
						jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
						jLabel5.setFont(new java.awt.Font("Aparajita",1,20));
					}
					
					{
						jLabel2 = new JLabel();
						container.add(jLabel2, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 8), 0, 0));
						jLabel2.setPreferredSize(new java.awt.Dimension(301, 216));
						jLabel2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/"+imageName+".png")));
					}
					{
						jLabel1 = new JLabel();
						container.add(jLabel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel1.setPreferredSize(new java.awt.Dimension(151, 155));
						jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/"+imageName0.substring(0, 1)+".png")));
					}
					{
						timerLabel = new JLabel();
						container.add(timerLabel, new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						timerLabel.setLayout(null);
						timerLabel.setText("3");
                                                timerLabel.setForeground(Color.BLUE);
						timerLabel.setFocusable(true);
						timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
						timerLabel.setFont(new java.awt.Font("Aparajita",0,30));
						timerLabel.addFocusListener(new FocusAdapter() {
							public void focusGained(FocusEvent evt) {
								timerLabelFocusGained(evt);
							}
						});
						timerLabel.addKeyListener(new KeyAdapter() {
							public void keyPressed(KeyEvent evt) {
								thisKeyPressed(evt);
							}
						});
					}
					{
						jLabel4 = new JLabel();
						container.add(jLabel4, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel4.setText("to");
						jLabel4.setFont(new java.awt.Font("Arial",0,20));
						jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
					}
					{
						jLabel3 = new JLabel();
						container.add(jLabel3, new GridBagConstraints(0, 0, 5, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jLabel3.setText("Please perform the transition when the timer goes to 0");
						jLabel3.setFont(new java.awt.Font("Lucida Bright",1,20));
						jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
					}
				}

			}
			this.setSize(600, 478);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	
	private void timerLabelFocusGained(FocusEvent evt) {
	
		//TODO add your code for timerLabel.focusGained
		new Thread(new Runnable() {
            @Override
            public void run() {
            	for(int i=2;i>=-1;i--){
        			try {
        				
        			Thread.sleep(1000);
        			
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			
        			timerLabel.setText(String.valueOf(i));
        			//timerLabel.repaint();
        			
        		
        		}
            	        timerLabel.setText("Please Perform");
                        
                        timerLabel.setForeground(Color.RED);
                        if(isTest){
                            task = new TimerManager(controller,OS,allFeatures,true);
        		    task.start();
                        }
                        else{
                            task = new TimerManager(controller,OS,allFeatures,false);
        		    task.start();
                        }
        		
                        while(!OS.isStarted && !OS.shouldBreak  && OS.leapConnected ){
                            
                        }
                        timerLabel.setText("Recording ……");
                        timerLabel.setForeground(Color.GREEN);
        		while(!OS.flag  && !OS.shouldBreak  && OS.leapConnected ){
        			
        		}
        		dispose();
           
            }
        }).start();
	}
	
	private void thisKeyPressed(KeyEvent evt) {//when any key is pressed, exit.
		//System.out.println("this.keyPressed, event="+evt);
		OS.userBreak = true;
		dispose();
			
	}

}
