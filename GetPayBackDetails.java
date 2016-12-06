package Autosys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GetPayBackDetails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
GetPayBackDetails gpb = new GetPayBackDetails();

Set<String> sd = new HashSet<String>();
	gpb.sendGet(sd);
	}
	public void sendGet(Set<String> sds) {
		 // The name of the file to open.
        String fileName = "Response.xml";
        Set<String> remaning = new HashSet<String>();
        // This will reference one line at a time
        String line = null;
        String response = "";
        String MobileNo = "";
        String[] s = null;
        String[] m = null;
        int MobCtr = -1;
        int DataCtr = 0;
        Map<Integer,Integer> MobData = new HashMap<Integer,Integer>();
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
              //  System.out.println(line);
                if(line.contains("<strData>") && line.contains("</strData>") && line.contains("|")){
                	response = response + "end" + line.split("strData")[1].trim()  + "end" ;
                	MobData.put(DataCtr,MobCtr);
                //	System.out.println("All  "+ DataCtr+ ","+MobCtr);
                	DataCtr++;
                }
                else if(line.contains("<strData>")  && line.contains("|")){
                	response = response +  "end" + line.split("<strData>")[1].trim();
        	     	MobData.put(DataCtr,MobCtr);
             //   	System.out.println("Break "+ DataCtr+ ","+MobCtr);
                	DataCtr++;
                }
                else if(line.contains("</strData>") && line.contains("|")){
                	response = response + line.split("</strData>")[0].trim() +  "end" ;
                }
                else if(line.contains("Mobile:")){
                	MobileNo = MobileNo  + line.split(":")[1].trim() + ":";
                	MobCtr++;
                }
                
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    
			
		//Generate the response and extract the status
	    if(response.contains("|")){
	   // System.out.println("Ok response");
	    s = response.replaceAll("endend", "end").replaceAll("[></]", "").replaceAll("[|]",",").split("end");
	    response =  response.replaceAll("endend", "end").replaceAll("[></]", "").replaceAll("[|]",",").replaceAll("end", "\r\n");
	   // System.out.println(response);
	    //ResponseWriter(response);
	    m = MobileNo.split(":");
	    
	    //System.out.println(MobData.get(0));
	    for(int x =0;x<=s.length-2;x++){
	    if(sds.toString().contains(m[MobData.get(x)])){
	    	System.out.println(m[MobData.get(x)] + s[x+1] );
	    ResponseWriter(m[MobData.get(x)] + s[x+1].replaceAll("\\w+\\=","") + "\r\n");
	    }
	    
	    }
	    int numOfRes = 0;
	    for(String PNO : sds){
			
			   if(!MobileNo.contains(PNO)){
				   numOfRes++;
				   remaning.add(PNO);
				   System.out.println("Found:"+PNO);
			   }
				
			}
	    extractPhoneNumber epn = new extractPhoneNumber();
		
	    if(numOfRes != 0){
	    	int numOfResp=0;
	    	for(String PNO : remaning){
	    		
	    		   if(epn.getPointsInfo(PNO)){
	    			   numOfResp++;
	    				System.out.println(numOfResp + ") Done:"+PNO);
	    			}
	    			else{
	    				System.out.println("Error:"+PNO);
	    			}
	    		}
	    	sendGet(remaning);
	    }
	    }
	    else{
		System.out.println("No data found");
	    }
        }
	public void ResponseWriter(String content){
		try {
            //System.out.println("<br>" +sfinalResponse);
			File file = new File("JobResponse.csv");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();


		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("<br>" +"ResponseWriter method failed</font>");
		}
		
	}
  	}
