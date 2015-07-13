/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recognitionModule;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Vector;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author tengfei
 */
public class GestureTracker extends JFrame{
    
    private JTextArea resultArea ;
    private  Controller controller;
    public GestureTracker(){
        super();
        controller = new Controller();
        controller.addListener(new controllerListener() );
        initGUI();
    }

    private void initGUI() {
       setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       this.setTitle("GestureTracking");
       resultArea = new JTextArea();
       resultArea.setLineWrap(true);
       resultArea.setWrapStyleWord(true);
       //this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
       this.add(resultArea);
       //this.setPreferredSize(new Dimension(500,500));
       pack();
       setSize(520, 700);
      
       this.setVisible(true);
    }

   
   class controllerListener extends Listener{
    
        @Override
        public void onInit(Controller controller)
        {
          
          controller.enableGesture(Gesture.Type.TYPE_SWIPE);
          controller.config().setFloat("Gesture.Swipe.MinLength", 400);
          controller.config().setFloat("Gesture.Swipe.MinVelocity", 2000);
          // enable top mounted policy
          //controller.setPolicyFlags(Controller.PolicyFlag.POLICY_OPTIMIZE_HMD);
        }
        public void onConnect (Controller controller){
            System.out.println("The Leap is Connected!");        
        }
 
        public void onFrame(final Controller controller)
        {

              Frame frame = controller.frame();
              for (Gesture gesture : frame.gestures())
              {

                 if(gesture.type() == Gesture.Type.TYPE_SWIPE) {
                    SwipeGesture swipeGesture = new SwipeGesture(gesture);

                    Vector swipeVector  = swipeGesture.direction();
                    
                    float dimX = Math.abs(swipeVector.getX());
                    float dimY = Math.abs(swipeVector.getY());
                    float dimZ = Math.abs(swipeVector.getZ());
                    System.out.println("swipeVector : " + swipeVector);

                    if(dimX > dimY && dimX > dimZ){
                        
                        if(swipeGesture.direction().getX() >0){
                            resultArea.append("swipe to right");
                        }else{
                            resultArea.append("swipe to left");
                        }
                    }
                    else if(dimY > dimX && dimY>dimZ){
                        if(swipeGesture.direction().getY()>0){
                            resultArea.append("swipe up");
                        }
                        else{
                            resultArea.append("swipe down");
                        }
                    }
                    else{
                        if(swipeGesture.direction().getZ()>0){
                            resultArea.append("swipe backward");
                        }
                        else{
                            resultArea.append("swipe forward");
                        }
                    }
                   
                        
                    float swipeDirection = swipeVector.getX();
                    System.out.println(swipeDirection);
                   }
               }

        }
   }
    	
}

