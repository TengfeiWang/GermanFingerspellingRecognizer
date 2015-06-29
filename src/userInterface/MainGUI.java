package userInterface;


import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationSequencesWriter;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationVectorWriter;
import dataCollection.GestureModelViewer;
import dataCollection.RecordingGesture;
import dataCollection.RecordingPreparation;
import featureExtraction.CurrentFeatureVector;
import featureExtraction.FeatureVectorConfig;
import hmm.TrainHmmModels;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.WindowConstants;
import recognitionModule.GestureRecognition;
import recognitionModule.ModelsEvaluation;
import recognitionModule.RecognitionDataCollection;
import util.CreatWorkSpace;
import util.DataFileOperator;
import util.FindLeapHome;
import util.ObservationSequence;
import util.RecordingManager;



/**ATHOR:Tengfei Wang
*This is the main frame of the application
*/
public class MainGUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

    
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                        
		} catch(Exception e) {
			e.printStackTrace();
		}
      
        
	}
	

	private Map<String ,String> unrecordedGesture;
        private JPanel controlPanel;
	private JButton dataCollection;
	private JButton training;
	private JLabel infoLabel;
	private JPanel part2datacollePanel;
	private JPanel part1datacollePanel;
	private JButton viewExample;
	private JButton deleteExample;
	private JButton contRecognition;
	private JMenuItem runEvaluation;
	private JMenu testSeq;
        private JMenuItem recordTestSequence;
        private JMenuItem importTestSequence;
	private JMenu Evalu;
	private JMenuItem importHmmFile;
	private JMenu importMenu;
	private JMenuItem importSequenceFile;
	private JMenuItem runLeap;
	private JButton seeFV;
	private JMenuItem featureVector;
	private JMenu configMenu;
	private JButton viewModels;
	private JMenuItem exitMenu;
	private JMenuItem aboutMenu;
	private JMenuItem logMenu;
	private JMenuItem recognitionMenu;
	private JMenuItem trainMenu;
	private JMenuItem dataCollectionMenu;
	private JMenu window;
	private JLabel jLabel1;
	private JLabel failureInfo;
	private  ContRecPanel conRec;
	private JPanel evalutatonPanel;
	private JLabel resultLabel;
	private JButton learnAlphabet;
	private JTable featureInfoTable;
	private JPanel resultPanel;
	private JTextArea dataAnalysis;
	private JButton runRecognitionB;
	private JPanel runRecognition;
	private JMenu File;
	private JMenu Help;
	private JMenuBar jMenuBar;
	private JButton cancelTraining;
	private JProgressBar trainingProcess;
	private JPanel trainingprocessPanel;
	private JLabel numofModels;
	private JButton trainModels;
	private JPanel trainmodelPanel;
	private JScrollPane featureinfoPanel;
	private JPanel dataCollePanel;
	private JPanel switchPanel;
	private JButton recognition;
	private JPanel trainingPanel;
	private JPanel recognitionPanel;
        private String workDir = "C:\\GermanFingerSpellingRecognition_Workspace";
        public String leapVisualizerDir = "";
    private JButton startRecording;
    private int numExamples;
    private int numHMMs;
    private JList fVectorList;
    private DefaultListModel listModel = new DefaultListModel();
    private HashMap<Integer,String> allFeatureVector = new HashMap<Integer,String>();
    {
        {
        	allFeatureVector.put(1, "Thumb to Hand Center");
        	allFeatureVector.put(2, "Index to Hand Center");
        	allFeatureVector.put(3, "Middle to Hand Center");
        	allFeatureVector.put(4, "Ring to Hand Center");
        	allFeatureVector.put(5, "Pinkey to Hand Center");
        	allFeatureVector.put(6, "Thumb to Index");
        	allFeatureVector.put(7, "Thumb to Middle");
        	allFeatureVector.put(8, "Index to Middle");
        	allFeatureVector.put(9, "Thumb to Ring");
        	allFeatureVector.put(10,"Thumb to Pinky" );
        	allFeatureVector.put(11,"Pinky to Ring" );
        	allFeatureVector.put(12,"Middle to Ring" );
                allFeatureVector.put(13,"Hand Rotation Angel" );
                allFeatureVector.put(14,"Hand Position Change" );
        	
        }
    };
    
    private List<Integer> featureVectorUsed=new ArrayList();{
        featureVectorUsed.add(1);
        featureVectorUsed.add(2);
    	featureVectorUsed.add(3);
    	featureVectorUsed.add(4);
    	featureVectorUsed.add(5);
        featureVectorUsed.add(6);
        featureVectorUsed.add(7);
        featureVectorUsed.add(8);
        featureVectorUsed.add(9);
        featureVectorUsed.add(10);     
        featureVectorUsed.add(11);
        featureVectorUsed.add(12);
        featureVectorUsed.add(13);
        featureVectorUsed.add(14);
    	
    }
      private List<Integer> allFeatures=new ArrayList();{
        allFeatures.add(1);
        allFeatures.add(2);
    	allFeatures.add(3);
    	allFeatures.add(4);
    	allFeatures.add(5);
        allFeatures.add(6);
        allFeatures.add(7);
        allFeatures.add(8);
        allFeatures.add(9);
        allFeatures.add(10);     
        allFeatures.add(11);
        allFeatures.add(12);
        allFeatures.add(13);
        allFeatures.add(14);
    	
    }
   
 
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				MainGUI inst = new MainGUI();
				inst.setResizable(false);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public MainGUI() {
            super();
            new CreatWorkSpace(workDir);
	    initGUI();
                              
	}
	


	private void initGUI() {
		try {
			//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
			//setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setTitle("German Fingerspelling Alphabet Recognition");
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/imageicon.jpg")).getImage());
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					//System.out.println("this.windowClosing, event="+evt);
					//TODO add your code for this.windowClosing
					int isYes = JOptionPane.showConfirmDialog(null, "Save data before exit?", "Save and exit", JOptionPane.YES_NO_OPTION);
					DataFileOperator fs = new DataFileOperator(workDir+"/sequencedata/");
					DataFileOperator fh = new DataFileOperator(workDir+"/hmmdata/");
                                        DataFileOperator ft = new DataFileOperator(workDir+"/testsequences/");
					if(isYes == JOptionPane.YES_OPTION){
						if(fs.getFileNames().length != 0 || fh.getFileNum() !=0){
							Date now = new Date(); 
						        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
						        String currentTime = dateFormat.format( now );
							try {
								DataFileOperator.copyDirectiory(workDir+"/sequencedata/", workDir+"/backup/sequences/"+currentTime+"sequencedata");
								DataFileOperator.copyDirectiory(workDir+"/hmmdata/", workDir+"/backup/hmms/"+currentTime+"hmmdata");
                                                                DataFileOperator.copyDirectiory(workDir+"/testsequences/", workDir+"/backup/test/"+currentTime+"testdata");
								Writer writer = new FileWriter(workDir+"/backup/sequences/"+currentTime+"sequencedata/featurevector.txt");
								for(int i =0;i<allFeatures.size();i++){
									writer.write(allFeatures.get(i)+"\r\n");
								}
								writer.close();
								Writer writer1 = new FileWriter(workDir+"/backup/hmms/"+currentTime+"hmmdata/featurevector.txt");
								for(int i =0;i<featureVectorUsed.size();i++){
									writer1.write(featureVectorUsed.get(i)+"\r\n");
								}
								writer1.close();
                                                              
								Writer writer2 = new FileWriter(workDir+"/backup/test/"+currentTime+"testdata/featurevector.txt");
								for(int i =0;i<allFeatures.size();i++){
									writer2.write(allFeatures.get(i)+"\r\n");
								}
								writer2.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					
						
					}
			
                                        fs.deletAll();
                                        fh.deletAll();
                                        ft.deletAll();
                                        System.exit(0);
				}
	
			});
          

	             {
				jMenuBar = new JMenuBar();
				setJMenuBar(jMenuBar);
				{
					File = new JMenu();
		     		        jMenuBar.add(File);
					File.setText("Open");
					//File.setPreferredSize(new java.awt.Dimension(31, 13));
					//File.setOpaque(true);
					//File.setSize(59, 23);
					{
						runLeap = new JMenuItem();
						File.add(runLeap);
						runLeap.setText("Leap Visualizer");
						runLeap.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								//System.out.println("runLeap.actionPerformed, event="+evt);
								//TODO add your code for runLeap.actionPerformed
                                                            //System.out.println(leapVisualizerDir);
								if(!leapVisualizerDir.isEmpty()  ){
                                                                     try {
                                                                          Runtime.getRuntime().exec(leapVisualizerDir);
                                                                      } catch (IOException ex) {
                                                                          Logger.getLogger(FindLeapHome.class.getName()).log(Level.SEVERE, null, ex);
                                                                      }
                                                                    
                                                                }
                                                                else{
                                                                  FindLeapHome leapHome = new FindLeapHome(leapVisualizerDir);
                                                                  leapHome.setModal(true);
                                                                  leapHome.setVisible(true);
                                                                  leapVisualizerDir = leapHome.getPath();
                                                                }
                                                                  
							
							}
						});
					}
					{
						importMenu = new JMenu();
						File.add(importMenu);
						importMenu.setText("Import");
						{
							importSequenceFile = new JMenuItem();
							importMenu.add(importSequenceFile);
							importSequenceFile.setText("Transitions Data");
							importSequenceFile.setToolTipText("Use already recorded transitions");
							importSequenceFile.setBounds(0, -23, 99, 23);
							importSequenceFile.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									//System.out.println("importSequenceFile.actionPerformed, event="+evt);
									//TODO add your code for importSequenceFile.actionPerformed
									DataFileOperator dfo = new DataFileOperator(workDir+"/sequencedata/",featureVectorUsed);
                                                                        boolean isFolderChosen = dfo.chooseFolder(workDir+"/backup/sequences");
                                                                        if(isFolderChosen){
                                                                        numExamples = new DataFileOperator(workDir+"/sequencedata").getFileNum();
                                                                        infoLabel.setText(numExamples+"  transitions recorded!");
                                                                        trainMenu.setEnabled(true);
                                                                        training.setEnabled(true);
                                                                        DataFileOperator df= new DataFileOperator(workDir+"/hmmdata/");
                                                                        df.deletAll();
                                                                        recognition.setEnabled(false);
                                                                        contRecognition.setEnabled(false);
                                                                        recognitionMenu.setEnabled(false);
                                                                        {
                                                                                listModel.removeAllElements();
                                                                                for(int i =0;i<featureVectorUsed.size();i++){

                                                                                        listModel.addElement("["+(i+1)+"]"+"      "+allFeatureVector.get(featureVectorUsed.get(i)));
                                                                                }

                                                                                featureinfoPanel.setViewportView(fVectorList);
                                                                                fVectorList.setModel(listModel);
                                                                        }
                                                                        numExamples = new DataFileOperator(workDir+"/sequencedata").getFileNum();
                                                                        numofModels.setText(numExamples+"  models to be trained.");
											
								}
                                                                }
							});
						}
						{
							importHmmFile = new JMenuItem();
							importMenu.add(importHmmFile);
							importHmmFile.setText("HMM Database");
							importHmmFile.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									//System.out.println("importHmmFile.actionPerformed, event="+evt);
									//TODO add your code for importHmmFile.actionPerformed
									DataFileOperator df= new DataFileOperator(workDir+"/hmmdata/");
									df.deletAll();
									DataFileOperator dfo = new DataFileOperator(workDir+"/hmmdata/",featureVectorUsed);
									boolean isFolderChosen = dfo.chooseFolder(workDir+"/backup/hmms");
                                                                        if(isFolderChosen){
                                                                            int numModels = new DataFileOperator(workDir+"/hmmdata/").getFileNum();
                                                                            numofModels.setText(numModels+"  models trained!");
                                                                            trainMenu.setEnabled(true);
                                                                            training.setEnabled(true);
                                                                            recognition.setEnabled(true);
                                                                            recognitionMenu.setEnabled(true);
                                                                            contRecognition.setEnabled(true);

                                                                            {
                                                                                    listModel.removeAllElements();
                                                                                    for(int i =0;i<featureVectorUsed.size();i++){

                                                                                            listModel.addElement("["+(i+1)+"]"+"      "+allFeatureVector.get(featureVectorUsed.get(i)));
                                                                                    }
                                                                                    //fVectorList = new JList();
                                                                                    //fVectorList.removeAll();
                                                                                    featureinfoPanel.setViewportView(fVectorList);
                                                                                    fVectorList.setModel(listModel);
                                                                            }

									
								        }
                                                                }
							});
						}
					}
					window = new JMenu();
					jMenuBar.add(window);
					window.setText("Window");
					{
						dataCollectionMenu = new JMenuItem();
						window.add(dataCollectionMenu);
						dataCollectionMenu.setText("Data Collection");
						dataCollectionMenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								//System.out.println("dataCollectionMenu.actionPerformed, event="+evt);
								//TODO add your code for dataCollectionMenu.actionPerformed
							 	dataCollePanel.setVisible(true);
				            	trainingPanel.setVisible(false);
				               	recognitionPanel.setVisible(false);
							}
						});
					}
					{
						trainMenu = new JMenuItem();
						window.add(trainMenu);
						trainMenu.setText("Train Models");
						trainMenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								//System.out.println("trainMenu.actionPerformed, event="+evt);
								//TODO add your code for trainMenu.actionPerformed
							   dataCollePanel.setVisible(false);
                                                           trainingPanel.setVisible(true);
                                                           recognitionPanel.setVisible(false);
							}
						});
					}
					{
						recognitionMenu = new JMenuItem();
						window.add(recognitionMenu);
						recognitionMenu.setText("Run Recognition");
						recognitionMenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								//System.out.println("recognitionMenu.actionPerformed, event="+evt);
								//TODO add your code for recognitionMenu.actionPerformed
					                    recognitionPanel.setVisible(true);
				            	            dataCollePanel.setVisible(false);
				            	            trainingPanel.setVisible(false);
							}
						});
					}
					{
						logMenu = new JMenuItem();
						window.add(logMenu);
						logMenu.setText("Logs");
						logMenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								//System.out.println("logMenu.actionPerformed, event="+evt);
								//TODO add your code for logMenu.actionPerformed
								try {
									java.awt.Desktop.getDesktop().open(new File(workDir+"/logs/"));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}  
							}
						});
					}
					{
						configMenu = new JMenu();
						jMenuBar.add(configMenu);
						configMenu.setText("Config");
						configMenu.setBounds(-31, 22, 49, 23);
						{
							featureVector = new JMenuItem();
							configMenu.add(featureVector);
							featureVector.setText("Change FV");
							featureVector.setToolTipText("Please delete all gesture and model data before change the feature vector");
							numExamples = new DataFileOperator(workDir+"/sequencedata").getFileNum();
							numHMMs = new DataFileOperator(workDir+"/hmmdata").getFileNum();
							if(numExamples !=0 && numHMMs !=0){
								featureVector.setEnabled(false);
							}
							featureVector.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									//System.out.println("featureVector.actionPerformed, event="+evt);
									//TODO add your code for featureVector.actionPerformed
									FeatureVectorConfig fvc = new FeatureVectorConfig(allFeatureVector,featureVectorUsed);
											fvc.setVisible(true);
								}
							});
						}
					}
					{
						Evalu = new JMenu();
						jMenuBar.add(Evalu);
						Evalu.setText("Evaluation");
                                                testSeq = new JMenu();
                                                testSeq.setText("Test Transitions");
                                                Evalu.add(testSeq);
						{
							recordTestSequence = new JMenuItem();
                                                        recordTestSequence.setText("Record");
							testSeq.add(recordTestSequence);
							
							recordTestSequence.addActionListener(new ActionListener() {
                                                            //do something when user want to record
								public void actionPerformed(ActionEvent evt) {
								if((numHMMs = new DataFileOperator(workDir+"/hmmdata").getFileNum()) != 0){
                                                                    Map<String,String> trainedHMMs = new RecordingManager().recordedGesture(workDir+"/hmmdata");
                                                                    RecordingPreparation rp = new RecordingPreparation(trainedHMMs,allFeatures,workDir);
                                                                    rp.ManageRecording();
                                                                }else{
                                                                        int isYes = JOptionPane.showConfirmDialog(null, "Import an HMM database?", "Info", JOptionPane.YES_NO_OPTION);
								
								        if(isYes == JOptionPane.YES_OPTION){
                                                                        DataFileOperator dfo = new DataFileOperator(workDir+"/hmmdata/",featureVectorUsed);
                                                                    	boolean isFolderChosen = dfo.chooseFolder(workDir+"/backup/hmms");
                                                                        if(isFolderChosen){
                                                                            int numModels = new DataFileOperator(workDir+"/hmmdata/").getFileNum();
                                                                            numofModels.setText(numModels+"  models trained!");
                                                                            trainMenu.setEnabled(true);
                                                                            training.setEnabled(true);
                                                                            recognition.setEnabled(true);
                                                                            recognitionMenu.setEnabled(true);
                                                                            contRecognition.setEnabled(true);

                                                                            {
                                                                                    listModel.removeAllElements();
                                                                                    for(int i =0;i<featureVectorUsed.size();i++){

                                                                                            listModel.addElement("["+(i+1)+"]"+"      "+allFeatureVector.get(featureVectorUsed.get(i)));
                                                                                    }
                                                                                    //fVectorList = new JList();
                                                                                    //fVectorList.removeAll();
                                                                                    featureinfoPanel.setViewportView(fVectorList);
                                                                                    fVectorList.setModel(listModel);
                                                                            }

									
								        }
                                                                        }
                                                                }
                                                              
                                                                   
								}
							});
                                                        
                                                        importTestSequence = new JMenuItem();
							importTestSequence.setText("Import");
							testSeq.add(importTestSequence);
							importTestSequence.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
								
                                                                        DataFileOperator dfo = new DataFileOperator(workDir+"/testsequences",featureVectorUsed,false);
                                                                    	boolean isFolderChosen = dfo.chooseFolder(workDir+"/backup/test/");
                                
                                                                   
								}
							});
                                                        
						}
						{
							runEvaluation = new JMenuItem();
							Evalu.add(runEvaluation);
							runEvaluation.setText("Run Evaluation");
							runEvaluation.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									//System.out.println("runEvaluation.actionPerformed, event="+evt);
									//TODO add your code for runEvaluation.actionPerformed
									try {
                                                                            
                                                                            if((numHMMs = new DataFileOperator(workDir+"/hmmdata").getFileNum()) == 0){
                                                                                JOptionPane.showMessageDialog(null, "Please build or import an HMM database", "Error", JOptionPane.ERROR_MESSAGE);
                                                                            }
                                                                            if(new DataFileOperator(workDir+"/testsequences").getFileNum() == 0){
                                                                                JOptionPane.showMessageDialog(null, "Please import or record test sequences.", "Error", JOptionPane.ERROR_MESSAGE);
                                                                                
                                                                            }else{
                                                                                new ModelsEvaluation(featureVectorUsed, workDir).evaluation();
                                                                            }
										
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (FileFormatException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
						}
					}
					jMenuBar.add(Box.createHorizontalGlue());
					Help = new JMenu();
					jMenuBar.add(Help);
					Help.setText("Help");
					{
						aboutMenu = new JMenuItem();
						Help.add(aboutMenu);
						aboutMenu.setText("About");
						aboutMenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								//System.out.println("aboutMenu.actionPerformed, event="+evt);
								//TODO add your code for aboutMenu.actionPerformed
								HelpPanel hp =new HelpPanel();
								hp.setVisible(true);
							}
						});
					}
					{
						exitMenu = new JMenuItem();
						Help.add(exitMenu);
						exitMenu.setText("Exit");
						exitMenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								//System.out.println("exitMenu.actionPerformed, event="+evt);
								//TODO add your code for exitMenu.actionPerformed
								int isYes = JOptionPane.showConfirmDialog(null, "Save data before exit?", "Save and exit", JOptionPane.YES_NO_OPTION);
								DataFileOperator fs = new DataFileOperator(workDir+"/sequencedata/");
								if(isYes == JOptionPane.YES_OPTION){
									if(fs.getFileNames().length != 0){
										Date now = new Date(); 
									    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
									    String currentTime = dateFormat.format( now );
										try {
											DataFileOperator.copyDirectiory(workDir+"/sequencedata/", workDir+"/backup/"+currentTime+"sequencedata");
											Writer writer = new FileWriter(workDir+"/backup/"+currentTime+"sequencedata/featurevector.txt");
											for(int i =0;i<featureVectorUsed.size();i++){
												writer.write(featureVectorUsed.get(i)+"\r\n");
											}
											writer.close();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								
									
								}
						
			                    fs.deletAll();
			                	DataFileOperator fh = new DataFileOperator(workDir+"/hmmdata/");
			                    fh.deletAll();
			                    System.exit(0);
							}
						});
					}
				}

			}
			{
				controlPanel = new JPanel();
				GridBagLayout controlPanelLayout = new GridBagLayout();
				controlPanelLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				controlPanelLayout.rowHeights = new int[] {87, 84, 85, 7};
				controlPanelLayout.columnWeights = new double[] {0.0, 0.1, 0.1, 0.1};
				controlPanelLayout.columnWidths = new int[] {24, 7, 7, 7};
				controlPanel.setLayout(controlPanelLayout);
				getContentPane().add(controlPanel, BorderLayout.WEST);
				controlPanel.setPreferredSize(new java.awt.Dimension(165, 662));
				{
					dataCollection = new JButton();
					dataCollection.setBackground(Color.ORANGE);
					controlPanel.add(dataCollection, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.VERTICAL, new Insets(40, 0, 0, 3), 0, 0));
					dataCollection.setText("1.Data Collection");
					dataCollection.setPreferredSize(new java.awt.Dimension(119, 32));
					dataCollection.addActionListener(new ButtonListener());
					dataCollection.setToolTipText("Show data collection panel");
				}
				{
					training = new JButton();
					training.setBackground(Color.ORANGE);
					controlPanel.add(training, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(39, 0, 0, 8), 0, 0));
					training.setText("2.Train");
					numExamples = new DataFileOperator(workDir+"/sequencedata").getFileNum();
					if(numExamples ==0){
						training.setEnabled(false);
						trainMenu.setEnabled(false);
					}
					training.setPreferredSize(new java.awt.Dimension(119, 32));
					training.addActionListener(new ButtonListener());
					training.setToolTipText("Show training panel");
				}
				{
					recognition = new JButton();
					recognition.setBackground(Color.ORANGE);
					controlPanel.add(recognition, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(37, 0, 2, 4), 0, 0));
					recognition.setText("3.Recognition");
				    numHMMs = new DataFileOperator(workDir+"/hmmdata").getFileNum();
					if(numHMMs ==0){
						recognition.setEnabled(false);
						recognitionMenu.setEnabled(false);
					}
					recognition.setPreferredSize(new java.awt.Dimension(93, 27));
					recognition.addActionListener(new ButtonListener());
					recognition.setToolTipText("Show recognition panel");
				}
				{
					learnAlphabet = new JButton();
					learnAlphabet.setBackground(Color.GREEN);
					controlPanel.add(learnAlphabet, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(230, 2, 110, 2), 0, 0));
					learnAlphabet.setText("Learn Alphabet");
					learnAlphabet.setToolTipText("See how German fingerspelling alphabet is defined");
					learnAlphabet.addActionListener(new ButtonListener());
				}
				{
					seeFV = new JButton();
					seeFV.setBackground(Color.GREEN);
					controlPanel.add(seeFV, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(150, 0, 190, 3), 0, 0));
					seeFV.setText("Feature Vector");
					seeFV.setToolTipText("See current FV used");
					seeFV.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//System.out.println("seeFV.actionPerformed, event="+evt);
							//TODO add your code for seeFV.actionPerformed
							CurrentFeatureVector CFV = new CurrentFeatureVector(featureVectorUsed,allFeatureVector,training,recognition,infoLabel);
							CFV.setModal(true);
							CFV.setVisible(true);
						}
					});
				}

			}
			{
				switchPanel = new JPanel();
				CardLayout switchPanelLayout = new CardLayout();
				switchPanel.setLayout(switchPanelLayout);
				getContentPane().add(switchPanel, BorderLayout.CENTER);
				switchPanel.setPreferredSize(new java.awt.Dimension(451, 636));
				{
					dataCollePanel = new JPanel();
					GridBagLayout dataCollePanelLayout = new GridBagLayout();
					dataCollePanelLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
					dataCollePanelLayout.rowHeights = new int[] {12, 106, 286, 7};
					dataCollePanelLayout.columnWeights = new double[] {0.0, 0.0, 0.1, 0.1};
					dataCollePanelLayout.columnWidths = new int[] {58, 410, 7, 7};
					dataCollePanel.setLayout(dataCollePanelLayout);
					switchPanel.add(dataCollePanel, "jPanel1");
					dataCollePanel.setPreferredSize(new java.awt.Dimension(400, 250));
					{
						part1datacollePanel = new JPanel();
						GridBagLayout part1datacollePanelLayout = new GridBagLayout();
						part1datacollePanelLayout.rowWeights = new double[] {0.0, 0.0, 0.1, 0.1};
						part1datacollePanelLayout.rowHeights = new int[] {36, 72, 7, 7};
						part1datacollePanelLayout.columnWeights = new double[] {0.0, 0.0, 0.1, 0.1};
						part1datacollePanelLayout.columnWidths = new int[] {52, 197, 7, 7};
						part1datacollePanel.setLayout(part1datacollePanelLayout);
						dataCollePanel.add(part1datacollePanel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 1, 17, 0), 0, 0));
						part1datacollePanel.setPreferredSize(new java.awt.Dimension(320, 211));
						part1datacollePanel.setBorder(BorderFactory.createTitledBorder("Record Transitions"));
						//part1datacollePanel.setLayout(new BorderLayout());
						startRecording = new JButton("Start Recording Transitions");
						startRecording.setToolTipText("start a new frame to record a transition");
						part1datacollePanel.add(startRecording, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(14, 0, 0, 0), 0, 0));
						startRecording.setPreferredSize(new java.awt.Dimension(250, 79));
						//startRecording.setText("Start Recording Gestures");
						startRecording.addActionListener(new ButtonListener());
					}
					{
						contRecognition = new JButton();
						contRecognition.setBackground(Color.MAGENTA);
						controlPanel.add(contRecognition, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(37, 0, 300, 0), 0, 0));
						contRecognition.setText("Recognition(cont.)");
						numHMMs = new DataFileOperator(workDir+"/hmmdata").getFileNum();
						if(numHMMs ==0){
							contRecognition.setEnabled(false);
						}
						contRecognition.addActionListener(new ButtonListener()); 
					}
					{
						part2datacollePanel = new JPanel();
						//TableLayout part2datacollePanelLayout1 = new TableLayout(new double[][] {{TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL}, {TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL}});
						//part2datacollePanelLayout1.setHGap(5);
						//part2datacollePanelLayout1.setVGap(5);
						GridBagLayout part2datacollePanelLayout = new GridBagLayout();
						part2datacollePanelLayout.rowWeights = new double[] {0.0, 0.1, 0.1, 0.1};
						part2datacollePanelLayout.rowHeights = new int[] {24, 7, 7, 7};
						part2datacollePanelLayout.columnWeights = new double[] {0.0, 0.0, 0.1, 0.1};
						part2datacollePanelLayout.columnWidths = new int[] {27, 248, 7, 7};
						dataCollePanel.add(part2datacollePanel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.VERTICAL, new Insets(26, 0, 0, 0), 0, 0));
						part2datacollePanel.setPreferredSize(new java.awt.Dimension(320, 211));
						part2datacollePanel.setBorder(BorderFactory.createTitledBorder("Manage Recorded Transitions"));
						part2datacollePanel.setLayout(part2datacollePanelLayout);
						{
							infoLabel = new JLabel();
							part2datacollePanel.add(infoLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							numExamples = new DataFileOperator(workDir+"/sequencedata").getFileNum();
							infoLabel.setText(numExamples+"  transitions recorded!");
						}
						{
							viewExample = new JButton();
							viewExample.setToolTipText("show the collected data");
							part2datacollePanel.add(viewExample, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 6, 0, 0), 0, 0));
							viewExample.setText("View Transitions");
							viewExample.addActionListener(new ButtonListener());
						}
						{
							deleteExample = new JButton();
							part2datacollePanel.add(deleteExample, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 6, 0, 0), 0, 0));
							deleteExample.setText("Delete Transitions!");
							deleteExample.setLocale(new java.util.Locale("en"));
							deleteExample.addActionListener(new ButtonListener());
						}
					}

					trainingPanel = new JPanel();
					GridBagLayout trainingPanelLayout = new GridBagLayout();
					//trainingPanel.setLayout(new BoxLayout(trainingPanel, BoxLayout.LINE_AXIS));
					switchPanel.add(trainingPanel,"jPanel1");
					trainingPanel.setPreferredSize(new java.awt.Dimension(59, 10));
					trainingPanelLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
					trainingPanelLayout.rowHeights = new int[] {207, 207, 177, 7};
					trainingPanelLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
					trainingPanelLayout.columnWidths = new int[] {315, -431, 309, 7};
					trainingPanel.setLayout(trainingPanelLayout);
					{
						featureinfoPanel = new JScrollPane();
						featureinfoPanel.setBorder(BorderFactory.createTitledBorder("Current Feature Vector"));
						trainingPanel.add(featureinfoPanel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(23, 0, 0, 0), 0, 0));
						{
							for(int i =0;i<featureVectorUsed.size();i++){
						    	
						    	listModel.addElement("["+(i+1)+"]"+"      "+allFeatureVector.get(featureVectorUsed.get(i)));
						    }
							fVectorList = new JList();
							featureinfoPanel.setViewportView(fVectorList);
							fVectorList.setModel(listModel);
						}
						/*{
							String[][] columnData =  new DataFileOperator("sequencedata/",featureVectorUsed).tableWriter();
							List titleArray = new ArrayList();
							{
								titleArray.add("Gesture Name");
								for(int i = 0; i<featureVectorUsed.size();i++){
									titleArray.add(allFeatureVector.get(featureVectorUsed.get(i)));
								}
							}
							
							TableModel featureInfoTableModel = new DefaultTableModel(columnData,titleArray.toArray());
							featureInfoTable = new JTable();
							//featureInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
							featureinfoPanel.setViewportView(featureInfoTable);
							featureInfoTable.setModel(featureInfoTableModel);
						}*/
					}
					{
						trainmodelPanel = new JPanel();
						GridBagLayout trainmodelPanelLayout = new GridBagLayout();
						trainmodelPanel.setBorder(BorderFactory.createTitledBorder("Train Models"));
						trainingPanel.add(trainmodelPanel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(9, 0, 23, 0), 0, 0));
						trainmodelPanel.setPreferredSize(new java.awt.Dimension(10, 41));
						trainmodelPanelLayout.rowWeights = new double[] {0.1, 0.0, 0.1, 0.1};
						trainmodelPanelLayout.rowHeights = new int[] {7, 38, 7, 7};
						trainmodelPanelLayout.columnWeights = new double[] {0.1, 0.0, 0.1, 0.1};
						trainmodelPanelLayout.columnWidths = new int[] {7, 245, 7, 7};
						trainmodelPanel.setLayout(trainmodelPanelLayout);
						{
							trainModels = new JButton();
							trainmodelPanel.add(trainModels, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							trainModels.setText("Train Models");
							trainModels.addActionListener(new ButtonListener());
							trainModels.setToolTipText("train the already recorded gestures' model");
						}
						{
							numofModels = new JLabel();
							numExamples = new DataFileOperator(workDir+"/sequencedata").getFileNum();
							trainmodelPanel.add(numofModels, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							numofModels.setText(numExamples+"  models to be trained.");
						}
						{
							viewModels = new JButton();
							trainmodelPanel.add(viewModels, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));
							viewModels.setText("View Models");
							viewModels.addActionListener(new ButtonListener());
						}
					}
					{
						trainingprocessPanel = new JPanel();
						GridBagLayout trainingprocessPanelLayout = new GridBagLayout();
						trainingprocessPanel.setBorder(BorderFactory.createTitledBorder("Training Precess"));
						trainingPanel.add(trainingprocessPanel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						trainingprocessPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
						trainingprocessPanelLayout.rowHeights = new int[] {7, 7, 7, 7};
						trainingprocessPanelLayout.columnWeights = new double[] {0.1, 0.0, 0.1, 0.1};
						trainingprocessPanelLayout.columnWidths = new int[] {7, 235, 7, 7};
						trainingprocessPanel.setLayout(trainingprocessPanelLayout);
						{
							trainingProcess = new JProgressBar();
							trainingProcess.setMaximum(100);
							trainingprocessPanel.add(trainingProcess, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 11), 0, 0));
						}
						{
							cancelTraining = new JButton();
							trainingprocessPanel.add(cancelTraining, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							cancelTraining.setText("Cancel");
						}
						{
							failureInfo = new JLabel();
							trainingprocessPanel.add(failureInfo, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
							//failureInfo.setText(" num1 sucess and num2 failed");
							failureInfo.setHorizontalAlignment(SwingConstants.CENTER);
							failureInfo.setVisible(false);
						}
					}

					recognitionPanel = new JPanel();
					switchPanel.add(recognitionPanel, "jPanel1");
					GridBagLayout recognitionPanelLayout = new GridBagLayout();
					recognitionPanelLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
					recognitionPanelLayout.rowHeights = new int[] {171, 254, 175, 7};
					recognitionPanelLayout.columnWeights = new double[] {0.0, 0.0, 0.1, 0.1};
					recognitionPanelLayout.columnWidths = new int[] {55, 405, 7, 7};
					recognitionPanel.setLayout(recognitionPanelLayout);
					recognitionPanel.setPreferredSize(new java.awt.Dimension(515, 660));
					{
						runRecognition = new JPanel();
						GridBagLayout runRecognitionLayout = new GridBagLayout();
						recognitionPanel.add(runRecognition, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(26, 36, 0, 48), 0, 0));
						runRecognitionLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
						runRecognitionLayout.rowHeights = new int[] {15, 2, 84, 7};
						runRecognitionLayout.columnWeights = new double[] {0.1, 0.0, 0.1, 0.1};
						runRecognitionLayout.columnWidths = new int[] {7, 304, 7, 7};
						runRecognition.setLayout(runRecognitionLayout);
						runRecognition.setBorder(BorderFactory.createTitledBorder("Recognition"));
						{
							runRecognitionB = new JButton();
							runRecognition.add(runRecognitionB, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 7, 0, 0), 0, 0));
							runRecognitionB.setText("Run Recognition");
							runRecognitionB.addActionListener(new ButtonListener());
							runRecognitionB.setToolTipText("start a new frame to record a gesture");
						}
					}
					{
						resultPanel = new JPanel();
						GridBagLayout resultPanelLayout = new GridBagLayout();
						resultPanel.setBorder(BorderFactory.createTitledBorder("Result"));
						recognitionPanel.add(resultPanel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(16, 33, 14, 47), 0, 0));
						resultPanelLayout.rowWeights = new double[] {0.1, 0.0, 0.1, 0.1};
						resultPanelLayout.rowHeights = new int[] {7, 46, 7, 7};
						resultPanelLayout.columnWeights = new double[] {0.0, 0.0, 0.1, 0.1};
						resultPanelLayout.columnWidths = new int[] {133, 154, 7, 7};
						resultPanel.setLayout(resultPanelLayout);
						{
							resultLabel = new JLabel();
							resultLabel.setFont(new java.awt.Font("Dialog", 1, 15));
							resultPanel.add(resultLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							resultLabel.setPreferredSize(new java.awt.Dimension(16, 16));
						}
					}
					{
						evalutatonPanel = new JPanel();
						GridBagLayout evalutatonPanelLayout = new GridBagLayout();
						recognitionPanel.add(evalutatonPanel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(17, 34, 7, 46), 0, 0));
						evalutatonPanelLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
						evalutatonPanelLayout.rowHeights = new int[] {23, 124, 36, 7};
						evalutatonPanelLayout.columnWeights = new double[] {0.0, 0.0, 0.1, 0.1};
						evalutatonPanelLayout.columnWidths = new int[] {17, 292, 7, 7};
						evalutatonPanel.setLayout(evalutatonPanelLayout);
						evalutatonPanel.setBorder(BorderFactory.createTitledBorder("Evaluation"));
					}
					{
						JScrollPane evaluation = new JScrollPane();

						evalutatonPanel.add(evaluation, new GridBagConstraints(0, 0, 4, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						{
							dataAnalysis = new JTextArea();
							evaluation.setViewportView(dataAnalysis);
							dataAnalysis.setText("this area will show the probability of each model during the computation");
							dataAnalysis.setEditable(false);
							dataAnalysis.setLineWrap(true);
						}
                         
					}
					{
						 /*JButton trainingb =new JButton("this is the recognition panel");
						 trainingb.setBorder(BorderFactory.createEtchedBorder(
					                EtchedBorder.RAISED));

						 recognitionPanel.add(trainingb);*/
					}
				}
				{
					conRec = new ContRecPanel(allFeatures, workDir,featureVectorUsed);
					switchPanel.add(conRec, "jPanel1");
					conRec.setPreferredSize(new java.awt.Dimension(512, 636));
				}
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, BorderLayout.SOUTH);
				jLabel1.setText("Designed and Implemented by Tengfei WANG");
				jLabel1.setPreferredSize(new java.awt.Dimension(684, 24));
				jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			}
			pack();
			this.setSize(717, 723);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	

	class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	
            JButton o = (JButton) e.getSource();
            String label = o.getText();
            if(label=="2.Train"){
            	dataCollePanel.setVisible(false);
            	//numExamples = new DataFileOperator(workDir+"/sequencedata/").getFileNum();
            	//numofModels.setText(numExamples+"  models to be trained.");
        	{
                    fVectorList.removeAll();
                    listModel.removeAllElements();
                    for(int i =0;i<featureVectorUsed.size();i++){

                    listModel.addElement("["+(i+1)+"]"+"      "+allFeatureVector.get(featureVectorUsed.get(i)));
                    }
                    fVectorList = new JList();
                    fVectorList.setBackground(Color.cyan);
                    featureinfoPanel.setViewportView(fVectorList);
                    fVectorList.setModel(listModel);
		}
            	trainingPanel.setVisible(true);
            	recognitionPanel.setVisible(false);
            	conRec.setVisible(false);
            }
            if(label=="1.Data Collection"){
                numExamples = new DataFileOperator(workDir+"/sequencedata/").getFileNum();
                infoLabel.setText(numExamples+"  examples recorded!");
            	dataCollePanel.setVisible(true);
            	//dataCollePanel.updateUI();
            	trainingPanel.setVisible(false);
               	recognitionPanel.setVisible(false);
               	conRec.setVisible(false);
            }
            if(label=="3.Recognition"){
            	recognitionPanel.setVisible(true);
            	dataCollePanel.setVisible(false);
            	trainingPanel.setVisible(false);
            	conRec.setVisible(false);
            }
            if(label =="Recognition(cont.)"){
            	
            	conRec.setVisible(true);
            	//conRec.setPreferredSize(new java.awt.Dimension(515, 660));
            	
				recognitionPanel.setVisible(false);
            	dataCollePanel.setVisible(false);
            	trainingPanel.setVisible(false);
           
            }
            if(label=="View Transitions"){
            	ViewExamples newFrame = new ViewExamples(featureVectorUsed,allFeatureVector,workDir);
            	newFrame.setVisible(true);
            }
            if(label=="Delete Transitions!"){
            	
            	GestureModelViewer deleteGesture = new GestureModelViewer(true,training,contRecognition,infoLabel,numofModels,featureVector,workDir);
            	deleteGesture.setVisible(true);
            	/*int isYes= JOptionPane.showConfirmDialog(null, "Are you sure to delete the examples?", "DeleteExamples", JOptionPane.YES_NO_OPTION);
            	if(isYes==JOptionPane.YES_OPTION){
            		
            		DataFileOperator fo = new DataFileOperator("sequencedata/");
                    fo.deletAll();
                    training.setEnabled(false);
                    infoLabel.setText("0  examples recorded!");
                }*/

            }
            if(label =="Start Recording Transitions"){
       		        
            	    unrecordedGesture = new RecordingManager().unrecordedGesture(workDir+"/sequencedata/");
            	    ArrayList<String> keys = new ArrayList<String>(unrecordedGesture.keySet());   
    		    Collections.sort(keys);
            	    //System.out.print(unrecordedGesture);
            	   
                    //gestureName = JOptionPane.showInputDialog(null,"Gesture Name:","Enter Recording Parameters",JOptionPane.QUESTION_MESSAGE);
            	    //Iterator<Entry<String, String>> iter = unrecordedGesture.entrySet().iterator(); 
            	    //while (iter.hasNext()) {
            	    for(int p = 0;p<keys.size();p++){
            	    	 List<List<ObservationVector>> seqs = new ArrayList<List<ObservationVector>>();
            	         //Map.Entry<String, String> entry = (Map.Entry<String,String>) iter.next(); 
            	         //String key = entry.getKey(); 
            	         //String val = entry.getValue();
            	    	 String key = keys.get(p);
            	    	 String val = unrecordedGesture.get(key);
            	    	 //System.out.println(key+val)
                         int flag = 0;
            	    	
            	         for(int i=0;i<3;i++){
            	        
            	             ObservationSequence OS = new ObservationSequence();
                             RecordingGesture recordingPanel = new RecordingGesture(OS,val,key,allFeatures,false);
                       
                             recordingPanel.setModal(true);
                             recordingPanel.setVisible(true);
                             if(OS.leapConnected == false){
                            	 flag = 1;
                            	 break;
                             }
                             if(OS.shouldBreak ==true){
                            	 flag = 2;
                            	 break;
                             }
                             if(OS.userBreak == true){
                            	 flag = 3;
                            	 break;
                             }
                               // System.out.println(OS.observationSequence);
        	    	         seqs.add(OS.observationSequence);
                                         	    	    
            	    	     
            	            }
            	          if(flag ==1){
            	        	  JOptionPane.showMessageDialog(null, "Please connect the leap!", "Error", JOptionPane.ERROR_MESSAGE);
            	        	  break;
            	        	  
            	          }
            	          if(flag ==2){
            	        	  JOptionPane.showMessageDialog(null, "Please put you hand in front of the leap", "Error", JOptionPane.ERROR_MESSAGE);
            	        	  break;
            	          }
            	          if(flag ==3){
            	        	  JOptionPane.showMessageDialog(null, "Recording stopped", "Info", JOptionPane.INFORMATION_MESSAGE);
            	        	  break;
            	          }
                			try {
                				Writer writer = new FileWriter(workDir+"/sequencedata/"+key+".seq");
                				ObservationSequencesWriter.write(writer, new ObservationVectorWriter(), seqs);
                				writer.close();
                				numExamples++;
                				featureVector.setEnabled(false);
                				infoLabel.setText(numExamples+"  examples recorded!");
                			} catch (IOException e1) {
                				// TODO Auto-generated catch block
                				e1.printStackTrace();
                			}
            	    }
            	    if(numExamples !=0){
            	    	training.setEnabled(true);
            	    	trainMenu.setEnabled(true);
            	    }
 
        			
           
            }
            if(label=="Learn Alphabet"){
            	
            	AlphabetPanel ap = new AlphabetPanel();
            	ap.setVisible(true);
            	
            }

            if(label=="Train Models"){
                
                    numExamples = new DataFileOperator(workDir+"/sequencedata/").getFileNum();
                    if(numExamples ==0){
                        JOptionPane.showMessageDialog(null, "No models to be trained!", "Info", JOptionPane.ERROR_MESSAGE);
                    }else{
                    FeatureVectorConfig fvc = new FeatureVectorConfig(allFeatureVector,featureVectorUsed);
                    fvc.setModal(true);
		    fvc.setVisible(true);
                    if(fvc.trainingCancel==false){
                            TrainHmmModels trainModels = new TrainHmmModels(trainingProcess,failureInfo,featureVectorUsed,cancelTraining,workDir);
                  
                            try {
                                    fVectorList.removeAll();
                                    listModel.removeAllElements();
                                    for(int i =0;i<featureVectorUsed.size();i++){

                                    listModel.addElement("["+(i+1)+"]"+"      "+allFeatureVector.get(featureVectorUsed.get(i)));
                                    }
                                    fVectorList = new JList();
                                    fVectorList.setBackground(Color.cyan);
                                    featureinfoPanel.setViewportView(fVectorList);
                                    fVectorList.setModel(listModel);
                                    trainModels.trainAll();
                                 //numHMMs = new DataFileOperator("hmmdata").getFileNum();
                                       // if(numHMMs !=0){
                                    recognition.setEnabled(true);
                                    featureVector.setEnabled(false);
                                    recognitionMenu.setEnabled(true);
                                    contRecognition.setEnabled(true);
                                       // }
                            } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                            }
                        
                    }
                    }
                
       
            }
            if(label == "View Models"){
            	GestureModelViewer viewModels = new GestureModelViewer(false,recognition,contRecognition,infoLabel,numofModels,featureVector,workDir);
            	viewModels.setVisible(true);
            	
            }
            if(label =="Run Recognition"){
            	/*ObservationSequence OS = new ObservationSequence();
            	RecognitionPanel recognitionPanel = new RecognitionPanel(OS);
            	recognitionPanel.setModal(true);
            	recognitionPanel.setVisible(true);
   	    	    observationSequence=OS.observationSequence;
   	    	    System.out.println("hi"+observationSequence);
   	    	    GestureRecognition gestureRecognition = new GestureRecognition(OS,dataAnalysis,resultLabel);
   	    	    gestureRecognition.showResult();*/
            	
            	
            	ObservationSequence OS = new ObservationSequence();
            	RecognitionDataCollection recognitionData = new RecognitionDataCollection(OS,allFeatures);
            	recognitionData.setModal(true);
            	recognitionData.setVisible(true);
            	
          
            	if(OS.leapConnected==false){
            		
            		JOptionPane.showMessageDialog(null, "Please connect the leap!", "Error", JOptionPane.ERROR_MESSAGE);
            	}
            	else if(OS.shouldBreak ==true){
  	        	  JOptionPane.showMessageDialog(null, "Please put you hand in front of the leap", "Error", JOptionPane.ERROR_MESSAGE);
  	            }else{
            	  
        	    GestureRecognition gestureRecognition = new GestureRecognition(OS,dataAnalysis,resultLabel,workDir,featureVectorUsed);
        	    gestureRecognition.showResult();
  	            }
            	
            	//for test only
            	/*ObservationSequence OS = new ObservationSequence();
            	 GestureRecognition gestureRecognition = new GestureRecognition(OS,dataAnalysis,resultLabel);
         	    gestureRecognition.showResult();*/
            	
            	
            }

        }
    }
}

