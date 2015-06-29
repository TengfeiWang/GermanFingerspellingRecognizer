package dataCollection;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import util.DataFileOperator;




/**
* 
* @author Tengfei Wang
*/
public class GestureModelViewer extends javax.swing.JFrame {
	private int exampleNum;
	private String[] names;
	private JButton openButton;
	private JButton deleteButton;
	private JScrollPane containerPanel;
	private JList<String> list;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private boolean isGesture;
	private JButton deleteAll;
	private String dir;
	private JButton button;
        private JButton button1;
	private JLabel label;
        private JLabel numofModels;
	private JMenuItem featureVector;
        private String workDir =null;

		
	public GestureModelViewer(boolean flag,JButton jButton,JButton button1,JLabel jLabel,JLabel numofModels,JMenuItem featureVector,String workDir) {
		super();
		this.isGesture = flag;
                this.workDir = workDir;
		this.button =jButton;
                this.button1 = button1;
		this.label = jLabel;
                this.numofModels =numofModels;
		this.featureVector=featureVector;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			GridBagLayout thisLayout = new GridBagLayout();
			if(isGesture ==true){
			this.setTitle("Delete Gestures");
			}
			else{
				this.setTitle("View Models");
			}
			thisLayout.rowWeights = new double[] {0.1, 0.0, 0.0, 0.1};
			thisLayout.rowHeights = new int[] {7, 247, 53, 7};
			thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
			thisLayout.columnWidths = new int[] {42, 101, 99, 7};
			getContentPane().setLayout(thisLayout);
			{
				containerPanel = new JScrollPane();
				getContentPane().add(containerPanel, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					try{
						if(isGesture ==true){
							   dir = workDir+"/sequencedata/";
							}
							else{
								   dir = workDir+"/hmmdata/";
							}
					   
						  DataFileOperator dataFileOperator= new DataFileOperator(dir);
				          exampleNum = dataFileOperator.getFileNum();
				          names =  new String[exampleNum];
				          names = dataFileOperator.getFileNames();
					 }catch (Exception e) {
				     	    //add your error handling code here
				     		e.printStackTrace();
				     }
					
					
				    for(int i =0;i<exampleNum;i++){
				    	
				    	listModel.addElement(names[i]);
				    }
					list = new JList<String>();
					containerPanel.setViewportView(list);
					list.setModel(listModel);
					//list.setPreferredSize(new java.awt.Dimension(197, 227));
					list.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent evt) {
							//System.out.println("list.valueChanged, event="+evt);
							//TODO add your code for list.valueChanged
							deleteButton.setEnabled(true);
							openButton.setEnabled(true);
						}
					});
				}
			}
			{
				openButton = new JButton();
				getContentPane().add(openButton, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 11), 0, 0));
				openButton.setText("Open");
				if(list.getSelectedIndices().length != 1){
					openButton.setEnabled(false);
				}
				openButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						openButtonActionPerformed(evt);
					}
				});
			}
			{
				deleteButton = new JButton();
				getContentPane().add(deleteButton, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 18, 0, 0), 0, 0));
				deleteButton.setText("Delete");
				if(list.getSelectedIndices().length == 0){
					deleteButton.setEnabled(false);
				}
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						deleteButtonActionPerformed(evt);
					}
				});
			}
			{
				deleteAll = new JButton();
				getContentPane().add(deleteAll, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(28, 0, 0, 0), 0, 0));
				deleteAll.setText("Delete All");
				deleteAll.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("deleteAll.actionPerformed, event="+evt);
						//TODO add your code for deleteAll.actionPerformed
				     	int isYes= JOptionPane.showConfirmDialog(null, "Are you sure to delete all?", "Delete All", JOptionPane.YES_NO_OPTION);
		            	if(isYes==JOptionPane.YES_OPTION){
		            		if(isGesture ==true){
		            		    DataFileOperator fo = new DataFileOperator(workDir+"/sequencedata/");
		                        fo.deletAll();
		                        button.setEnabled(false);
                                        button1.setEnabled(false);
		                        label.setText("0  examples recorded!");
		                        listModel.removeAllElements();
		                       
		                    }
		            		else{
		            			DataFileOperator fo = new DataFileOperator(workDir+"/hmmdata/");
			                    fo.deletAll();
			                    button.setEnabled(false);
                                            button1.setEnabled(false);
                                            numofModels.setText("0 model to be trained.");
			                    listModel.removeAllElements();
		            		}
		            		int numExamples = new DataFileOperator(workDir+"/sequencedata").getFileNum();
							int numHMMs = new DataFileOperator(workDir+"/hmmdata").getFileNum();
							if(numExamples ==0 && numHMMs ==0){
								featureVector.setEnabled(true);
							}
							dispose();
		                }
					}
				});
			}

			pack();
			this.setSize(300, 429);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void deleteButtonActionPerformed(ActionEvent evt) {
		//System.out.println("deleteButton.actionPerformed, event="+evt);
		//TODO add your code for deleteButton.actionPerformed
		int[] selectedItem = list.getSelectedIndices();
		System.out.println(selectedItem[0]);
		
		for(int i = 0; i<selectedItem.length;i++){
			
			DataFileOperator dataFileOperator = new DataFileOperator(dir);
			dataFileOperator.deletByName(names[selectedItem[i]]);
			System.out.println(names[selectedItem[i]]);
			listModel.remove(selectedItem[i]);
		}
		int numExamples = new DataFileOperator(workDir+"/sequencedata").getFileNum();
		int numHMMs = new DataFileOperator(workDir+"/hmmdata").getFileNum();
		if(numExamples ==0 && numHMMs ==0){
			featureVector.setEnabled(true);
		}
	}
	
	private void openButtonActionPerformed(ActionEvent evt) {
		//System.out.println("openButton.actionPerformed, event="+evt);
		//TODO add your code for openButton.actionPerformed
		try{
			int selectedItem = list.getSelectedIndex();
			if(isGesture == true){
				Runtime.getRuntime().exec("cmd /c start notepad "+dir+names[selectedItem]+".seq");
			}
			else{
				Runtime.getRuntime().exec("cmd /c start notepad "+dir+names[selectedItem]+".hmm");			
			}
			
		
			}catch(Exception e2){
				
			}
		
	}

}
