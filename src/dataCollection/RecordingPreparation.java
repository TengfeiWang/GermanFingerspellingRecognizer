/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataCollection;

import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationSequencesWriter;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationVectorWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import util.ObservationSequence;
import util.RecordingManager;

/**
 * preparation for test sequences recording.
 * @author Tengfei Wang
 */
public class RecordingPreparation {
    
    private Map<String ,String> unrecordedGesture;
    private List<Integer> allFeatureVector;
    private String workDir;
    
    public RecordingPreparation(Map<String ,String> unrecordedGesture,List<Integer> allFeatureVector,String workDir){
        this.unrecordedGesture =unrecordedGesture;
        this.allFeatureVector =allFeatureVector;
        this.workDir = workDir;
    }
    
    public void ManageRecording(){
       
        ArrayList<String> keys = new ArrayList<String>(unrecordedGesture.keySet());   
        Collections.sort(keys);


        for(int p = 0;p<keys.size();p++){
             List<List<ObservationVector>> seqs = new ArrayList<List<ObservationVector>>();

             String key = keys.get(p);
             String val = unrecordedGesture.get(key);
             //System.out.println(key+val)
             int flag = 0;

             for(int i =0;i<1;i++){

                 ObservationSequence OS = new ObservationSequence();
                 RecordingGesture recordingPanel = new RecordingGesture(OS,val,key,allFeatureVector,true);

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

                     //System.out.println("hi"+observationSequence);
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
                                    Writer writer = new FileWriter(workDir+"/testsequences/"+key+".seq");
                                    ObservationSequencesWriter.write(writer, new ObservationVectorWriter(), seqs);
                                    writer.close();


                            } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                            }
        }
    }
    
}
