package Autosys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AutosysResponse.RunBatch;

public class extractPhoneNumber {

	private Process prt ;
	public void setProcess(Process fetchPBP){
		prt = fetchPBP;
	}
	public Process getProcess()
	{
		return prt;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		extractPhoneNumber epn = new extractPhoneNumber();
		epn.readFile();
		//epn.getPointsInfo("9810101893");
		
	}
public void readFile() throws IOException
{
	// Open the file
	FileInputStream fstream = new FileInputStream("WhatsApp.txt");
	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
    Set<String> s = new HashSet<String>();
    Integer NumberOfExtraction = 0;
	String strLine;

	//Read File Line By Line
	while ((strLine = br.readLine()) != null)   {
	  // Print the content on the console
		//System.out.println(strLine);
		//System.out.println (extractNumber(strLine));
	    String PhNo = extractNumber(strLine).trim(); 
		if(PhNo != ""){
		s.add(PhNo);
		}
	    NumberOfExtraction++;
	}
	//Close the input stream
	br.close();
	System.out.println(NumberOfExtraction + "   Count of Numbers:" + s.size() + s.toString());
	int numOfRes=0;
	for(String PNO : s){
		
		   if(getPointsInfo(PNO)){
			   numOfRes++;
				System.out.println(numOfRes + ") Done:"+PNO);
			}
			else{
				System.out.println("Error:"+PNO);
			}
		}
	GetPayBackDetails gpb = new GetPayBackDetails();
	gpb.sendGet(s);
}


public String extractNumber(String line)
{
	 // String to be scanned to find the pattern.
    //String line = "This order was placed for QT3000! OK?";
   String result = "";
	String pattern = "(\\+91[0-9]{10})";

    // Create a Pattern object
    Pattern r = Pattern.compile(pattern);
    line = line.replaceAll(" ", "");
    // Now create matcher object.
    Matcher m = r.matcher(line);
     try{
    	 if (m.find( )) {
     
      // System.out.println("Found value: " + m.group(0) );
       result=  m.group(0).substring(3);
           }}
     catch(Exception e){
    	 e.printStackTrace();
    	 result = "";
     }
    return result;
}

public boolean getPointsInfo(String PhNo){
	ResponseWriter("@echo off \r\n GetPayBackInfo.bat " + PhNo + ">>Response.xml");
	
	//RunBatch rb = new RunBatch();
	//rb.runBat("Execute.bat", "");
	if(GPBBAT(PhNo)){
		//System.out.println("Success");
		return true;
	}
	else{
		//System.out.println("Failure");
		return false;
	}
	
	
}
public void ResponseWriter(String content) {
    try {
        File file = new File("Execute.bat");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
      //  System.out.println("Job written to Execute Batch. Invoking GetPayBackInfo sequence..");
      //  RunBatch respToExcel = new RunBatch();
      //  respToExcel.runBat("RunRespMail.bat", "");
    }
    catch (IOException e) {
        e.printStackTrace();
    }
}
public boolean GPBBAT(String PhNo){
	String cmd = "cmd /c  start /min Execute.bat";
    int threadStatus = 1;
	Runtime r = Runtime.getRuntime();
	Process pr = null;
	try {
		pr = r.exec(cmd);
		threadStatus = Sleeper(pr,PhNo);
		//System.out.println("Sleeper Status:" + threadStatus); 
} catch (IOException e) {
		// TODO Auto-generated catch block
	e.printStackTrace();
	}
	if(threadStatus == 0){
		return true; 
	 }
	else{
		return false;
	}
}
public int Sleeper(Process prs,String phno){
	int exitValue = 1;
	try {
	    Thread.sleep(1 * 1000);
	    exitValue = prs.exitValue();
	    // get the process results here
	  //  System.out.println(exitValue);
	   } catch (InterruptedException e) {
		   // TODO Auto-generated catch block
		   //e.printStackTrace();
		  System.out.println("Waiting for 5 secs for " + phno );
		   Sleeper(prs,phno);
	   }
	return exitValue;
}

}
