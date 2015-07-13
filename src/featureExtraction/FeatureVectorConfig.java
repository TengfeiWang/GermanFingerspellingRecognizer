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
 * A dialog where the user can modify the feature vector used to train an HMM.
 * @author Tengfei Wang
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
        private JCheckBox ringHandCenter;
        private JCheckBox pinkyHandCenter;
        
        private JCheckBox thumbIndex;
        private JCheckBox indexMiddle;
        private JCheckBox middleRing;
        private JCheckBox ringPinky;
        
        private JCheckBox fingerCount;
	private JCheckBox indexOpenness;
        
	private JCheckBox thumbIndexAngle;
	private JCheckBox indexMiddleAngle;
	private JCheckBox middleRingAngle;
	private JCheckBox ringPinkyAngle;
	private JCheckBox thumbPalmNormal;
	private JCheckBox indexPalmNormal;
	private JCheckBox middlePalmNormal;
        private JCheckBox ringPalmNormal;
        private JCheckBox pinkyPalmNormal;
	
        private JCheckBox handRotationAngel;
        private JCheckBox handTranslation;
        private JCheckBox dotProduct;
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
                        /*allFeatureVector.put(1, "Thumb to Hand Center");
                        allFeatureVector.put(2, "Index to Hand Center");
                        allFeatureVector.put(3, "Middle to hand center");
                        allFeatureVector.put(4, "Ring to hand center");
                        allFeatureVector.put(5, "Pinkey to Hand Center");
                        allFeatureVector.put(6, "Extended finger count");

                        allFeatureVector.put(7, "Index Openness");

                        allFeatureVector.put(8, "thumb index angle");
                        allFeatureVector.put(9, "index middle angle");
                        allFeatureVector.put(10, "middle ring angle");
                        allFeatureVector.put(11, "ring pinky angke");
                        allFeatureVector.put(12,"thumb palmnormal angle" );
                        allFeatureVector.put(13,"index palmnormal angle" );
                        allFeatureVector.put(14,"middle palmnormal angle" );
                        allFeatureVector.put(15,"ring palmnormal angle" );
                        allFeatureVector.put(16,"pinky palmnormal angle" );
                        allFeatureVector.put(17, "hand rotation angel");
                        allFeatureVector.put(18, "hand position change magnitude");
                        allFeatureVector.put(19 "dot product hand movement vector and first frame hand normal"):*/
			{
				thumbHandCenter = new JCheckBox();
				checkBoxPane.add(thumbHandCenter);
				thumbHandCenter.setText("Thumb to Hand Center[1]");
			}
			{
				indexHandCenter = new JCheckBox();
				checkBoxPane.add(indexHandCenter);
				indexHandCenter.setText("Index to Hand Center[2]");
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
				indexMiddle = new JCheckBox();
				checkBoxPane.add(indexMiddle);
				indexMiddle.setText("Index to Middle[7]");
			}
                        {
				middleRing = new JCheckBox();
				checkBoxPane.add(middleRing);
				middleRing.setText("Middle to Ring[8]");
			}
                        {
				ringPinky = new JCheckBox();
				checkBoxPane.add(ringPinky);
				ringPinky.setText("Ring to Pinky[9]");
			}
                        
                        
                        
                        
                        
                        {
                                fingerCount = new JCheckBox();
				checkBoxPane.add(fingerCount);
				fingerCount.setText("Extended Fingers Count[10]");
			}
			{
				indexOpenness = new JCheckBox();
				checkBoxPane.add(indexOpenness);
				indexOpenness.setText("Index Finger Openness[11]");
			}
			{
				thumbIndexAngle = new JCheckBox();
				checkBoxPane.add(thumbIndexAngle);
				thumbIndexAngle.setText("Thumb Index Angle[12]");
			}
			{
				indexMiddleAngle = new JCheckBox();
				checkBoxPane.add(indexMiddleAngle);
				indexMiddleAngle.setText("Index Middle Angle[13]");
			}
			{
				middleRingAngle = new JCheckBox();
				checkBoxPane.add(middleRingAngle);
				middleRingAngle.setText("Middle Ring Angle[14]");
			}
			{
				ringPinkyAngle = new JCheckBox();
				checkBoxPane.add(ringPinkyAngle);
				ringPinkyAngle.setText("Ring Pinky Angle[15]");
			}
			{
				thumbPalmNormal = new JCheckBox();
				checkBoxPane.add(thumbPalmNormal);
				thumbPalmNormal.setText("Thumb Palmnormal Angle[16]");
			}
			{
				indexPalmNormal = new JCheckBox();
				checkBoxPane.add(indexPalmNormal);
				indexPalmNormal.setText("Index Palmnormal Angle[17]");
			}
                        {
				middlePalmNormal = new JCheckBox();
				checkBoxPane.add(middlePalmNormal);
				middlePalmNormal.setText("Middle Palmnormal Angle[18]");
			}
                        {
				ringPalmNormal = new JCheckBox();
				checkBoxPane.add(ringPalmNormal);
				ringPalmNormal.setText("Ring Palmnormal Angle[19]");
			}
                        {
				pinkyPalmNormal = new JCheckBox();
				checkBoxPane.add(pinkyPalmNormal);
				pinkyPalmNormal.setText("Pinky Palmnormal Angle[20]");
			}
                        {
				handRotationAngel = new JCheckBox();
				checkBoxPane.add(handRotationAngel);
				handRotationAngel.setText("Hand Rotation Angel[21]");
			}
                        {
				handTranslation = new JCheckBox();
				checkBoxPane.add(handTranslation);
				handTranslation.setText("Hand Position Change[22]");
			}
                        {
                                dotProduct = new JCheckBox();
                                checkBoxPane.add(dotProduct);
                                dotProduct.setText("Dot Product[23] ");
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
                if(indexMiddle.isSelected()){
			featureSelected.add(7);
			counter++;
		}
                if(middleRing.isSelected()){
			featureSelected.add(8);
			counter++;
		}
                if(ringPinky.isSelected()){
			featureSelected.add(9);
			counter++;
		}
                
		if(fingerCount.isSelected()){
			featureSelected.add(10);
			counter++;
		}
		if(indexOpenness.isSelected()){
			featureSelected.add(11);
			counter++;
		}
		if(thumbIndexAngle.isSelected()){
			featureSelected.add(12);
			counter++;
		}
		if(indexMiddleAngle.isSelected()){
			featureSelected.add(13);
			counter++;
		}
		if(middleRingAngle.isSelected()){
			featureSelected.add(14);
			counter++;
		}
		if(ringPinkyAngle.isSelected()){
			featureSelected.add(15);
			counter++;
		}
		if(thumbPalmNormal.isSelected()){
			featureSelected.add(16);
			counter++;
		}
                if(indexPalmNormal.isSelected()){
			featureSelected.add(17);
			counter++;
		}
                if(middlePalmNormal.isSelected()){
			featureSelected.add(18);
			counter++;
		}
                if(ringPalmNormal.isSelected()){
			featureSelected.add(19);
			counter++;
		}
                if(pinkyPalmNormal.isSelected()){
			featureSelected.add(20);
			counter++;
		}
                if(handRotationAngel.isSelected()){
			featureSelected.add(21);
			counter++;
		}
                if(handTranslation.isSelected()){
			featureSelected.add(22);
			counter++;
		}
                if(dotProduct.isSelected()){
			featureSelected.add(23);
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
