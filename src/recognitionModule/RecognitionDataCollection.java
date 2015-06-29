package recognitionModule;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import util.ObservationSequence;
import util.TimerManager;

import com.leapmotion.leap.Controller;
import java.awt.Color;




/**
 * 
 * @author WangTF
 *
 */

public class RecognitionDataCollection extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel timer;
	private JLabel instructionLabel;
	private  TimerManager task;
    private  Controller controller;
    public  ObservationSequence OS;
    private List<Integer> featureVectorUsed;
	/**
	* Auto-generated main method to display this JDialog
	*/
		
	public RecognitionDataCollection(ObservationSequence OS,List<Integer> featureVectorUsed) {
		super();
		controller = new Controller();
		this.featureVectorUsed = featureVectorUsed;
		initGUI();
		this.OS = OS;
		
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("Gesture Recognition");
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				{
					instructionLabel = new JLabel();
					getContentPane().add(instructionLabel, new GridBagConstraints(0, 0, 4, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					instructionLabel.setText("Please perform a gesture when the timer goes to 0");
					instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
					instructionLabel.setFont(new java.awt.Font("Lucida Sans Typewriter",1,16));
				}
				{
					timer = new JLabel();
					getContentPane().add(timer, new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					timer.setText("3");
                                        timer.setForeground(Color.BLUE);
					timer.setFocusable(true);
					timer.setHorizontalAlignment(SwingConstants.CENTER);
					timer.setFont(new java.awt.Font("Malgun Gothic",1,36));
					//timer.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/background2.jpg")));
					timer.addFocusListener(new FocusAdapter() {
						public void focusGained(FocusEvent evt) {
							timerFocusGained(evt);
						}
					});
				}
			}
			this.setSize(567, 440);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void timerFocusGained(FocusEvent evt) {
		//System.out.println("timer.focusGained, event="+evt);
		//TODO add your code for timer.focusGained
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
        			
        			timer.setText(String.valueOf(i));
        			//timerLabel.repaint();
        			
        		
        		}
            	        timer.setText("Please Perform");                       
                        timer.setForeground(Color.RED);
        		task = new TimerManager(controller,OS,featureVectorUsed,true);
        		task.start();
                        while(!OS.isStarted && !OS.shouldBreak && OS.leapConnected ){
                            
                        }
                        timer.setText("Recording ……");
                        timer.setForeground(Color.GREEN);
       
        		while(!OS.flag  && !OS.shouldBreak && OS.leapConnected){
        			
        		}
                        System.out.println("Recognition Sequence"+OS.observationSequence);
        		dispose();
           
            }
        }).start();
	}

}
