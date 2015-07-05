package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.swing.JFileChooser;

import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationSequencesReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationVectorReader;

/**Athor Tengfei Wang
 * This class contains 4 methods
 * 1.getFileNum returns the number of files in the folder "dir".
 * 2.deleteAll delete all the files in the folder "dir".
 * 3.getFileNames returns the name(without extension) list of files in folder "dir" 
 * 4.tableWriter reads the files in "sequencedata" folder and represents them in a table.
 */

/**
 * This class contains methods to do data files operation in the system.
 * @author tengfei
 */
public class DataFileOperator {
	
	private String dir;
	private List<Integer> featureVectorUsed;
        private boolean shouldChangeFV = true;
	
	public DataFileOperator(String directory){
		dir= directory;
	}
	public DataFileOperator(String directory,List<Integer> featureVectorUsed){
		dir= directory;
		this.featureVectorUsed = featureVectorUsed;
	}
        
        public DataFileOperator(String directory,List<Integer> featureVectorUsed,boolean shouldChangeFV){
		dir= directory;
                this.shouldChangeFV = shouldChangeFV;
		this.featureVectorUsed = featureVectorUsed;
	}
	
	//import a folder to baseDir
        /**
         * 
         * @param baseDir the default folder of the folder chooser.
         * @return  true if one folder is chosen.
         */
	public boolean chooseFolder(String baseDir){
            boolean isFolderChosen = false;
            String filePath = null;
            JFileChooser fileChooser = new JFileChooser(baseDir);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fileChooser.showOpenDialog(fileChooser);
            if(returnVal == JFileChooser.APPROVE_OPTION){       
                  filePath= fileChooser.getSelectedFile().getAbsolutePath();//the folder path that you choose
                  if(filePath.contains("-")){
                   try {
                      if(filePath!= null){
                          
                            isFolderChosen = true;
                            new DataFileOperator("GFRworkspace"+"/sequencedata/").deletAll();
                            copyDirectiory(filePath, dir);
                            //read feature vector information from featurevector.txt
                            if(shouldChangeFV){
                                
                                 File filename = new File(filePath+"/featurevector.txt"); 
                                 InputStreamReader reader = new InputStreamReader(  
                                         new FileInputStream(filename));  
                                 BufferedReader br = new BufferedReader(reader); 
                                 String line = "";  

                                 featureVectorUsed.clear();
                                 while ((line =br.readLine()) != null) {  
                                     //line = br.readLine(); 
                                     //System.out.println(line);
                                     featureVectorUsed.add(Integer.parseInt(line));
                                 } 
                            }
                      }
                   

                    } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
                  }
                  else{
                      //System.out.println(filePath);
                      return false;
                  }
            } 
           
            return isFolderChosen;
	}
	
	
	/**
         * 
         * @param sourceFile the folder where you want to copy from.
         * @param targetFile the folder where you want to copy tp.
         * @throws IOException 
         */
	public static void copyFile(File sourceFile,File targetFile) throws IOException{

	    FileInputStream input = new FileInputStream(sourceFile);
	    BufferedInputStream inBuff=new BufferedInputStream(input);

	    FileOutputStream output = new FileOutputStream(targetFile);
	    BufferedOutputStream outBuff=new BufferedOutputStream(output);
			        
		byte[] b = new byte[1024 * 5];
		int len;
		while ((len =inBuff.read(b)) != -1) {
		outBuff.write(b, 0, len);
	    }
		
		outBuff.flush();

		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}
        
        
	// copy folder except featurevector.info
	 public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
	     // create new folder
		 (new File(targetDir)).mkdirs();

	         File[] file = (new File(sourceDir)).listFiles();

