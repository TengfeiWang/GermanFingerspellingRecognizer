package util;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import userInterface.MainGUI;


/**
 * 
 * @author WangTF
 *
 */

public class FindLeapHome extends javax.swing.JDialog {
        
        private JPanel contentPanel = null;
        private JPanel tipPanel = null;
        private JLabel tipLabel = null;
        private JPanel pathPanel = null;
        private JTextField pathField= null;
        private JButton findButton =null;
        
        private String leapVisualizerDir ;
        
		
	public FindLeapHome(String leapVisualizerDir) {
		super();
		this.leapVisualizerDir = leapVisualizerDir;

		initGUI();
	}
        
        public String getPath(){
            return leapVisualizerDir;
        }
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			//this.setResizable(false);
	                //this.setAlwaysOnTop(true);
                        this.setResizable(false);
			this.setTitle("Set Leap Visulizer Path");
                        contentPanel = new JPanel();
                        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                        tipPanel = new JPanel();
                        tipPanel.setLayout(new BoxLayout(tipPanel, BoxLayout.X_AXIS));
                        tipLabel = new JLabel();
                        tipLabel.setText( "Usually in "+"Leap_Home/Core Services/VisualizerApp.exe");
                        tipPanel.add(tipLabel);
                        pathPanel = new JPanel();
                        pathPanel.setLayout(new BoxLayout(pathPanel, BoxLayout.X_AXIS));
                        pathPanel.setMaximumSize(new Dimension(400,24));
                        pathPanel.setMinimumSize(new Dimension(400,24));
                        contentPanel.add(tipPanel);
                        
                        contentPanel.add(pathPanel);
                        
                        pathField = new JTextField();
                        findButton = new JButton("Browse");
                        findButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						JFileChooser fileChooser = new JFileChooser();
                                                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                                                int returnVal = fileChooser.showOpenDialog(fileChooser);
                                                if(returnVal == JFileChooser.APPROVE_OPTION){
                                                      leapVisualizerDir=fileChooser.getSelectedFile().getAbsolutePath();
                                                      
                                                      System.out.println("leapVisualizerDir"+leapVisualizerDir);
                                                      pathField.setText(leapVisualizerDir);
                                                      try {
                                                          Runtime.getRuntime().exec(leapVisualizerDir);
                                                      } catch (IOException ex) {
                                                          //Logger.getLogger(FindLeapHome.class.getName()).log(Level.SEVERE, null, ex);
                                                      }
                                                }
					}
				});
                        pathPanel.add(Box.createVerticalStrut(20));
                        pathPanel.add(pathField);
                        pathPanel.add(findButton);
                        
                        
			pack();
                        this.add(contentPanel);
			this.setSize(400, 80);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}


}
