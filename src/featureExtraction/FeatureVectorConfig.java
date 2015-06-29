package featureExtraction;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
 * 
 * @author WangTF
 *
 */

public class FeatureVectorConfig extends javax.swing.JDialog {
        private JLabel infoLabel = null;
        private JLabel pictureLabel;
        private JScrollPane checkBoxScrollPane=null;
        private JPanel checkBoxPane = null;
        private JPanel bigPanel;
        private JPanel secondPanel;
        private JPanel thirdPanel;
        private JPanel picturePanel;
        private JPanel buttonPanel;
        private JPanel infoLabelPanel;
        private JPanel currentFVPanel;
        private JLabel currentFVLabel;
	private JCheckBox thumbHandCenter;
	private JCheckBox indexHandCenter;
	private JCheckBox middleHandCenter;
	private JCheckBox indexMiddle;	
	private JCheckBox middletoRing;
	private JCheckBox pinkytoRing;
	private JCheckBox thumbPinky;
	private JCheckBox thumbRing;
	private JCheckBox thumbMiddle;
	private JCheckBox thumbIndex;
	private JCheckBox pinkyHandCenter;
	private JCheckBox ringHandCenter;
        private JCheckBox handRotationAngel;
        private JCheckBox handTranslation;
        private JButton okButton;
        private JButton remainButton;
	private HashMap<Integer,String> allFeatureVector = new HashMap<Integer,String>();
	private List<Integer> featureVectorUsed;
        public boolean trainingCancel =true;
	/**
	* Auto-generated main method to display this JFrame
	*/
		