		 for (int i = 0; i < file.length; i++) {
			   if (file[i].isFile() && file[i].getName().equals("featurevector.txt") == false) {

			      File sourceFile=file[i];

			      File targetFile=new File(new File(targetDir).getAbsolutePath()+File.separator+file[i].getName());
			      copyFile(sourceFile,targetFile);
			   }
			   if (file[i].isDirectory()) {

			       String dir1=sourceDir + "/" + file[i].getName();

			       String dir2=targetDir + "/"+ file[i].getName();
			       copyDirectiory(dir1, dir2);
			    }
		   }
	}

	
	/**
         * 
         * @return the number of files in the folder.
         */
	public int getFileNum() {
        

		File f=new File(dir);
		String[] str=f.list();
		return str.length;
		

	}
	
        
        /**
         * delete all filed in the folder.
         */
	public void deletAll(){
            File f=new File(dir);
            File temp = null;
            String[] str=f.list();
	    for (int i = 0; i < str.length; i++) {
              temp = new File(dir + str[i]);
	      if (temp.isFile()) {
	        temp.delete();
	      }
	    }
	}
	
        
        /**
         * 
         * @param file name without extension 
         */
	public void deletByName(String name){
		File f=new File(dir);
		String[] str=f.list();
		File temp = null;
		for(int i =0;i<str.length;i++){
			
			if(name.equals(str[i].substring(0,str[i].lastIndexOf(".")))){
				
				temp = new File(dir + str[i]);
				if (temp.isFile()) {
				   temp.delete();
			    }
				break;
			}
			
		}
		
	}
	
        
        /**
         * 
         * @return an array of file name in the folder
         */
	public String[] getFileNames(){
		
            File f=new File(dir);
            String[] str=f.list();
            for(int i =0;i<str.length;i++){
                    str[i]=str[i].substring(0,str[i].lastIndexOf("."));
            }
            return str;
	}
	
        
        /**
         * read training sequences data and put the data in a table
         * @return 
         */
	public String[][] tableWriter(){
                String[][] columnData = null;
		try{
		    DataFileOperator dataFileOperator= new DataFileOperator(dir);
		    int exampleNum = dataFileOperator.getFileNum();
		    String[] names =  new String[exampleNum];
		    names = dataFileOperator.getFileNames();//string names stores all the training data file names
		    int rowNum = 0;
		    for(int i =0;i<exampleNum;i++){
		    	 int sequenceNum = 0;
			     Reader reader = new FileReader (dir+names[i]+".seq");
			     List<List <ObservationVector>> seqs = ObservationSequencesReader.readSequences(new ObservationVectorReader(),  reader);
			     sequenceNum = seqs.size();
			     for(int j = 0; j<sequenceNum;j++){
			    	 rowNum +=seqs.get(j).size();
			     }
			     reader.close ();
		    }
		
		    //System.out.println(rowNum);

		/*for(int i =0;i<rowNum;i++){
			for(int j=0;j<21;j++)
				columnData[i][j]="0";
		}*/
		    
	
                    columnData = new String[rowNum][featureVectorUsed.size()+1];
                    int currentRowNumBottom = 0;
                    int currentRowNumTop=0;
                    int currentRowNum = 0;
                    for(int i =0;i<exampleNum;i++){
                            Reader reader = new FileReader ("GFRworkspace"+"/sequencedata/"+names[i]+".seq");
                            List<List <ObservationVector >> seqs =  ObservationSequencesReader.readSequences (new ObservationVectorReader (),  reader);
                            for(int m= 0;m<seqs.size();m++){
                                 currentRowNumTop += seqs.get(m).size();
                            }
                            for(int j=currentRowNumBottom;j<currentRowNumTop;j++){
                                 columnData[j][0]=names[i];

                            }
                            currentRowNumBottom= currentRowNumTop;


                            for(int sequenceNum = 0;sequenceNum<3;sequenceNum++){
                                    for(int currentSeqIndex = 0;currentSeqIndex<seqs.get(sequenceNum).size();currentSeqIndex++){
                                         for(int k = 1;k<(featureVectorUsed.size()+1);k++){

                                          columnData[currentRowNum][k]=String.valueOf(seqs.get(sequenceNum).get(currentSeqIndex).value(k-1));
                                          }
                                         currentRowNum ++;
                                    }
                            }

                            reader.close ();


                    }
                }catch (Exception e) {
                    //add your error handling code here
                        e.printStackTrace();
                }
		return columnData;
	}

}
