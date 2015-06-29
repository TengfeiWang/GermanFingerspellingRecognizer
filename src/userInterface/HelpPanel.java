package userInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;



/**
 * 
 * @author WangTF
 *
 */

public class HelpPanel extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

        private JTabbedPane  tabbedPanel = null;
        
        private JScrollPane introScrollPanel = null;
        private JTextArea textArea;
        private JTextArea textArea1;
        private JPanel introPanel = null;
        private JPanel architecturePanel = null;
        private JLabel architectureLabel = null;
        
        
        private JScrollPane recorScrollPanel = null;
        private JPanel recorPanel = null;
        private JPanel recorPanel0 = null;
        private JPanel recorPanel1 = null;
        private JLabel recorLabel0 = null;
        private JLabel recorLabel1 = null;
        private JScrollPane trainScrollPanel = null;
        private JScrollPane recogScrollPanel = null;
        private JPanel trainPanel;
        private JLabel trainLabel;
        private JPanel recogPanel;
        private JLabel recogLabel;

	/**
	* Auto-generated main method to display this JFrame
	*/
		
	public HelpPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setTitle("Help information");
			this.setFont(new java.awt.Font("Arial",1,11));
                        this.setResizable(false);
			{
                            
                                tabbedPanel = new JTabbedPane();
				introScrollPanel = new JScrollPane();
                                introPanel = new JPanel();
                                introScrollPanel.add(introPanel);
				
				{
                                        introScrollPanel.setViewportView(introPanel);
					textArea = new JTextArea();
					textArea.setLineWrap(true);
					textArea.setWrapStyleWord(true);
					
					textArea.setText("German FingerSpelling Rcognition System"+"\n"+ "Authot:Tengfei WANG"+"\n"+"Email:tengfeiwang654@gmail.com"+"\n"
					/*"1.Data Collection:In order to establish an HMM model for a gesture, the user has to perform the gesture first while the system capture a sequence of feature vectors."+"\n"+
					"2.Training: This part is disabled if no gesture data is collected.Even if gesture data is collected the training process can still fail because of bad data quality."+"\n"+
					"3.Recognition:This part is disabled if no gesture models  is trained.If models do exist,then the user perform a gesture in the effective area of the leap, the name of recognized " +
				        "gesture will be diplayed in the result panel. "+"\n"+
					"The user can also configarate the feature vector by selecting which quntities should be included in the feature vector."*/
					+"This project is designed and implemented by Tengfei Wang and can only be used for research purpose."+"\n"+
                                                "Special thanks to my advisor Dr.Alexis Heloir who realy helps a lot on this project."
					
					);
                                        textArea.setMaximumSize(new Dimension(520,400));
					textArea.setFont(new java.awt.Font("Aparajita",1,20));
                                        
                                        introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));
                                        introPanel.add(textArea);
                                        
                                        architecturePanel = new JPanel();
                                        architectureLabel = new JLabel();
                                       // ImageIcon icon = new ImageIcon("src/images/systemarchitecture.png");
                                       // architectureLabel.setIcon(icon);
                                        architectureLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/systemarchitecture.png")));
                                        architecturePanel.add(architectureLabel);
                                        introPanel.add(architecturePanel);
                                        
                                        textArea1 = new JTextArea();
					textArea1.setLineWrap(true);
					textArea1.setWrapStyleWord(true);
					
					textArea1.setText(
                                                "\n"+
                                                "Although German Sign Language defines a gesture vocabulary of frequently used words, there are still exceptions. "
                                                        + "For those words that do not have corresponding gestures,"
                                                        + " a manual representation of letters is required, namely finger spelling. "
                                                        + "Our project is a German finger spelling recognition system that is able "
                                                        + "to do real-time continuous finger spelling recognition. Instead of modeling static posture "
                                                        + "for each letter, letter to letter transition is modeled using hidden Markov model, which is a "
                                                        + "statistical model that can handle the variation of different signers. We use the newly introduced "
                                                        + "leap motion controller to collect tracking data of hands and use the data to train our system."
                                                        + " In additional to the statistical model, the bigram language model is also used."+
                                                "\n"+"\n"+
                                                
                                                "There are two procedures embedded in this project as illustrated in the above picture:"+
                                                
                                                "\n"+ "The first procedure is labbed by red arrows, that is, how do we get our model database."+
                                                
					/*"1.Data Collection:In order to establish an HMM model for a gesture, the user has to perform the gesture first while the system capture a sequence of feature vectors."+"\n"+
					"2.Training: This part is disabled if no gesture data is collected.Even if gesture data is collected the training process can still fail because of bad data quality."+"\n"+
					"3.Recognition:This part is disabled if no gesture models  is trained.If models do exist,then the user perform a gesture in the effective area of the leap, the name of recognized " +
				        "gesture will be diplayed in the result panel. "+"\n"+
					"The user can also configarate the feature vector by selecting which quntities should be included in the feature vector."*/
                                                "\n"+ 
					"The second procedure is labbed by green arrows, this is how a transition is recognized"+"\n"+"\n"+ 
                                                "To get further information about how to use this system please find the corresponding tabs on the top."+"\n"
                                                
					
					);
                                        textArea1.setMaximumSize(new Dimension(520,1000));
					textArea1.setFont(new java.awt.Font("Aparajita",1,20));
                                        introPanel.add(textArea1);
                                        
                                        
				}
                                
                                //data collection tab
                                
                            
                                {   
                                    recorScrollPanel = new JScrollPane();
                                    recorPanel = new JPanel();
                                   
                                    recorPanel0 = new JPanel();
                                    
                                    recorScrollPanel.add(Box.createHorizontalStrut(8));
                                    recorScrollPanel.add(recorPanel);
                                    recorPanel.setLayout(new BoxLayout(recorPanel, BoxLayout.Y_AXIS));
                                    recorPanel.add(recorPanel0);
                                    
                                   
                                    recorLabel0 = new JLabel();
                                    //ImageIcon icon = new ImageIcon("src/images/datacollectionuserinstruction-0.jpg");
                                    //recorLabel0.setIcon(icon);
                                    recorLabel0.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/datacollectionuserins.jpg")));
                                    recorPanel0.add(recorLabel0);
                                    
                                    recorScrollPanel.setViewportView(recorPanel);
                                    
                                    
                                }
                                
                                //training tab
                                {
                                     trainScrollPanel = new JScrollPane();
                                     
                                     trainPanel = new JPanel();
                                     trainPanel.setLayout(new BoxLayout( trainPanel, BoxLayout.Y_AXIS));
                                     trainLabel = new JLabel();
                                     //ImageIcon icon = new ImageIcon("src/images/traininguserinstruction.jpg");
                                     //trainLabel.setIcon(icon);
                                     trainLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/traininguserinstruction.jpg")));
                                     trainPanel.add(trainLabel);
                                     trainScrollPanel.add(trainPanel);
                                     trainScrollPanel.setViewportView(trainPanel);
                                }
                                
                                //recognition
                                {
                                     recogScrollPanel = new JScrollPane();
                                     
                                     recogPanel = new JPanel();
                                     recogPanel.setLayout(new BoxLayout(recogPanel, BoxLayout.Y_AXIS));
                                     recogLabel = new JLabel();
                                     //ImageIcon icon = new ImageIcon("src/images/recognitionuserinstruction.jpg");
                                     recogLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/recognitionuserinstruction.jpg")));
                                     //recogLabel.setIcon(icon);
                                     recogPanel.add(recogLabel);
                                     recogScrollPanel.add(recogPanel);
                                     recogScrollPanel.setViewportView(recogPanel);
                                }
                                tabbedPanel.add("     About     ", introScrollPanel);
                                tabbedPanel.add("Data Collection", recorScrollPanel);
                                tabbedPanel.add("     Traing    ", trainScrollPanel);
                                tabbedPanel.add("  Recognition  ", recogScrollPanel);
                                this.add(tabbedPanel);
                                
			}
			pack();
			setSize(520, 700);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