	public FeatureVectorConfig( HashMap<Integer,String> allFeatureVector, List<Integer> featureVectorUsed) {
		super();
		this.allFeatureVector =allFeatureVector;
		this.featureVectorUsed = featureVectorUsed;
                
		System.out.println(allFeatureVector);
		System.out.println(featureVectorUsed);
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			//this.setResizable(false);
	                this.setAlwaysOnTop(true);
		
			this.setTitle("Feature Vector Config...");
                        bigPanel = new JPanel();
                        bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.X_AXIS));
                        bigPanel.add(Box.createHorizontalStrut(10));
                        secondPanel = new JPanel();
                        secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.Y_AXIS));
			checkBoxScrollPane = new JScrollPane();
                        checkBoxPane = new JPanel();
                        checkBoxPane.setLayout(new BoxLayout(checkBoxPane, BoxLayout.Y_AXIS));
			checkBoxScrollPane.add(checkBoxPane);
                        checkBoxScrollPane.setViewportView(checkBoxPane);
                        
                        infoLabelPanel = new JPanel();
                        infoLabelPanel.setLayout(new BoxLayout(infoLabelPanel, BoxLayout.X_AXIS));
                        infoLabel = new JLabel("Please choose at leat one item to form the Feature Vector.");
                        infoLabelPanel.add(infoLabel);
                        secondPanel.add(Box.createVerticalStrut(10));
                        secondPanel.add(infoLabelPanel);
                        secondPanel.add(Box.createVerticalStrut(10));
                        secondPanel.add(checkBoxScrollPane);
                        secondPanel.add(Box.createVerticalStrut(10));
			{
				thumbHandCenter = new JCheckBox();
				checkBoxPane.add(thumbHandCenter);
				thumbHandCenter.setText("Thumb to Hand Center[1]");
			}
			{
				indexHandCenter = new JCheckBox();
				checkBoxPane.add(indexHandCenter);
				indexHandCenter.setText("Index Finger Openness[2]");
			}
			{
				middleHandCenter = new JCheckBox();
				checkBoxPane.add(middleHandCenter);
				middleHandCenter.setText("Middle to Hand Center[3]");
			}
			{
				ringHandCenter = new JCheckBox();
				checkBoxPane.add(ringHandCenter);
				ringHandCenter.setText("Ring to Hand Center[4]");
			}
			{
				pinkyHandCenter = new JCheckBox();
				checkBoxPane.add(pinkyHandCenter);
				pinkyHandCenter.setText("Pinkey to Hand Center[5]");
			}
			{
				thumbIndex = new JCheckBox();
				checkBoxPane.add(thumbIndex);
				thumbIndex.setText("Thumb to Index[6]");
			}
			{
				thumbMiddle = new JCheckBox();
				checkBoxPane.add(thumbMiddle);
				thumbMiddle.setText("Thumb to Middle[7]");
			}
			{
				indexMiddle = new JCheckBox();
				checkBoxPane.add(indexMiddle);
				indexMiddle.setText("Index to Middle[8]");
			}
			{
				thumbRing = new JCheckBox();
				checkBoxPane.add(thumbRing);
				thumbRing.setText("Thumb to Ring[9]");
			}
			{
				thumbPinky = new JCheckBox();
				checkBoxPane.add(thumbPinky);
				thumbPinky.setText("Thumb to Pinky[10]");
			}
			{
				pinkytoRing = new JCheckBox();
				checkBoxPane.add(pinkytoRing);
				pinkytoRing.setText("Pinky to Ring[11]");
			}
			{
				middletoRing = new JCheckBox();
				checkBoxPane.add(middletoRing);
				middletoRing.setText("Middle to Ring[12]");
			}
                        {
				handRotationAngel = new JCheckBox();
				checkBoxPane.add(handRotationAngel);
				handRotationAngel.setText("Hand Rotation Angel[13]");
			}
                        {
				handTranslation = new JCheckBox();
				checkBoxPane.add(handTranslation);
				handTranslation.setText("Hand Position Change[14]");
			}
			{
                                buttonPanel = new JPanel();
			
                                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
                                okButton = new JButton("OK");
                                buttonPanel.add(Box.createHorizontalGlue());
				buttonPanel.add(okButton);
                                buttonPanel.add(Box.createHorizontalGlue());
                                remainButton = new JButton("Use Current FV");
                                remainButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
                                            trainingCancel =false;
						dispose();
					}
				});
                                
                              
                                buttonPanel.add(remainButton);
                                buttonPanel.add(Box.createHorizontalGlue());
				
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						okButtonActionPerformed(evt);
					}
				});
                                secondPanel.add(buttonPanel);
			}
                        bigPanel.add(secondPanel);
                        picturePanel = new JPanel();
                        picturePanel.setVisible(true);
                        thirdPanel = new JPanel();
                        thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));
                        
                        currentFVPanel = new JPanel();
                        currentFVLabel = new JLabel("Current Feature Vector: "+featureVectorUsed.toString());
                        
                        currentFVPanel.add(currentFVLabel);
                        //ImageIcon icon = new ImageIcon("src/images/features.png");
                        pictureLabel = new JLabel();
                        pictureLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/features.png")));
                        
                        picturePanel.add(pictureLabel);
                        thirdPanel.add(currentFVPanel);
                        thirdPanel.add(picturePanel);
                        bigPanel.add(thirdPanel);
                        this.add(bigPanel);
			pack();
			//this.setSize(300, 416);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void okButtonActionPerformed(ActionEvent evt) {
                trainingCancel =false;
		int counter =0;
		List<Integer> featureSelected = new ArrayList();
		

		System.out.println(featureVectorUsed.size());
		if(thumbHandCenter.isSelected()){
			featureSelected.add(1);
			counter++;
		}
		if(indexHandCenter.isSelected()){
			featureSelected.add(2);
			counter++;
		}
		if(middleHandCenter.isSelected()){
			featureSelected.add(3);
			counter++;
		}
		if(ringHandCenter.isSelected()){
			featureSelected.add(4);
			counter++;
		}
		if(pinkyHandCenter.isSelected()){
			featureSelected.add(5);
			counter++;
		}
		if(thumbIndex.isSelected()){
			featureSelected.add(6);
			counter++;
		}
		if(thumbMiddle.isSelected()){
			featureSelected.add(7);
			counter++;
		}
		if(indexMiddle.isSelected()){
			featureSelected.add(8);
			counter++;
		}
		if(thumbRing.isSelected()){
			featureSelected.add(9);
			counter++;
		}
		if(thumbPinky.isSelected()){
			featureSelected.add(10);
			counter++;
		}
		if(pinkytoRing.isSelected()){
			featureSelected.add(11);
			counter++;
		}
		if(middletoRing.isSelected()){
			featureSelected.add(12);
			counter++;
		}
                if(handRotationAngel.isSelected()){
			featureSelected.add(13);
			counter++;
		}
                if(handTranslation.isSelected()){
			featureSelected.add(14);
			counter++;
		}
		if(counter ==0){
			JOptionPane.showMessageDialog(null, "Please select at least one item", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			featureVectorUsed.clear();
			
			for(int i =0 ;i<featureSelected.size();i++){
				featureVectorUsed.add(featureSelected.get(i));
			}
			this.dispose();
			
		}
		System.out.println("Feature Vector changed to: "+featureVectorUsed);
	}

}
