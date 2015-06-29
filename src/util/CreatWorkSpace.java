/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.File;

/**
 *
 * @author Tengfei
 */
public class CreatWorkSpace {
    
    private String dirName= null;
          
    
    public CreatWorkSpace(String dirName){
        
        
        new File(dirName).mkdir();
        System.out.println("Creating workspace in "+dirName+"......");
        new File(dirName+"/hmmdata").mkdir();
        System.out.println("Creating folder 'hmmdata' in workspace ......");
        new File(dirName+"/sequencedata").mkdir();
        System.out.println("Creating folder 'sequencedata' in workspace ......");
        new File(dirName+"/testsequences").mkdir();
        System.out.println("Creating folder 'testsequences' in workspace ......");
        new File(dirName+"/backup").mkdir();
        System.out.println("Creating folder 'backup' in workspace ......");
        new File(dirName+"/logs").mkdir();
        System.out.println("Creating folder 'logs' in workspace ......");
        new File(dirName+"/backup/hmms").mkdir();
        System.out.println("Creating folder 'hmms' in 'backup' ......");
        new File(dirName+"/backup/sequences").mkdir();
        System.out.println("Creating folder 'sequences' in 'backup' ......");
        
        new File(dirName+"/backup/test").mkdir();
        System.out.println("Creating folder 'test' in 'backup' ......");
        System.out.println("workspace built complete...");
        
       
    
    }
    
}
