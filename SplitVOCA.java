package readWrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class SplitVOCA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileInputStream fstream;
		
		int bukCtr = -1;
		int bciCtr = -1;
		String extractType = "";
		String Hdr = "";
		String Trl = "";
		String tempChunk ="";
		Map<Integer,String> BUKSet = new HashMap<Integer,String>();
		Map<Integer,String> BCISet = new HashMap<Integer,String>();
		String strLine;
		try {
			fstream = new FileInputStream("VOCA.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
			while ((strLine = br.readLine()) != null)   {
				  // Print the content on the console
					//System.out.println(strLine);
					//System.out.println (extractNumber(strLine));
				if(strLine.contains("HEADER")){
					Hdr = strLine + "\n";
				}
				else if(strLine.contains("STARTYPEBUK"))    
				{
					bukCtr++;
					extractType = "BUK";
				}
				else if(strLine.contains("ENDTYPEBUK"))    
				{
					extractType = "";
					BUKSet.put(bukCtr, tempChunk);
					tempChunk ="";
				}
				else if(strLine.contains("STARTYPEBCI"))    
				{
					bciCtr++;
					extractType = "BCI";
				}
				else if(strLine.contains("ENDTYPEBCI"))    
				{
					extractType = "";
					BCISet.put(bciCtr, tempChunk);
					tempChunk ="";
				}
				else if(strLine.contains("TRAILER"))    
				{
					Trl = strLine + "\n";
				}
				else{
				if(extractType.equalsIgnoreCase("BUK")){
					tempChunk = tempChunk + strLine + "\n";
				}
				else if(extractType.equalsIgnoreCase("BCI")){
					tempChunk = tempChunk + strLine + "\n";
				}
				
				}
				
				}
				  
				
				//Close the input stream
				br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(BUKSet.size() > 0){
			
		    try {
		    	
		        File file = new File("BUKFile.txt");
		        if (!file.exists()) {
		            file.createNewFile();
		        }
		        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		        BufferedWriter bw = new BufferedWriter(fw);
		  
		        
				bw.write(Hdr + BUKSet.values().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(", ", "") + Trl);
		        bw.close();
		     }
		    catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		if(BCISet.size() > 0){
			
		    try {
		    	
		        File file = new File("BCIFile.txt");
		        if (!file.exists()) {
		            file.createNewFile();
		        }
		        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		        BufferedWriter bw = new BufferedWriter(fw);
		  
		        bw.write(Hdr + BCISet.values().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(", ", "") + Trl);
			        bw.close();
		     }
		    catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
	}


}
