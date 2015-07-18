/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recognitionModule;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Vector;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author tengfei
 */
public class GestureTracker extends JFrame{
    
    private JTextArea resultArea ;
    private JScrollPane containerPanel;
    private  Controller controller;
    public GestureTracker(){
        super();
        controller = new Controller();
        controller.addListener(new controllerListener() );
        initGUI();
    }

    private void initGUI() {
        try{
           setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
           this.setTitle("GestureTracking");
           containerPanel = new JScrollPane();

           resultArea = new JTextArea();
           resultArea.setLineWrap(true);
           resultArea.setWrapStyleWord(true);
           containerPanel.add(resultArea);
           containerPanel.setViewportView(resultArea);
           //this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
           this.add(containerPanel);
           //this.setPreferredSize(new Dimension(500,500));
           pack();
           setSize(520, 700);

           this.setVisible(true);
        }catch (Exception e) {
                    //add your error handling code here
		e.printStackTrace();
	}
       
    }

   
   class controllerListener extends Listener{
    
        @Override
        public void onInit(Controller controller)
        {
          
          controller.enableGesture(Gesture.Type.TYPE_SWIPE);
          controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
          controller.config().setFloat("Gesture.Swipe.MinLength", 5000);
          controller.config().setFloat("Gesture.Swipe.MinVelocity", 5000);
          // enable top mounted policy
          //controller.setPolicyFlags(Controller.PolicyFlag.POLICY_OPTIMIZE_HMD);
        }
        public void onConnect (Controller controller){
            System.out.println("The Leap is Connected!");        
        }
 
        public void onFrame(final Controller controller)
        {

              Frame frame = controller.frame();
              Frame previous = controller.frame(1);
              Hand hand = frame.hands().get(0);
              FingerList fingers=hand.fingers();
              int extendedFingersNum = hand.fingers().extended().count();
              
              boolean flag =false;
              for (Gesture gesture : frame.gestures())
              {
                
                 if( gesture.type() == Gesture.Type.TYPE_SWIPE ) {
                     
                    
                    SwipeGesture swipeGesture = new SwipeGesture(gesture);
                    
                    for(Gesture gestureLast : previous.gestures()){
                        if(gestureLast.id() == swipeGesture.id()){
                            //System.out.println("kengdie");
                            flag =true;
                            break;
                        }
                        
                               
                    }
                    if(flag == false){
                        Vector swipeVector  = swipeGesture.direction();
                    System.out.println(swipeGesture.id());
                    float dimX = Math.abs(swipeVector.getX());
                    float dimY = Math.abs(swipeVector.getY());
                    float dimZ = Math.abs(swipeVector.getZ());
                    //System.out.println("swipeVector : " + swipeVector);

                    if(dimX > dimY && dimX > dimZ){
                        
                        if(swipeGesture.direction().getX() >0){
                            if(extendedFingersNum ==1){
                                resultArea.append("1 finger swipe right");
                            }else if(extendedFingersNum ==2){
                                resultArea.append("2 fingers swipe right");
                            }else if(extendedFingersNum ==3){
                                resultArea.append("3 fingers swipe right");
                            }else{
                                resultArea.append("full fingers swipe right");
                            }
                                
                            
                        }else{
                             if(extendedFingersNum ==1){
                                resultArea.append("1 finger swipe left");
                            }else if(extendedFingersNum ==2){
                                resultArea.append("2 fingers swipe left");
                            }else if(extendedFingersNum ==3){
                                resultArea.append("3 fingers swipe left");
                            }else{
                                resultArea.append("full fingers swipe left");
                            }
                        }
                    }
                    else if(dimY > dimX && dimY>dimZ){
                        if(swipeGesture.direction().getY()>0){
                            if(extendedFingersNum ==1){
                                resultArea.append("1 finger swipe up");
                            }else if(extendedFingersNum ==2){
                                resultArea.append("2 fingers swipe up");
                            }else if(extendedFingersNum ==3){
                                resultArea.append("3 fingers swipe up");
                            }else{
                                resultArea.append("full fingers swipe up");
                            }
                        }
                        else{
                            if(extendedFingersNum ==1){
                                resultArea.append("1 finger swipe down");
                            }else if(extendedFingersNum ==2){
                                resultArea.append("2 fingers swipe down");
                            }else if(extendedFingersNum ==3){
                                resultArea.append("3 fingers swipe down");
                            }else{
                                resultArea.append("full fingers swipe down");
                            }
                        }
                    }
                    else{
                        if(swipeGesture.direction().getZ()>0){
                            if(extendedFingersNum ==1){
                                resultArea.append("1 finger swipe backward");
                            }else if(extendedFingersNum ==2){
                                resultArea.append("2 fingers swipe backward");
                            }else if(extendedFingersNum ==3){
                                resultArea.append("3 fingers swipe backward");
                            }else{
                                resultArea.append("full fingers swipe backward");
                            }
                        }
                        else{
                              if(extendedFingersNum ==1){
                                resultArea.append("1 finger swipe forward");
                            }else if(extendedFingersNum ==2){
                                resultArea.append("2 fingers swipe forward");
                            }else if(extendedFingersNum ==3){
                                resultArea.append("3 fingers swipe forward");
                            }else{
                                resultArea.append("full fingers swipe forward");
                            }
                        }
                    }
                    }
                    
                        
                    //float swipeDirection = swipeVector.getX();
                    //System.out.println(swipeDirection);
                   }
                 
                 
                 
                 //else if(gesture.type() == Gesture.Type.TYPE_CIRCLE){
                   //         resultArea.append("  circle ");
                   //}
               }

        }
   }
    	
}

