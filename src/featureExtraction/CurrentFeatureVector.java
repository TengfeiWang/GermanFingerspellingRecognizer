package featureExtraction;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.DataFileOperator;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 * 
 * @author WangTF
 *
 */


public class CurrentFeatureVector extends javax.swing.JDialog {
	private JScrollPane featureVectorPabel;
	private JButton changButton;
	private JButton okButton;
	private  List<Integer> featureVectorUsed;
	private HashMap<Integer,String> allFeatureVector;
    private JList fVectorList;
    private DefaultListModel listModel = new DefaultListModel();
    private JButton training;
    private JButton recognition;
    private JLabel infoLabel;
	/**
	* Auto-generated main method to display this JDialog
	*/
		
	public CurrentFeatureVector(List<Integer> featureVectorUsed,HashMap<Integer,String> allFeatureVector,JButton training,JButton recognition,JLabel infoLabel) {
		super();
		this.featureVectorUsed =featureVectorUsed;
		this.allFeatureVector = allFeatureVector;
		this.training = training;
		this.recognition = recognition;
		this.infoLabel = infoLabel;
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				
				GridBagLayout thisLayout = new GridBagLayout();
				this.setTitle("Current Feature Vector");
				thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				thisLayout.rowHeights = new int[] {107, 66, 148, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.0, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 83, 7, 7};
				getContentPane().setLayout(thisLayout);
				this.setAlwaysOnTop(true);
				{
					featureVectorPabel = new JScrollPane();
					//featureVectorPabel.setBackground(Color.blue);
					getContentPane().add(featureVectorPabel, new GridBagConstraints(0, 0, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					featureVectorPabel.setPreferredSize(new java.awt.Dimension(298, 353));
					  for(int i =0;i<featureVectorUsed.size();i++){
					    	
					    	listModel.addElement("["+(i+1)+"]"+"      "+allFeatureVector.get(featureVectorUsed.get(i)));
					    }
						fVectorList = new JList();
						fVectorList.setBackground(Color.LIGHT_GRAY);
						featureVectorPabel.setViewportView(fVectorList);
						fVectorList.setModel(listModel);
				}
		
				{
					changButton = new JButton();
					getContentPane().add(changButton, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					changButton.setText("CHANGE");
					changButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//System.out.println("changButton.actionPerformed, event="+evt);
							//TODO add your code for changButton.actionPerformed
						    int isYes= JOptionPane.showConfirmDialog(null, "You have to delete model data before changing the feature vector!", "Info", JOptionPane.YES_NO_OPTION);
			            	            if(isYes==JOptionPane.YES_OPTION){
			          
                                                            DataFileOperator fh = new DataFileOperator("hmmdata/");
                                                            fh.deletAll();
                                                            
                                                            recognition.setEnabled(false);
			                                   
							    FeatureVectorConfig fvc= new FeatureVectorConfig(allFeatureVector,featureVectorUsed);
							    fvc.setVisible(true);
							    dispose();
			            	            }
						}
					});
				}
				{
					okButton = new JButton();
					getContentPane().add(okButton, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 13, 0, 12), 0, 0));
					okButton.setText("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//System.out.println("okButton.actionPerformed, event="+evt);
							//TODO add your code for okButton.actionPerformed
							dispose();
						}
					});
				}
			}
			this.setSize(300, 444);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
